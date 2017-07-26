package Main;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
    static boolean trigger = true;

    public void method1() {
        long a = System.currentTimeMillis();
        Arrays.fill(arr, 1);
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    static class method2Thread implements Runnable{
        static long a = System.currentTimeMillis();
        static float[] arr = new float[size];
        @Override
        public void run() {
            float[] a2 = new float[h];
            float[] a1 = new float[h];
            if (trigger == true) {
                System.arraycopy(arr, 0, a1, 0, h);
                for (int i = 0; i < h; i++) {
                    a1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                trigger = false;
            }
            else {
                System.arraycopy(arr, h, a2, 0, h);
                for (int i = 0; i < h; i++) {
                    a2[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
            if (true ) {
                System.arraycopy(a1, 0, arr, 0, h);
                System.arraycopy(a2, 0, arr, h, h);
                System.out.println(System.currentTimeMillis() - a);
            }
        }

    }

    public static void main(String[] args) {
        new Main().method1();
        new Thread(new method2Thread()).start();
        new Thread(new method2Thread()).start();

    }
}