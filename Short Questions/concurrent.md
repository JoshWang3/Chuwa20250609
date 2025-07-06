Concurrent collections are implementations of Collection and map interfaces that provide built-in thread-safe features. (HashMap -> ConcurrentHashMap)

Concurrent data structure:
1. Map: ConcurrentHashMap<K,V>
2. Queue: ConcurrentLinkedQueue<E>/ConcurrentLinkedDeque<E>, ArrayBlockingQueue<E>/ListBlockingQueue<E>, PriorityBlockingQueue<E>
3. List: CopyOnWriteArrayList<E>