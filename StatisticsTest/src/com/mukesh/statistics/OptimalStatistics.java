package src.com.mukesh.statistics;

/**
 * Variance, σ^2 will be calculated with optimal mechanism as ∑xi^2 - µ^2
 */
public class OptimalStatistics extends BaseStatistics {
    protected long squaredSum;
    @Override
    public void event(int num) {
        updateInternalVariables(num);
    }

    @Override
    public int min() {
        validateCall();
        return currentMin;
    }

    @Override
    public int max() {
        validateCall();
        return currentMax;
    }

    @Override
    public float mean() {
        validateCall();
        return mean;
    }

    /**
     * This method uses the basic method to calculate the variance(σ^2) as below where xi is element and µ is mean
     * σ^2 = (∑(xi - µ)^2)/N
     * .......
     * σ^2 = ∑xi^2 - µ^2
     * @return - the calculated variance, σ^2
     */
    @Override
    public float variance() {
        synchronized(lock) {
            variance = (float)squaredSum/totalElements - mean * mean;
        }
        return variance;
    }

    private void updateInternalVariables(int num){
        // We need execute the operations of calculating min, max, mean and variance in synchronized block
        // to handle multiple threads in Critical section
        synchronized (lock) {
            updateInternals(num);
            squaredSum += (long)num * num;
            //System.out.println("inserting " + num);
        }
    }

}
