Future<V> is the result of an asynchronous computation that may not yet have completed.

CompletableFuture<V> implements both Future<V> and CompletionStage<V> interface that enables non-blocking operations, callback support, exception handling, and composability.

Methods:
1. Running tasks asynchronously: supplyAsync(Supplier<T> supplier), runAsync(Runnable runnable)
2. Chaining: thenApply(Function<T, R> fn), thenAccept(Consumer<T> action), thenRun(Runnable action), thenCompose(Function<T, CompletableFuture<R>> fn)
3. Combining: thenCombine(CompletableFuture<U> other, BiFunction<T, U, R> fn), allOf(), anyOf()
4. Exception: exceptionally(Function<Throwable, T> fn), handle(BiFunction<T, Throwable, R> fn), whenComplete(BiConsumer<T, Throwable> action)
5. Completion: isDone()
6. Follow-up: thenApply(), thenAccept()