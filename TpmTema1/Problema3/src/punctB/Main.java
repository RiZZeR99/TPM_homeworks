package punctB;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int capacity = 5;
        int meals = 10000;
        int numberOfSalbatici = 500;
        Oala oala = new Oala(capacity, numberOfSalbatici);
        NotificareChef notificareChef = new NotificareChef();
        Chef chef = new Chef(oala, notificareChef, capacity);
        chef.start();
        List<Salbatic> salbaticList = new ArrayList<>(numberOfSalbatici);
        for (int i : new int[numberOfSalbatici]) {
            salbaticList.add(new Salbatic(oala, notificareChef, meals, i));
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
