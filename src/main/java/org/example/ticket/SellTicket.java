package org.example.ticket;
/**
 * 使用多線程，模擬3個窗口同時售票100張
 */
public class SellTicket {
    public static void main(String[] args) {
//        SellTicket01 sellTicket01 = new SellTicket01();
//        SellTicket01 sellTicket02 = new SellTicket01();
//        SellTicket01 sellTicket03 = new SellTicket01();
//
//        //這裡會出現超賣
//        sellTicket01.start();
//        sellTicket02.start();
//        sellTicket03.start();

        System.out.println("===使用實現接口的方式售票====");
        SellTicket02 sellTicket02 = new SellTicket02();
        new Thread(sellTicket02).start();
        new Thread(sellTicket02).start();
        new Thread(sellTicket02).start();
    }
}

//使用Thread方式
class SellTicket01 extends Thread{
    private static int ticketNum = 100; //讓多個線程共享
    @SuppressWarnings("BusyWait")
    @Override
    public void run() {

        do {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口 "+Thread.currentThread().getName()+" 售出了一張票"+" ，剩餘票數=" + (--ticketNum));
        }while (ticketNum >0);
            System.out.println("售票結束...");

    }
}
//實現接口方式
class SellTicket02 implements Runnable{
    private int ticketNum = 100; //讓多個線程共享
    @SuppressWarnings("BusyWait")
    @Override
    public void run() {

        do {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口 "+Thread.currentThread().getName()+" 售出了一張票"+" ，剩餘票數=" + (--ticketNum));
        }while (ticketNum >0);
        System.out.println("售票結束...");

    }
}