package com.company;

public class Salbatic extends Thread {

    private Oala oalaReferinta;
    private Chef chefReferinta;
    private NotificareChef canal;
    private String salbatic;

    public Salbatic(Oala oala, Chef chef, NotificareChef canal) {
        chefReferinta = chef;
        oalaReferinta = oala;
        this.canal = canal;
        this.salbatic = "Salbatic " + (int) getId();
    }

    @Override
    public void run() {
        oalaReferinta.lock();
        System.out.println(salbatic + " a obtinut lock");
        while (oalaReferinta.isEsteGoala()) {
            System.out.println(salbatic + " vede oala goala. Asteapta umplere oala");
            oalaReferinta.unlock();
            while (oalaReferinta.isEsteGoala()) {
            }
            oalaReferinta.lock();
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
        oalaReferinta.unlock();
        System.out.println(salbatic + " a deblocat oala");
    }

}
