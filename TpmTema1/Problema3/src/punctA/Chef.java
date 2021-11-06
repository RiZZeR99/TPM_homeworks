package punctA;

public class Chef extends Thread {
    private Oala oalaReferinta;
    private NotificareChef notificareChef;
    private int capacitateOala;

    public Chef(Oala oala, NotificareChef notificareChef, int capacitateOala) {
        this.oalaReferinta = oala;
        this.notificareChef = notificareChef;
        this.capacitateOala = capacitateOala;
    }

    @Override
    public void run() {
        while (true) {
            notificareChef.lock();
            if (notificareChef.getCerere()) {
                System.out.println("Primit notificare sa umplu oala");
                oalaReferinta.lock("Chef");
                System.out.println("Am luat lock pe oala. Inceput sa umplu oala");
                oalaReferinta.umpleOala(capacitateOala);
                oalaReferinta.setEsteGoala(false);
                System.out.println("Am terminat de umplut oala. Eliberez lock oala");
                notificareChef.setCerere(false);
                oalaReferinta.unlock("Chef");
            }
            notificareChef.unlock();
        }
    }

}
