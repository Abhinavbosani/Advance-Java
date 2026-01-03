package MultiThreading;

public class BasicThreadExample extends Thread{
    private static int value;

    @Override
    public void run() {
        value=10;
        for (int i = 0; i < 5 ; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread is Running: "+ Thread.currentThread().getName()+", value: "+value);
            value+=10;
            if(value==30){
                Thread.yield();
            }
        }

    }

    public static void main(String[] args) {
        BasicThreadExample b=new BasicThreadExample();
        b.start();

        BasicRunnable br=new BasicRunnable();

        Thread t=new Thread(br);
        t.start();

    }
}
class BasicRunnable implements Runnable{
    static class InnerClass{
        InnerClass(){
            System.out.println("Inner class!");
        }
    }
    @Override
    public void run() {
        System.out.println("Runnable Thread: "+Thread.currentThread().getName());
    }
}
