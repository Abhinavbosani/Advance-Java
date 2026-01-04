package MultiThreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Locks implements Callable<Integer> {
    static ReentrantLock lock = new ReentrantLock();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static int counter=0;
    @Override
    public Integer call() throws Exception {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is processing task");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " is completed task");
        }finally {
            lock.unlock();
        }
        return 0;

    }

    public void increment() {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            counter++;
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " incremented counter to: " + counter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            readWriteLock.writeLock().unlock();
        }
    }

    public void getCount() {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is reading: " + counter);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        Locks locks = new Locks();
        ThreadPoolExecutor ex=new ThreadPoolExecutor(3,6,
                2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());

        ex.submit(new Locks());
        ex.submit(new Locks());
        ex.submit(new Locks());
        ex.submit(new Locks());

        ex.submit(locks::increment);
        ex.submit(locks::increment);
        ex.submit(locks::getCount); //count
        ex.submit(locks::increment);
        ex.submit(locks::getCount); //count
        ex.submit(locks::increment);

        ex.submit(locks::getCount);
        ex.submit(locks::getCount);

        ex.shutdown();

    }
}
