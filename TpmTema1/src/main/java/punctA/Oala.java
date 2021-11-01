package punctA;

import java.util.concurrent.locks.ReentrantLock;

public class Oala {
    private volatile int capacity;
    private ReentrantLock lock;

    public Oala(int capacity) {
        this.capacity = capacity;
        this.lock = new ReentrantLock();
    }

    public synchronized void lock() {
        this.lock.lock();
    }

    public synchronized void unlock() {
        this.lock.unlock();
    }

    public synchronized boolean decreaseNumberOfPortions() {
        if (capacity>0){
            capacity--;
            return true;
        }
        return false;
    }
    public synchronized int getCurrentNumberOfPortions(){
        return capacity;
    }
    public synchronized void fillOala(int capacity){
        this.capacity = capacity;
    }
}
