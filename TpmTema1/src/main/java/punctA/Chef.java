package punctA;

public class Chef extends Thread {
    private Oala referenceToOala;
    private int capacity;
    private volatile boolean requestToFill = false;
    private volatile boolean wasCalledForRefill = false;

    public Chef(Oala oala, int capacity) {
        this.referenceToOala = oala;
        this.capacity = capacity;
    }

    public synchronized void lock(int threadIndex) {
        while (wasCalledForRefill) {
        }
        wasCalledForRefill = true;
        System.out.println("Chef locked by thread " + threadIndex);
    }

    public void setRefill() {
        this.requestToFill = true;
    }

    public void unlock(int threadIndex) {
        wasCalledForRefill = false;
        System.out.println("Chef freed by thread " + threadIndex);
    }

    @Override
    public void run() {
        while (true) {
            if (requestToFill) {
                executeRequestToRefill();
                requestToFill = false;
            }
        }
    }

    private void executeRequestToRefill() {
        this.referenceToOala.lock();
        try {
            System.out.println("Received request to refill");
            if (this.referenceToOala.getCurrentNumberOfPortions() == 0) {
                this.referenceToOala.fillOala(capacity);
                System.out.println("refilled");
            } else {
                System.out.println("oala is already at its maximum capacity");
            }
        } finally {
            this.referenceToOala.unlock();
        }
    }
}
