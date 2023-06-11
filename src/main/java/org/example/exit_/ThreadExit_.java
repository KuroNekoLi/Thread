package org.example.exit_;

public class ThreadExit_ {
    public static void main(String[] args) {
        T t1 = new T();
        t1.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.setLoop(false); //讓t1退出run方法，從而終止t1線程 -> 通知方式
    }
}
class T extends Thread{
    private int count = 0;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    private boolean loop = true;
    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("T 運行中");
        }
    }
}
