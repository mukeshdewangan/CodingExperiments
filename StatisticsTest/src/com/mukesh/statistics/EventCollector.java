package src.com.mukesh.statistics;

import java.util.Scanner;

/**
 * Command line application which takes event from command line and provide the min(), max(), variance() functions
 * <NOTE>It does not use multithreading<NOTE/>
 * Multithreading has been handled in Unit test cases.
 */
public class EventCollector {
    public static void main(String[] args) {
        EventCollector program = new EventCollector();
        program.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        Statistics statistics = new OptimalStatistics();
        char option;

        do {
            displayMenu();
            option = scanner.next().charAt(0);

            switch (option) {
                case 'A':
                case 'a':
                    System.out.println("Enter event number:");
                    int num = scanner.nextInt();
                    statistics.event(num);
                    break;
                case 'B':
                case 'b':
                    System.out.println("Min: " + statistics.min());
                    break;
                case 'C':
                case 'c':
                    System.out.println("Max: " + statistics.max());
                    break;
                case 'D':
                case 'd':
                    System.out.println("Variance: " + statistics.variance());
                    break;
                case 'Q':
                case 'q':
                    option = 'Q';
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 'Q');

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\nOptions:");
        System.out.println("A. Input event");
        System.out.println("B. Get Min");
        System.out.println("C. Get Max");
        System.out.println("D. Get Variance");
        System.out.println("Q. Quit");
        System.out.print("Choose an option: ");
    }
}
