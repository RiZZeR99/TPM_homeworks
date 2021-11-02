package punctA;

public class Salbatic extends Thread {
    private final Oala referenceToOala;
    private final Chef referenceToChef;
    private final int index;
    private boolean hasEaten = false;

    public Salbatic(Oala oala, Chef chef) {
        this.referenceToOala = oala;
        this.referenceToChef = chef;
        this.index = (int) getId();
    }

    @Override
    public void run() {
        System.out.println("Salbatic " + this.index + " created");
        this.eat();
    }

    private void eat() {
        System.out.println("Salbatic " + this.index + " starts to eat");
        while (!hasEaten) {
            System.out.println("Entered into the try. Salbatic index " + this.index);
            if (referenceToOala.decreaseNumberOfPortions()) {
                System.out.println("Salbatic " + this.index + " has eaten");
                hasEaten = true;
            } else {
                System.out.println("Salbatic " + this.index + " found oala empty. Notifies chef");
                try {
                    referenceToChef.lock(this.index);
                    if (referenceToOala.getCurrentNumberOfPortions() == 0) {
                        System.out.println("Chef notified by salbatic " + this.index);
                        this.referenceToChef.setRefill();
                    }
                } finally {
                    referenceToChef.unlock(this.index);
                }
            }
        }
    }
}
