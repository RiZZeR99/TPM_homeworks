package punctA;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Oala implements Lock {
    private volatile int capacity;
    private volatile boolean isBusy = false;

    public Oala(int capacity) {
        this.capacity = capacity;
    }

    public synchronized boolean decreaseNumberOfPortions() {
        if (capacity > 0) {
            capacity--;
            return true;
        }
        return false;
    }

    public synchronized int getCurrentNumberOfPortions() {
        return capacity;
    }

    public synchronized void fillOala(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void lock() {
        while (isBusy) {
            Thread.onSpinWait();
        }
        isBusy = true;
    }

    @Override
    public void unlock() {
        isBusy = false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
