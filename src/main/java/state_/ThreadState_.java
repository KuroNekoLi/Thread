package state_;
/**
 * 生命週期
 */
//https://www.ctyun.cn/zhishi/p-218330
public class ThreadState_ {
    public static void main(String[] args) {
        T t = new T();
        System.out.println(t.getName()+"狀態"+t.getState());
        t.start();

        while ((Thread.State.TERMINATED!= t.getState())){
            System.out.println(t.getName()+"狀態"+t.getState());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(t.getName()+"狀態"+t.getState());
    }
}
class T extends Thread{
    @SuppressWarnings({ "BusyWait"})
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "執行中...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
        }
    }
}
