///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           this is a game where there is a random number and the user has to guess the
// random number.
// Course:          CS 200, Fall, 2022
//
// Author:          Garrett Hetchler
// Email:           ghetchler@wisc.edu
// Lecturer's Name: Jim
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// did this by myself
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Random;
import java.util.Scanner;

/**
 * This program takes a random number and then has the user input guesses until the random number
 * is guessed.  The program then lets you know when you have guessed the number and in how many
 * guesses it took you to get it
 */
public class GuessTheNumber {

    /**
     * This is a guess the number game that uses the method above for user input.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        final int LOW_BOUND = 1;
        final int HIGH_BOUND = 100;
        int hiddenNum = rand.nextInt(HIGH_BOUND - LOW_BOUND + 1) + LOW_BOUND;
        int guessCount = 0;
        int guessedNum;

        do {
            guessedNum = isGuessCorrectValue(input, "Enter a number between " +
                    LOW_BOUND + " and " + HIGH_BOUND + ": ", LOW_BOUND, HIGH_BOUND);
            guessCount++;
            if (guessedNum >= LOW_BOUND && guessedNum <= HIGH_BOUND) {
                if (guessedNum < hiddenNum) {
                    System.out.println("This guess is too low.");
                } else if (guessedNum > hiddenNum) {
                    System.out.println("This guess is too high.");
                } else {
                    System.out.println("This guess is correct.");
                }
            } else {
                System.out.println("This guess is invalid.");
            }
        } while (guessedNum != hiddenNum);

        System.out.print("You won in " + guessCount + " attempts!");
        input.close();
    }

    /**
     * Method that tests to make sure the guess value is within the test parameters
     *
     * @param userGuessValue     this is the user input guess value
     * @param promptForUserInput this string that asks for user to input a value between lower and
     *                           upper bounds
     * @param lowerBound         this is the minimum value that the hidden number could be
     * @param upperBound         this is the maximum value that the hidden number could be
     * @return returns -1 if guess value is null, -2 if our lower bounds is less than 0, -3 if our
     * lower bound is larger than our upper bound, -4 if our guess value is greater than our upper
     * bound, -5 if our guess value is less than out lower bound
     */
    public static int isGuessCorrectValue(Scanner userGuessValue, String promptForUserInput,
                                          int lowerBound, int upperBound) {
        if (userGuessValue == null) {
            return -1;
        } else if (lowerBound < 0) {
            return -2;
        } else if (lowerBound > upperBound) {
            return -3;
        }
        int userInputValue = -6;
        System.out.println(promptForUserInput);
        if (userGuessValue.hasNextInt()) {
            userInputValue = userGuessValue.nextInt();
            userGuessValue.nextLine(); //read and ignore the rest of the line
            if (userInputValue > upperBound) {
                return -4;
            } else if (userInputValue < lowerBound) {
                return -5;
            }
        } else {
            userGuessValue.nextLine(); //read and ignore the token that isn't an integer
        }
        return userInputValue;
    }
}
