package com.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * It identifies how many singles and doubles can be played in the given
 * duration with arrived players at different times. Rules: If at least 2
 * players are available, they can play Singles Rules: If at least 4 players are
 * available, they can play Doubles
 *
 * @author nmorla
 */
public class PingPongScheduler {

    public static final int GAMESTARTTIME = 9;
    public static final int GAMEENDTIME = 18;

    static int singlesCount = 0;
    static int doublesCount = 0;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter Number of Players :--->");

        int numberOfPlayers = input.nextInt();
        int[][] playersTimings = new int[numberOfPlayers][2];

        /**
         * Input Receiving and Validation Set.
         */
        for (int i = 0; i < numberOfPlayers; i++) {
            boolean validInputs = true;
            System.out.println("Please Enter Starting Hr of :" + (i + 1) + " : st Player -->");
            int startingHr = input.nextInt();
            System.out.println("Please Enter Ending Hr of :" + (i + 1) + " : st Player -->");
            int endingHr = input.nextInt();

            if (startingHr < GAMESTARTTIME || endingHr > GAMEENDTIME) {
                System.out.println(" *********   Game Available time Between :" + GAMESTARTTIME + ": And :" + GAMEENDTIME + " *******");
                validInputs = false;
            }

            if (startingHr >= endingHr) {
                System.out.println("  Starting Hr should be lesss than Ending Hr. ");
                validInputs = false;
            }
            if (!validInputs) {
                i = i - 1;
                continue;
            }
            playersTimings[i][0] = startingHr;
            playersTimings[i][1] = endingHr;
        }
        Arrays.asList(playersTimings).forEach(val -> System.out.print("{" + val[0] + " -- " + val[1] + "}, "));
        incrementHoursV4(playersTimings);

        /**
         * Test Data.
         */
//        playersTimings = new int[][]{{9, 10}, {9, 12}};// singles : 1 , doubles : 0
//        playersTimings = new int[][]{{9, 10}, {9, 12}, {10, 18}, {13, 15}};// singles : 5 , doubles : 0
//        playersTimings = new int[][]{{9, 11}, {10, 14}, {11, 18}, {12, 14}, {13, 15}, {14, 18}, {15, 18}};// singles : 7 , doubles : 1
//        playersTimings = new int[][]{{10, 13}, {10, 14}, {9, 10}, {9, 12}, {9, 13}, {14, 15}, {14, 18}, {15, 18}, {16, 18}};// singles : 6 , doubles : 2
//        playersTimings = new int[][]{{9, 13}, {9, 14}, {9, 15}, {10, 15}, {12, 13}, {14, 16}, {15, 17}, {16, 18}};// singles : 5 , doubles : 3
//        long startTime = System.nanoTime();
//        incrementHoursV4(playersTimings);
//        long endTime = System.nanoTime();
//        System.out.println("================= Time taken for V4 Model:: " + (endTime - startTime) / 1000);
    }

    private static void incrementHoursV4(int[][] playerTimings) {
        int singles = 0;
        int doubles = 0;
        int[] counts = new int[GAMEENDTIME - GAMESTARTTIME];
        for (int i = 0; i < playerTimings.length; i++) {
            int startTime = playerTimings[i][0];
            int endTime = playerTimings[i][1];
            while (startTime < endTime) {
                counts[startTime - GAMESTARTTIME] = counts[startTime - GAMESTARTTIME] + 1;
                ++startTime;
            }
        }
        for (int value : counts) {
            if (value >= 4) {
                doubles += 1;
            } else if (value >= 2) {
                singles += 1;
            }
        }
        System.out.println("Singles:" + singles + ":Doubles:" + doubles);
    }
}
