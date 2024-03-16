package src.com.mukesh.statistics;

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
     */
    int max();
    /**
     * Mean of all the events consumed
     * @return Mean of all the events consumed
     */
    float mean();
    /**
     * Variance of all the events consumed
     * @return Variance of all the events consumed
     */
    float variance();
}
