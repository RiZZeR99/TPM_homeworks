package punctB;

public class NotificareChef {

    private volatile boolean isLocked = false;
    private volatile boolean cerere = false;

    public void lock() {
        synchronized (this) {
            while (isLocked) {
            }
            isLocked = true;
        }
    }

    public  void unlock() {
        isLocked = false;
    }

    public void setCerere(boolean value) {
        cerere = value;
    }

    public boolean getCerere() {
        return cerere;
    }
}
