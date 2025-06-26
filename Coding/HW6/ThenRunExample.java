import java.util.concurrent.CompletableFuture;

public class ThenRunExample {
    public static void main(String[] args) {
        // 模拟任务 A
        CompletableFuture<Void> taskA = CompletableFuture.runAsync(() -> {
            System.out.println("任务 A 完成");
        });

        // 模拟任务 B
        CompletableFuture<Void> taskB = CompletableFuture.runAsync(() -> {
            System.out.println("任务 B 完成");
        });

        // 模拟任务 C
        CompletableFuture<Void> taskC = CompletableFuture.runAsync(() -> {
            System.out.println("任务 C 完成");
        });

        // 等 A/B/C 都完成之后，再执行下面的逻辑
        CompletableFuture.allOf(taskA, taskB, taskC)
                .thenRun(() -> System.out.println("✅ 所有任务完成，开始后续操作！"))
                .join(); // 主线程等待所有完成
    }

    // 简化 sleep 写法
    private static void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}
