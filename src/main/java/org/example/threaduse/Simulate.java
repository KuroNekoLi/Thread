package org.example.threaduse;

//參考https://javaguide.cn/java/basis/proxy.html
//https://medium.com/@mithunsasidharan/understanding-the-proxy-design-pattern-5e63fe38052a

public class Simulate {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();
    }
}

class Animal{}
class Tiger extends Animal implements Runnable{
    @Override
    public void run() {
        System.out.println("老虎嗷嗷叫!");
    }
}
//可把Proxy當作一個Thread類
class ThreadProxy implements Runnable{

    private Runnable target = null;

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    @Override
    public void run() {
        if (target != null){
            target.run(); //動態綁定
        }
    }

    public void start(){
        start0();
    }

    private void start0(){
        run();
    }
}
