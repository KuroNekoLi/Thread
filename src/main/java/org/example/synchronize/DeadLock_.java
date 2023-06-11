package org.example.synchronize;

/**
 * 模擬線程死鎖
 */
public class DeadLock_ {
    public static void main(String[] args) {
        //模擬死鎖現象
        DeadLockDemo A = new DeadLockDemo(true);
        A.setName("線程1");
        DeadLockDemo B = new DeadLockDemo(false);
        B.setName("線程2");
        A.start();
        B.start();
    }
}

class DeadLockDemo extends Thread {
    static Object o1 = new Object(); //保證多線程共享一個對象
    static Object o2 = new Object();
    boolean flag;

    public DeadLockDemo(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        //1. 如果flag為T，線程1就會先持有o1對象鎖，然後嘗試獲取o2對象鎖
        //2. 如果線程1得不到o2對象鎖，就會blocked
        //3. 如果flag為F，線程2就會先持有o2對象鎖，然後嘗試獲取o1對象鎖
        //4. 如果線程1得不到o1對象鎖，就會blocked
        if (flag) {
            synchronized (o1) { //對象互斥鎖，下面就是同步代碼
                System.out.println(Thread.currentThread().getName() + "進入1");
                synchronized (o2) {//這裡獲得o1對象的監視權
                    System.out.println(Thread.currentThread().getName() + "進入2");
                }
            }
        }else{
            synchronized (o2) {

                System.out.println(Thread.currentThread().getName() + "進入3");
                synchronized (o1) {//這裡獲得o1對象的監視權
                    System.out.println(Thread.currentThread().getName() + "進入4");
                }
            }
        }
    }
}