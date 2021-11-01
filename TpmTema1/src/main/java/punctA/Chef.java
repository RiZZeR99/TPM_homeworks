package punctA;

import java.util.concurrent.locks.ReentrantLock;
//Class responsible with fulling the oala
public class Chef extends Thread {
    private Oala referenceToOala;
    private int capacity;
    private ReentrantLock lock;

    public Chef(Oala oala, int capacity) {
        this.referenceToOala = oala;
        this.capacity = capacity;
    }

    public synchronized void executeRequestToRefill() {
//        this.lock.lock();
        System.out.println("Received request to refill");
        this.referenceToOala.lock();
        try {
            if (this.referenceToOala.getCurrentNumberOfPortions() == 0) {
                this.referenceToOala.fillOala(capacity);
                System.out.println("refilled");
            }
            else{
                System.out.println("oala is already at its maximum capacity");
            }
        } finally {
//            this.lock.unlock();
            this.referenceToOala.unlock();
        }
    }
}
