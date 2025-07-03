Java Garbage Collection is an automatic process within the JVM that manages memory. It reclaims memory occupied by objects that are no longer in use.

1. Serial GC: Single-threaded collector that stops the application entirely.
2. Parallel GC: Multi-threaded collector that executes GC tasks in parallel.
3. Concurrent Mark-Sweep: Minimize application pause times by performing most of the marking work concurrently with the application.
4. Garbage-First (G1) GC: Divides heap into many regions, collecting in regions with the most garbage first and attempting to meet a specified pause time goal.
5. ZGC: A scalable low-latency collector designed for very large heaps with minimal pause times.
6. Shenandoah GC: Similar to ZGC.