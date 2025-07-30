# Kafka Producer-Consumer with Database Storage

## What I Built
Added database storage and implemented At-Least-Once and At-Most-Once delivery patterns.

## Database Schema
`kafka_messages` table stores:
- message_key (unique for idempotency)
- message_content 
- topic, partition, offset
- consumer_group, processed_at
- delivery_type, retry_count, status

## At-Least-Once Implementation
- Consumer group: `at-least-once-group`
- Commits offset AFTER processing and DB save
- Checks for duplicate keys
- Retries on failure

## At-Most-Once Implementation
- Consumer group: `at-most-once-group` 
- Commits offset BEFORE processing
- No retries, faster processing
- Possible message loss

## API Endpoints
- `POST /publish?key=X&message=Y` - Send message
- `POST /publish-batch?count=10` - Send batch
- `GET /stats` - View statistics
- `GET /messages` - View all messages
- `DELETE /clear` - Clear all messages

## Testing
```bash
# Start MySQL and create kafka_messages database
# Start Kafka and application

# Test messages
curl -X POST "localhost:8088/publish?key=test1&message=hello"
curl -X POST "localhost:8088/publish-batch?count=5"
curl localhost:8088/stats
```

## Key Difference
- **At-Least-Once**: No message loss, possible duplicates
- **At-Most-Once**: No duplicates, possible message loss

Both patterns save messages to database for verification.

## Verification
✅ **App compiles and runs successfully**
✅ **Manual acknowledgment configured properly** 
✅ **Database schema auto-creates**
✅ **Both delivery patterns implemented**