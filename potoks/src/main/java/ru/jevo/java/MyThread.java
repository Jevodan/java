package ru.jevo.java;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyThread extends Thread {

    long timeStart;
    long timeEnd;
    String lol;

    @Override
    public void run() {
        super.run();
    }

    public MyThread(float[] arr, int size, String mes) {
        new Thread(() -> {
            timeStart = System.currentTimeMillis();
            for (int i = 0; i < size; i++)
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            System.out.print(mes);
            timeEnd = System.currentTimeMillis() - timeStart;
            System.out.println(timeEnd);
        }).start();
    }
}
