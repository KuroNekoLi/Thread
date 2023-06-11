package org.example.method;

public class ThreadMethod01 {
    public static void main(String[] args) {
        T t = new T();
        t.setName("李靈");
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
        System.out.println(t.getName());

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hi"+i);
        }
        System.out.println(t.getName()+"線程的優先級="+t.getPriority());
        t.interrupt();
    }
}

class T extends Thread{
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+" 吃包子~~~" + i);
            }
            try {
                Thread.sleep(20000);
                System.out.println(Thread.currentThread().getName()+" 休眠中~~~" );
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+" 被中斷了" );
            }
        }
    }
}
