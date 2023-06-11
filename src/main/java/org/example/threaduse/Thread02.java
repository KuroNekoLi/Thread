package org.example.threaduse;

/**
 * 透過實現接口創建線程
 */
public class Thread02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //創建Thread對象，把dog對象(實現Runnable)放入Thread
        //這裡使用了代理模式(靜態代理)
        Thread thread = new Thread(dog);
        thread.start();
    }
}
class Dog implements Runnable{
    int count = 0;
    @Override
    public void run() {
        while (true){
            System.out.println("小狗汪汪叫..hi"+(++count)+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count==10){
                break;
            }
        }
    }
}