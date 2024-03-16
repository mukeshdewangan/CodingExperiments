package src.com.mukesh.statistics;


/**
 * Few improvements -
 * 1. min(), max, mean() and variance() will throw IllegalStateException if no events have been consumed
 * 2. Interface should be complete so adding functionality later does not become a problem,
 * so I would like to add two more functions
 *      2.a) float standardDeviation();
 *      2.b) void resetData();
 */
public interface Statistics {
    /**
     * The only method which takes events as input.
     * @param n event value
     */
    void event(int n);
    /**
     * Minimum of all the events consumed
     * @return Minimum of all the events consumed
     */
    int min();
    /**
     * Maximum of all the events consumed
     * @return Maximum of all the events consumed
     * @throws IllegalStateException if no events have been consumed
     *
     */
    int max();
    /**
     * Mean of all the events consumed
     * @return Mean of all the events consumed
     * @throws IllegalStateException if no events have been consumed
     */
    float mean();
    /**
     * Variance of all the events consumed
     * @return Variance of all the events consumed
     * @throws IllegalStateException if no events have been consumed
     */
    float variance();
}
