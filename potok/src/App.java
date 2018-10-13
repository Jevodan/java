

public class App {
    public static void main(String[] args) {
        App e1 = new App();
        System.out.println("Start");
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method2()).start();
    }
    public synchronized void method1() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }
    public synchronized void method2() {
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }
}