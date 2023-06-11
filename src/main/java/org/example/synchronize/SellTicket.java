package org.example.synchronize;
/**
 * 使用多線程，模擬3個窗口同時售票100張
 */
public class SellTicket {
    public static void main(String[] args) {
        SellTicket03 sellTicket03 = new SellTicket03();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
    }
}

//實現接口方式，使用synchronized實現線程同步
//每個執行緒在運行 run() 方法時都會檢查 loop 變數，因為 loop 被初始化為 true，所以每個執行緒都會進入迴圈，並呼叫 sell() 方法。
//在 sell() 方法中，由於 synchronized 關鍵字的存在，同一時間只有一個執行緒可以進入 sell() 方法。其他的執行緒會在門外等待。
//假設 Thread1 是第一個進入 sell() 方法的執行緒。Thread1 會檢查 ticketNum 變數，如果 ticketNum 大於 0，那麼執行緒會休眠 50 毫秒（模擬售票的過程），然後列印一條消息並將 ticketNum 減 1。由於我們假設 ticketNum 初始為 5，所以 ticketNum 現在變為 4。Thread1 退出 sell() 方法，釋放對 sell() 方法的鎖定。
//Thread1 釋放鎖定後，Thread2 或 Thread3（取決於 JVM 的執行緒調度策略）可以進入 sell() 方法，並重複 Thread1 剛才的過程。
//當所有的票都售完（也就是 ticketNum 變為 0）時，進入 sell() 方法的下一個執行緒會將 loop 變數設為 false，然後退出 sell() 方法。
//此時，所有的執行緒都會退出 while 迴圈（因為 loop 變為 false），然後在 run() 方法中列印 "售票結束..."。
//class SellTicket03 implements Runnable {
//    private int ticketNum = 1000;//让多个线程共享ticketNum
//    private boolean loop = true;
//    //synchronized同步方法, 在同一时刻只能有一个线程来执行sell方法
//    //解释为什么把synchronized加到sell()方法上而不是直接加到我们下面的run()方法上
//    //因为直接加到run()方法就不能保证同时多线程了(synchronized作用就是保证同时只能由一个线程进入方法)，
//    //而题目要求我们需要三个线程
//    public /*synchronized*/ void sell(){//同步方法
//        synchronized (this) {
//            if (ticketNum <= 0) {
//                System.out.println("售票结束");
//                loop = false;//结束while循环
//                return;
//            }
//
//            //休眠50毫秒
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("窗口 " + Thread.currentThread().getName() + "售出一张票" +
//                    "剩余票数=" + (--ticketNum));
//        }
//    }
//    //同步方法(靜態)的鎖為當前類本身
//    //1.此鎖是加在SellTicket03.class上
//    //2.如果在靜態方法中實現一個同步代碼塊要使用
//
//    //    public static void m2(){
//    //        synchronized (SellTicket03.class){
//    //            System.out.println("m2");
//    //        }
//    //    }
//    public synchronized static void m1(){}
//    @Override
//    //synchronized 直接加到run()方法就不能保证同时多线程,所以写一个sell方法，在那里加synchronized
//    //这样既保证了同时可以有三个线程进入run，又可以保证同时只能有一个线程去访问sell方法
//    public void run() {
//        while(loop){
//            sell();
//        }
//    }
//}
class SellTicket03 implements Runnable {
    private int ticketNum = 1000; //讓多個線程共享

    @Override
    public void run() {
        while (true) {
            sell();
        }
    }

    private void sell() {
        synchronized (this) {
            if (ticketNum <= 0) {
                System.out.println("售票結束...");
                System.exit(0);  // 當票已售完，則結束程式
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出了一張票" + " ，剩餘票數=" + (--ticketNum));
        }
    }
}

