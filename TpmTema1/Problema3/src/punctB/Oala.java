package punctB;


public class Oala {
    private volatile int capacitate;
    private volatile boolean isLocked = false;
    private volatile boolean esteGoala;
    private int participant;
    private int[] level;
    private int[] victim;

    public Oala(int capacitate, int participants) {
        this.capacitate = capacitate;
        this.participant = participants;
        level = new int[participants + 2];
        victim = new int[participants + 2];
        for (int i = 0; i <= participants; i++) {
            level[i] = victim[i] = 0;
        }
    }

    public void lock(String name, int indexThread) {
        synchronized (this) {
//            System.out.println(indexThread + " entered");
            for (int L = 1; L < this.participant; L++) {
                level[indexThread] = L;
                victim[L] = indexThread;
                while ((existsPriorityThreadInWaitingCondition(L, indexThread)) &&
                        victim[L] == indexThread) {
                }
            }
            while (existsThreadStillExecutingCriticalSection(indexThread)) {
            }
        }
    }

    public void lockUnfair(String name, int indexThread) {
        synchronized (this) {
//            System.out.println("Thread " + name + " vrea sa obtina lock");
            while (isLocked) {
            }
//            System.out.println("Thread " + name + " obtinut lock");
            isLocked = true;
        }
    }

    private boolean existsPriorityThreadInWaitingCondition(int L, int indexThread) {
        for (int k = 0; k < participant; k++) {
            if (k != indexThread && level[k] >= L)
                return true;
        }
        return false;
    }

    private boolean existsThreadStillExecutingCriticalSection(int index) {
        for (int k = 0; k < participant; k++) {
            if (k != index) {
                if (level[k] != 0 && victim[level[k]] != k) {
                    return true;
                }
            }
        }
        return false;
    }

    public void unlock(String name, int index) {
//        System.out.println("Deblocare de catre " + name);
        isLocked = false;
        if (level.length > 0) {
            level[index] = 0;
        }
    }

    public void umpleOala(int capacitate) {
        this.capacitate = capacitate;
    }

    public void scadeCapacitate(int index) {
        if (capacitate > 0) capacitate--;
        else try {
            throw new Exception("Ai mancat -1 portie salbatic + " + index);
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
