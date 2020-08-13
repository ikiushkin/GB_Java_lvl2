package ru.geekbrains;

import java.util.Arrays;

public class Lesson5_2 {

    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    static float[] arr1 = new float[h];
    static float[] arr2 = new float[h];

    public static void main(String[] args) {

        method1(arr);
        method2(arr);

    }

    private static void method1(float[] arr) {

        Arrays.fill(arr, 1);

        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis());
    }

    private static void method2(float[] arr) {

        Arrays.fill(arr, 1);

        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread myThready1 = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread myThready2 = new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        myThready1.start();
        myThready2.start();

        float[] arr3 = new float[size];
        System.arraycopy(arr1, 0, arr3, 0, h);
        System.arraycopy(arr2, 0, arr3, h, h);

        System.out.println(System.currentTimeMillis());
    }
}
