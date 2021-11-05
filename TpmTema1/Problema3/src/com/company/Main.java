package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int capacity = 2;
        int numberOfSalbatici = 5;
        Oala oala = new Oala(capacity);
        NotificareChef notificareChef = new NotificareChef();
        Chef chef = new Chef(oala, notificareChef, capacity);
        chef.start();
        List<Salbatic> salbaticList = new ArrayList<>(5);
        for (int i : new int[numberOfSalbatici]) {
            salbaticList.add(new Salbatic(oala, chef, notificareChef));
        }
        salbaticList.forEach(Thread::start);
        for (Salbatic salbatic : salbaticList) {
            try {
                salbatic.join();//waiting for all the threads to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chef.stop();
        System.out.println("Everything went smoothly");
    }
}
