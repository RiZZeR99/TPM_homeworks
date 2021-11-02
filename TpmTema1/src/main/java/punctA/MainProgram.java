package punctA;

import java.util.ArrayList;
import java.util.List;

public class MainProgram {
    public static void main(String[] args) throws InterruptedException {
        int capacity = 2;
        int numberOfSalbatici = 5;
        Oala oala = new Oala(capacity);
        Chef chef = new Chef(oala, capacity);
        chef.start();
        List<Salbatic> salbaticList = new ArrayList<>(5);
        for (int i : new int[numberOfSalbatici]) {
            salbaticList.add(new Salbatic(oala, chef));
        }
        salbaticList.forEach(Thread::start);
        for (Salbatic salbatic : salbaticList) {
            salbatic.join();
        }
        chef.stop();
        System.out.println("Everything went smoothly");

    }
}
