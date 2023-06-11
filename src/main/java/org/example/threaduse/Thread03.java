package org.example.threaduse;
/**
 * main線程啟動2個子線程
 */
public class Thread03 {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
    }
}

class T1 implements Runnable{
    int count;
    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        do {
            System.out.println("hello, world! " + (++count));
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (count != 10);
    }
}

class T2 implements Runnable{
    int count;
    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        do {
            System.out.println("hi! " + (++count));
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (count != 5);
    }
}
