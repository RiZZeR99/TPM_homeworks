package com.company;

public class Oala {
    private volatile int capacitate;
    private volatile boolean isLocked;
    private volatile boolean esteGoala;

    public Oala(int capacitate) {
        this.capacitate = capacitate;
    }

    public void lock() {
        //needs re-work
        //pana seteaza pe true lock-ul altul vine citeste si vede ca e fals asa ca pune si el pe true lock-ul
        //desi cu synchronized nu ar trebui sa fie asa
        synchronized (this) {
            while (isLocked) {
            }
            isLocked = true;
        }
    }

    public void unlock() {
        isLocked = false;
    }

    public void umpleOala(int capacitate) {
        this.capacitate = capacitate;
    }

    public void scadeCapacitate() {
        if(capacitate>0)capacitate--;
        else try {
            throw new Exception("Ai mancat -1 portie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int veziNumarPortii() {
        return capacitate;
    }

    public boolean isEsteGoala() {
        return esteGoala;
    }

    public void setEsteGoala(boolean value) {
        esteGoala = value;
    }
}
