public class OverloadPractice {
    public static class Practice {
        public int add(int a, int b) {
            return a + b;
        }

        public String add(String a, int b) {
            return a + b;
        }

        public String add(String a, String b) {
            return a + b;
        }
    }

    public static void main(String[] args) {
        Practice p1 = new Practice();
        System.out.println(p1.add(1, 2));
        System.out.println(p1.add("a", 3));
        System.out.println(p1.add("a", "b"));
    }
}