package punctB;

public class Salbatic extends Thread {

    private Oala oalaReferinta;
    private NotificareChef canal;
    private String salbatic;
    private int countMeals;
    private int index;
    private double accessTime;
    private double[] data;

    public Salbatic(Oala oala, NotificareChef canal, int meals, int index) {
        oalaReferinta = oala;
        this.canal = canal;
        this.salbatic = "Salbatic " + index;
        this.countMeals = meals;
        this.index = index;
        data = new double[meals];
    }

    public double[] getData() {
        return data;
    }

    private void eat() {
        for (int i = 0; i < countMeals; i++) {
            double start = System.currentTimeMillis();
//            System.out.println(salbatic + " tries to get the lock");
            oalaReferinta.lock(salbatic, index);
//            System.out.println(salbatic + " get the lock");
            while (oalaReferinta.isEsteGoala()) {
//                System.out.println(salbatic + " vede oala goala. Asteapta umplere oala");
                oalaReferinta.unlock(salbatic, index);
                while (oalaReferinta.isEsteGoala()) {
                }
                oalaReferinta.lock(salbatic, index);
            }
            try {
//            System.out.println(salbatic + " mananca la tura " + i);
                oalaReferinta.scadeCapacitate(index);
                if (oalaReferinta.veziNumarPortii() == 0) {
//                System.out.println(salbatic + " vede oala goala. Notifica chef");
                    oalaReferinta.setEsteGoala(true);
//                System.out.println(salbatic + " trimite notificare spre chef");
                    canal.lock();
                    canal.setCerere(true);
                    canal.unlock();
                }
            } catch (Exception e) {
                System.out.println("Error thread " + salbatic + ". Tried to eat -1 portie");
            } finally {
                oalaReferinta.unlock(salbatic, index);
            }
            double finish = System.currentTimeMillis();
            accessTime += (finish - start);
            data[i] = accessTime;
        }
//        System.out.println(salbatic + " a accessat in medie oala intr-un timp de " + accessTime / countMeals);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void run() {
        this.eat();
    }
}
