package src.com.mukesh.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Variance, σ^2 will be calculated as σ^2 = (∑(xi - µ)^2)/N
 */

public class SubOptimalStatistics extends BaseStatistics {
    private final List<Integer> numbers = new ArrayList<>();
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
     *  σ^2 = (∑(xi - µ)^2)/N
     * @return calculated variance
     */
    @Override
    public float variance() {
        // We need to use synchronized block to handle any mutation of any dependent variants
        synchronized(lock) {
            long squaredSum = 0L;
            for (Integer num : numbers) {
                squaredSum += (long) ((long)(num - mean) * (num - mean));
            }
            variance = (float) squaredSum /totalElements;
        }
        return variance;
    }

    private void updateInternalVariables(int num){
        synchronized (lock) {
            updateInternals(num);
            numbers.add(num);
        }
    }
}
