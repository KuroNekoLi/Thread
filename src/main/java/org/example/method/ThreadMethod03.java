package org.example.method;
/**
 * 守護線程
 */
public class ThreadMethod03 {
    public static void main(String[] args) {
        MyDaemonThread myDaemonThread = new MyDaemonThread();
        //如果我們希望當main線程結束時，子線程自動結束，只需將子線程設為守護線程即可
        myDaemonThread.setDaemon(true);
        myDaemonThread.start();
        for (int i = 0; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + "執行中...");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (i==10){
                System.out.println(Thread.currentThread().getName() + "執行完畢...");
            }
        }
    }
}

class MyDaemonThread extends Thread{
    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "執行中...");
        }
    }
}
