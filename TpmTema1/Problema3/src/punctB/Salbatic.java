package punctB;

public class Salbatic extends Thread {

    private Oala oalaReferinta;
    private NotificareChef canal;
    private String salbatic;
    private int countMeals;
    private int index;
    private double averageAccessWait;
    private double time;

    public Salbatic(Oala oala, NotificareChef canal, int meals, int index) {
        oalaReferinta = oala;
        this.canal = canal;
        this.salbatic = "Salbatic " + (int) getId();
        this.countMeals = meals;
    }

    public double getAverageAccessWait() {
        return averageAccessWait;
    }

    public void setAverageAccessWait(double averageAccessWait) {
        this.averageAccessWait = averageAccessWait;
    }

    private void eat() {
        for (int i = 0; i < 2; i++) {
            double start = System.currentTimeMillis();
            oalaReferinta.lock(salbatic, index);
            while (oalaReferinta.isEsteGoala()) {
//                System.out.println(salbatic + " vede oala goala. Asteapta umplere oala");
                oalaReferinta.unlock(salbatic);
                while (oalaReferinta.isEsteGoala()) {
                }
                oalaReferinta.lock(salbatic, index);
            }
//            System.out.println(salbatic + " mananca");
            oalaReferinta.scadeCapacitate();
            if (oalaReferinta.veziNumarPortii() == 0) {
//                System.out.println(salbatic + " vede oala goala. Notifica chef");
                oalaReferinta.setEsteGoala(true);
//                System.out.println(salbatic + " trimite notificare spre chef");
                canal.lock();
                canal.setCerere(true);
                canal.unlock();
            }
            oalaReferinta.unlock(salbatic);
            double finish = System.currentTimeMillis();
            averageAccessWait += (finish - start);
        }
        System.out.println(salbatic + " a accessat in medie oala intr-un timp de " + averageAccessWait / countMeals);
    }

    @Override
    public void run() {
        this.eat();
    }
}
