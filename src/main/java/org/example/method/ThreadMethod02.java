package org.example.method;
/**
 * 使用線程插隊
 */
public class ThreadMethod02 {
    public static void main(String[] args) throws InterruptedException {
        T2 t2 = new T2();
        t2.start();
        for (int i = 0; i <= 20; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+i);
            if (i==5){
                System.out.println("T2"+"先執行");
                t2.join();
//                Thread.yield(); //禮讓，不一定成功
                System.out.println("T2"+"執行完畢");
            }
        }
    }
}
class T2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 20; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+i);
        }
    }
}
