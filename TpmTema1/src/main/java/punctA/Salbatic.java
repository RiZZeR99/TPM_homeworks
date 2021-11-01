package punctA;
//Class modelling an salbatic which eats
public class Salbatic extends Thread {
    private final Oala referenceToOala;
    private final Chef referenceToChef;
    private final int index;
    private volatile Boolean startFlag;

    public Salbatic(Oala oala, Chef chef, Boolean startFlag) {
        this.referenceToOala = oala;
        this.referenceToChef = chef;
        this.index = (int) getId();
        this.startFlag = startFlag;
    }

    @Override
    public void run() {
        while (!startFlag) {
            System.out.println("I wait salbatic with index " + this.index);
        }
        System.out.println("Salbatic " + this.index + " starts to eat");
        this.eat();
    }

    private void eat() {
        this.referenceToOala.lock();
        System.out.println("Salbatic " + index + " aquired lock");
        try {
            System.out.println("Salbatic " + this.index + " tries to eat");
            //trying to eat
            if (!this.referenceToOala.decreaseNumberOfPortions()) {
                System.out.println("Salbatic " + index + " found empty oala. Request to refill");
                this.referenceToOala.unlock();
                this.referenceToChef.executeRequestToRefill();
            }
            //waiting for the chef to refill if necessary
            while (!this.referenceToOala.decreaseNumberOfPortions()) {
                System.out.println("Waiting to refill. Salbatic " + index);
            }
        } finally {
            this.referenceToOala.unlock();
        }
    }
}
