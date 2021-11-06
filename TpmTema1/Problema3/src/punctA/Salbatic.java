package punctA;

public class Salbatic extends Thread {

    private Oala oalaReferinta;
    private NotificareChef canal;
    private String salbatic;

    public Salbatic(Oala oala, NotificareChef canal) {
        oalaReferinta = oala;
        this.canal = canal;
        this.salbatic = "Salbatic " + (int) getId();
    }

    @Override
    public void run() {
        oalaReferinta.lock(salbatic);
        while (oalaReferinta.isEsteGoala()) {
            System.out.println(salbatic + " vede oala goala. Asteapta umplere oala");
            oalaReferinta.unlock(salbatic);
            while (oalaReferinta.isEsteGoala()) {
            }
            oalaReferinta.lock(salbatic);
        }
        System.out.println(salbatic + " mananca");
        oalaReferinta.scadeCapacitate();
        if (oalaReferinta.veziNumarPortii() == 0) {
            System.out.println(salbatic + " vede oala goala. Notifica chef");
            oalaReferinta.setEsteGoala(true);
            System.out.println(salbatic + " trimite notificare spre chef");
            canal.lock();
            canal.setCerere(true);
            canal.unlock();
        }
        oalaReferinta.unlock(salbatic);
    }

}
