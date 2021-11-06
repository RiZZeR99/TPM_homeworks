package punctA;


public class Oala {
    private volatile int capacitate;
    private volatile boolean isLocked = false;
    private volatile boolean esteGoala;

    public Oala(int capacitate) {
        this.capacitate = capacitate;
    }

    public void lock(String name) {
        synchronized (this) {
            System.out.println("Thread " + name + " vrea sa obtina lock");
            while (isLocked) {
            }
            System.out.println("Thread " + name + " obtinut lock");
            isLocked = true;
        }
    }

    public void unlock(String name) {
        System.out.println("Deblocare de catre " + name);
        isLocked = false;
    }

    public void umpleOala(int capacitate) {
        this.capacitate = capacitate;
    }

    public void scadeCapacitate() {
        if (capacitate > 0) capacitate--;
        else try {
            throw new Exception("Ai mancat -1 portie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int veziNumarPortii() {
        return capacitate;
    }

    public boolean isEsteGoala() {
        return esteGoala;
    }

    public void setEsteGoala(boolean value) {
        esteGoala = value;
    }
}
