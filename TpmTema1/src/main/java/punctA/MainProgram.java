package punctA;

import java.util.ArrayList;
import java.util.List;

public class MainProgram {
    public static void main(String[] args) {
        int capacity = 2;
        int numberOfSalbatici = 5;
        Boolean startFlag = false;
        Oala oala = new Oala(capacity);
        Chef chef = new Chef(oala, capacity);
        List<Salbatic> salbaticList = new ArrayList<>(5);
        for (int i : new int[numberOfSalbatici]) {
            salbaticList.add(new Salbatic(oala, chef, true));
        }
        salbaticList.forEach(Thread::start);
        startFlag = true;
        System.out.println("Everything went smoothly");

    }
}
