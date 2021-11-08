package punctB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int capacity = 2;
        int meals = 100;
        int numberOfSalbatici = 200;
        Oala oala = new Oala(capacity, numberOfSalbatici);
        NotificareChef notificareChef = new NotificareChef();
        Chef chef = new Chef(oala, notificareChef, capacity);
        Statistics[] statistics = new Statistics[numberOfSalbatici + 2];
        chef.start();
        double startProcess = System.currentTimeMillis();
        List<Salbatic> salbaticList = new ArrayList<>(numberOfSalbatici);
        for (int i = 0; i < numberOfSalbatici; i++) {//0 is for chef
            salbaticList.add(new Salbatic(oala, notificareChef, meals, i + 1));
            statistics[i] = new Statistics();
            statistics[i].index = i;
        }
        statistics[numberOfSalbatici] = new Statistics();
        statistics[numberOfSalbatici].index = numberOfSalbatici;
        statistics[numberOfSalbatici + 1] = new Statistics();
        statistics[numberOfSalbatici + 1].index = numberOfSalbatici + 1;
        salbaticList.forEach(Thread::start);
        for (Salbatic salbatic : salbaticList) {
            try {
                salbatic.join();//waiting for all the threads to finish
//                System.out.println("Salbatic " + salbatic.getIndex() + " oprit");
                statistics[salbatic.getIndex()].maximum = Arrays.stream(salbatic.getData()).max().getAsDouble();
                statistics[salbatic.getIndex()].minimum = Arrays.stream(salbatic.getData()).min().getAsDouble();
                statistics[salbatic.getIndex()].average = Arrays.stream(salbatic.getData()).average().getAsDouble();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double finishProcess = System.currentTimeMillis();

        chef.stop();
        System.out.println("Everything went smoothly. The time is in milliseconds.");
        System.out.println("Total time to run: " + (finishProcess - startProcess));
        for (int i = 1; i <= numberOfSalbatici; i++) {
            System.out.println(statistics[i]);
        }
    }
}
