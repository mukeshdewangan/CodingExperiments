package src.com.mukesh.statistics;


/**
 * It is the abstract class to keep basic variables and functions used in concrete implementation of
 * Statistics interface.
 * It provides variable like sum, totalElements, mean, variance etc and also validateInput function
 */

public abstract class BaseStatistics implements Statistics{
    protected final Object lock = new Object();
    protected long sum;
    protected int totalElements;
    protected float mean;
    protected float variance;
    protected int currentMin = Integer.MAX_VALUE;
    protected int currentMax = Integer.MIN_VALUE;
    protected void validateCall(){
        synchronized (lock) {
            if (totalElements == 0) {
                throw new IllegalStateException("Mean is undefined as there are no events yet.");
            }
        }
    }

    protected void updateInternals(int num){
        totalElements++;
        sum += num;
        if (num > currentMax) {
            currentMax = num;
        }
        if (num < currentMin) {
            currentMin = num;
        }
        mean = (float) sum / totalElements;
    }

}
