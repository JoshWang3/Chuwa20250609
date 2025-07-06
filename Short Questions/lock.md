Object Lock: To synchronize access to non-static methods or blocks within a specific instance of a class. Only one thread can execute a synchronized method or block on a given object instance at a time.

Class Lock: To synchronize access to all static methods or blocks of code within a class.


Kinds of Locks:
1. synchronized keyword: JVM automatically handles the acquire and release of the object's monitor and is reentrant.
2. ReentrantLock: Can interrupt waits, enable more control methods such as timed lock(tryLock(long, Time))
3. ReadWriteLock: Used when more reads than writes. The lock is split into a read side and a write side.