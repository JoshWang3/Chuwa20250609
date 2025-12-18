  Changes Made

  1. Added Retry Tracking (DeliveryGuaranteeConsumerService.java:26-27)

  private final Map<String, Integer> retryAttempts = new ConcurrentHashMap<>();
  private static final int MAX_RETRY_ATTEMPTS = 3;

  2. Track Attempts Per Message (DeliveryGuaranteeConsumerService.java:93-98)

  String offsetKey = partition + "-" + offset;
  int attemptNumber = retryAttempts.getOrDefault(offsetKey, 0) + 1;
  retryAttempts.put(offsetKey, attemptNumber);

  System.out.println(String.format(
      "...| Attempt: %d/%d", attemptNumber, MAX_RETRY_ATTEMPTS
  ));

  3. DLQ After Max Retries (DeliveryGuaranteeConsumerService.java:121-133)

  if (attemptNumber >= MAX_RETRY_ATTEMPTS) {
      System.err.println("Max retries reached. Sending to Dead Letter Queue.");
      sendToDeadLetterQueue(partition, offset, key, message, e);
      acknowledgment.acknowledge(); // Commit to move forward
      retryAttempts.remove(offsetKey);
  } else {
      System.err.println("Will retry. Offset NOT committed.");
  }

  4. Dead Letter Queue Handler (DeliveryGuaranteeConsumerService.java:222-239)

  Logs poison messages with full context for later investigation/replay.

  ---
  Testing the Fix

  1. Restart your application

  mvn spring-boot:run

  2. Send the crash test message

  curl -X POST "http://localhost:8088/delivery-guarantee/test/at-least-once/crash"

  3. Watch console output - You should see:

  [AT-LEAST-ONCE CONSUMER #1] Attempt: 1/3
  [AT-LEAST-ONCE CONSUMER] Will retry (1/3). Offset NOT committed.

  [AT-LEAST-ONCE CONSUMER #2] Attempt: 2/3
  [AT-LEAST-ONCE CONSUMER] Will retry (2/3). Offset NOT committed.

  [AT-LEAST-ONCE CONSUMER #3] Attempt: 3/3
  [AT-LEAST-ONCE CONSUMER] Max retries (3) reached. Sending to Dead Letter Queue.

  ========== DEAD LETTER QUEUE ==========
  Partition: 0, Offset: X
  Message: crash-at-least-once
  ========================================

  [AT-LEAST-ONCE CONSUMER] Poison message handled. Offset committed to move forward.

  4. Check LAG - Should be 0 now!

  docker exec -it broker-1 kafka-consumer-groups \
    --bootstrap-server localhost:29091 \
    --describe \
    --group at-least-once-group

  5. Send normal message - Should process fine!

  curl -X POST "http://localhost:8088/delivery-guarantee/at-least-once?message=normal"

  ---
  Why This Matters

  This demonstrates the real-world challenge with at-least-once semantics:

  Without DLQ (Your Original Issue):

  - ✅ Message never lost (at-least-once guarantee)
  - ❌ Poison message blocks entire partition forever

  With DLQ (Fixed):

  - ✅ Message never lost (tried 3 times)
  - ✅ Poison message isolated to DLQ
  - ✅ Consumer continues processing other messages
  - ✅ Can replay DLQ messages after fixing the issue

  ---
  Summary

  Your observation was correct! The infinite LAG was a bug, not normal behavior. The fix implements the industry-standard pattern:

  1. ✅ Limited retries (3 attempts)
  2. ✅ Dead Letter Queue for poison messages
  3. ✅ Commit offset after DLQ to unblock consumer
  4. ✅ Preserve at-least-once semantics (tried multiple times)

  Check the new file POISON_MESSAGE_HANDLING.md for detailed documentation!