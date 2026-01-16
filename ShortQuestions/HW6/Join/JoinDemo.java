package Join;

public class JoinDemo {
    public static void main(String[] args) {
        Thread spongebob = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("SpongeBob finished cooking :)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Main starts");
        spongebob.start();

        try {
            spongebob.join(); // wait for thread spongebob finished cooking
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main resumes after SpongeBob finished");
    }
}


/*
output:
Main starts
SpongeBob finished cooking :)
Main resumes after SpongeBob finished
 */
