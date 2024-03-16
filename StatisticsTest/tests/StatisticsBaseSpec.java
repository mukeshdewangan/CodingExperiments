
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.com.mukesh.statistics.OptimalStatistics;
import org.junit.jupiter.api.Assertions;
import src.com.mukesh.statistics.Statistics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class StatisticsBaseSpec {
    protected Statistics basicStatistics;
    @BeforeEach
    public void setUp(){
        basicStatistics = new OptimalStatistics();
    }

    protected void insertSameEvents(){
        basicStatistics.event(40);
        basicStatistics.event(40);
        basicStatistics.event(40);
        basicStatistics.event(40);
        basicStatistics.event(40);
    }

    protected void insertDiscreteEvents(){
        basicStatistics.event(30);
        basicStatistics.event(40);
        basicStatistics.event(50);
        basicStatistics.event(35);
        basicStatistics.event(45);
    }

    protected void insertNegativeEvents(){
        basicStatistics.event(-30);
        basicStatistics.event(-30);
        basicStatistics.event(-30);
        basicStatistics.event(-30);
        basicStatistics.event(-30);
    }


    @Test
    public void verifyMinMaxMeanVariance_forSameNumbers(){
        insertSameEvents();
        Assertions.assertEquals(basicStatistics.min(),40);
        Assertions.assertEquals(basicStatistics.max(),40);
        Assertions.assertEquals(basicStatistics.mean(),40);
        Assertions.assertEquals(basicStatistics.variance(),0);
    }

    @Test
    public void verifyMinMaxMeanVariance_forDifferentNumbers(){
        insertDiscreteEvents();
        Assertions.assertEquals(basicStatistics.min(),30);
        Assertions.assertEquals(basicStatistics.max(),50);
        Assertions.assertEquals(basicStatistics.mean(),40);
        Assertions.assertEquals(basicStatistics.variance(),50);
    }

    @Test
    public void verifyMinMaxMeanVariance_forSameNumbers_inMultithreadedEnv(){
        int callerNumber = 10;

        int threadCount = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < callerNumber; i++) {
            executor.submit(this::insertSameEvents);
        }
        executor.shutdown();

        while(!executor.isTerminated()){
            //System.out.println("not terminated, still waiting");
        }
        Assertions.assertEquals(basicStatistics.min(),40);
        Assertions.assertEquals(basicStatistics.max(),40);
        Assertions.assertEquals(basicStatistics.mean(),40);
        Assertions.assertEquals(basicStatistics.variance(),0);
    }

    @Test
    public void verifyMinMaxMeanVariance_forDifferentNumbers_inMultithreadedEnv(){
        int callerNumber = 10;

        int threadCount = 8;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < callerNumber; i++) {
            executor.submit(this::insertDiscreteEvents);
        }
        executor.shutdown();

        while(!executor.isTerminated()){
            //System.out.println("not terminated, still waiting");
        }
        Assertions.assertEquals(basicStatistics.min(),30);
        Assertions.assertEquals(basicStatistics.max(),50);
        Assertions.assertEquals(basicStatistics.mean(),40);
        Assertions.assertEquals(basicStatistics.variance(),50);
    }

    @Test
    public void verifyMinMaxMeanVariance_forDifferentAndSameNumbers_inMultithreadedEnv(){
        int callerNumber = 10;

        int threadCount = 8;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < callerNumber; i++) {
            executor.submit(()->{ insertDiscreteEvents(); insertSameEvents();});
        }
        executor.shutdown();

        while(!executor.isTerminated()){
            //System.out.println("not terminated, still waiting");
        }
        Assertions.assertEquals(basicStatistics.min(),30);
        Assertions.assertEquals(basicStatistics.max(),50);
        Assertions.assertEquals(basicStatistics.mean(),40);
        Assertions.assertEquals(basicStatistics.variance(),25);
    }

    @Test
    public void verifyMinMaxMeanVariance_forPositiveAndNegativeNumbers_inMultithreadedEnv(){
        int callerNumber = 10;

        int threadCount = 8;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < callerNumber; i++) {
            executor.submit(()->{ insertNegativeEvents();insertSameEvents();});
        }
        executor.shutdown();

        while(!executor.isTerminated()){
            //System.out.println("not terminated, still waiting");
        }
        Assertions.assertEquals(basicStatistics.min(),-30);
        Assertions.assertEquals(basicStatistics.max(),40);
        Assertions.assertEquals(basicStatistics.mean(),5);
        Assertions.assertEquals(basicStatistics.variance(),1225);
    }
}
