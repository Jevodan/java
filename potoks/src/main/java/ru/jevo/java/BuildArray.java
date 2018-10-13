package ru.jevo.java;

public class BuildArray {

    static final int SIZE = 10000000;
    static final int H = SIZE / 2;

    static public float[] getArrOne() {
        float[] arr = new float[SIZE];
        for (float cell : arr)
            cell = 1;
        return arr;
    }
}
