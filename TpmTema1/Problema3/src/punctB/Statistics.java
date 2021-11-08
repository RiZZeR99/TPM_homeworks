package punctB;

public class Statistics {
    int index;
    double maximum;
    double minimum;
    double average;

    @Override
    public String toString() {
        return "Thread " + index + " has maximum time access " + maximum + " minimum time access " + minimum + " average time access " + average;
    }
}
