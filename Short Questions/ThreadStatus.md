1. NEW: A thread is in NEW state immediately after creation.
2. RUNNABLE: A thread transits to RUNNABLE state after invoking start()
3. BLOCKED: A thread will move to BLOCKED state when it attempts to enter a synchronized block or a method used by another thread. Will return to RUNNABLE after lock removed.
4. WAITING: A thread enters WAITING state when it calls any indefinite wait operations (.wait(), .join(), etc.)
5. TIMED_WAITING: WAITING state with a definite time limit (.wait(5)).
6. TERMINATED: A thread enters TERMINATED state after the run() method completes, either done running or throws exception and it will never run again.