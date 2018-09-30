package ru.jevo.java;


import lombok.SneakyThrows;


public class App {

    static long timeMethod2;

    public static void main(String[] args) {
        App e2 = new App();
        System.out.println("Start");
        new Thread(new MyThread(BuildArray.getArrOne(), BuildArray.SIZE, "Прогон целого массива: "));
        new Thread(() -> e2.methodTwo()).start();
    }

    @SneakyThrows
    public void methodTwo() {

        long cutTime, splitTime;
        final float[] arr = BuildArray.getArrOne();
        final float[] arrHalf1 = new float[BuildArray.H];
        final float[] arrHalf2 = new float[BuildArray.H];
        cutTime = System.currentTimeMillis();

        System.arraycopy(arr, 0, arrHalf1, 0, BuildArray.H - 1);
        System.arraycopy(arr, BuildArray.H - 1, arrHalf2, 0, BuildArray.H - 1);
        timeMethod2 += System.currentTimeMillis() - cutTime;
        System.out.println("Время на резку массива пополам: " + timeMethod2);

        final MyThread half1 = new MyThread(arrHalf1, BuildArray.H, "Прогон половины массива: ");
        final MyThread half2 = new MyThread(arrHalf2, BuildArray.H, "Прогон половины массива: ");

        half1.join();
        half2.join();

        Thread.sleep(5000);
        timeMethod2 += half1.getTimeEnd();
        timeMethod2 += half2.getTimeEnd();
        splitTime = System.currentTimeMillis();
        System.arraycopy(arrHalf1, 0, arr, 0, BuildArray.H);
        System.arraycopy(arrHalf2, 0, arr, BuildArray.H, BuildArray.H);
        timeMethod2 += System.currentTimeMillis() - splitTime;
        System.out.print("Время на склейку массива: ");
        System.out.println(System.currentTimeMillis() - splitTime);
        System.out.println("Итоговое Время с учетом склейки массива: " + timeMethod2);

    }


}