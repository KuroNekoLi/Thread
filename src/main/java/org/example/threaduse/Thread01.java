package org.example.threaduse;

/**
 * 創建一個新線程
 */
public class Thread01 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.start();
        //當主線程啟動一個子線程Thread-0,主線程不會阻塞，會繼續執行
        //如果這邊使用run()，依然會在主線程
        /*
        調用 start() 方法會間接調用 start0() 方法。這是 Java 中 Thread 類的一個本地方法，也就是 native method，通常這些方法是用其他語言（如 C 或 C++）實現的，然而這個方法對於 Java 開發者來說是不可見的。它是 JVM（Java 虛擬機器）內部的實現細節，用於啟動一個新的執行緒。
        當調用 Thread 的 start() 方法時，Java 會去執行一系列的操作，包括調用 start0()。這個方法會在作業系統層面創建一個新的執行緒並使其運行，然後這個新執行緒會調用 run() 方法。
        儘管 Java 開發者通常不能直接調用 start0()，但我們可以通過調用 start() 方法間接使用它。注意，我們不應直接調用 run() 方法，因為這不會創建新的執行緒，而是在當前執行緒中運行 run() 方法中的代碼。
        */
        //參考:https://segmentfault.com/a/1190000038287645
        //https://blog.csdn.net/fengxiandada/article/details/123432662

    }
}

//1.當一個類繼承Thread類，該類可當線程使用
//2.重寫run方法，寫上自己的程式碼
//3. run Thread類實現了Runnable接口的run方法
/*
    @Override
    public void run() {
        if (target != null){
        target.run();
    }
**/
class Cat extends Thread {
    private int times;

    @Override
    public void run() {
        while (true) {
            System.out.println("喵喵，我是小貓咪" + "線程名=" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                ++times;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (times == 80) {
                break;
            }
        }
    }
}