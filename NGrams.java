///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           This program finds the number of n-grams in a file
// Course:          CS 200, Fall, 2022
//
// Author:          Garrett Hetchler
// Email:           ghetchler@wisc.edu
// Lecturer's Name: Jim
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// I did this by myself
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class with methods to read files, create n-grams, and print the result.
 *
 * @author Victor Suciu
 * @author ADD YOUR NAME WHEN YOU CONTRIBUTE
 */
public class NGrams {

    /**
     * Reads the space-delimited words from a file. If the file does not exist, displays the message
     * "File [path] not found" and returns an empty ArrayList.
     * <p>
     * For example, if path = /Documents/ExampleFolder/text_document.txt and text_document.txt
     * contains the following:
     * <p>
     * one two three four five
     * <p>
     * You would return the ArrayList ["one", "two", "three", "four", "five"]
     * <p>
     * If that file did not actually exist, it would print the following: "File
     * /Documents/ExampleFolder/text_document.txt not found"
     * <p>
     * and return an empty ArrayList
     *
     * @param path - a file path to a text file
     * @return an ArrayList of words
     */
    public static ArrayList<String> readWords(String path) {
        File nGram = new File(path);
        ArrayList<String> readFile = new ArrayList<String>();
        try {
            Scanner scnr = new Scanner(nGram);
            while (scnr.hasNext()) {
                readFile.add(scnr.next());
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + path + " not found");
        }
        return readFile;
    }

    /**
     * Extracts all n-grams from a sequence of words
     * <p>
     * For example, if the ArrayList of words is ["one", "two" "three", "four", "five"] with n=3,
     * the ArrayList of n-grams should have the format [["one", "two" "three"], ["two" "three",
     * "four"], ["three", "four", "five"]]
     *
     * @param words - a list of words
     * @param n     - the n-gram size
     * @return an ArrayList of n-grams, where each n-gram is itself an ArrayList
     */
    public static ArrayList<ArrayList<String>> getNGrams(ArrayList<String> words, int n) {
        ArrayList<ArrayList<String>> nGrams = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < words.size() - n + 1; i++) {
            ArrayList<String> nGram = new ArrayList<String>();
            for (int j = 0; j < n; j++) {
                nGram.add(words.get(i + j));
            }
            nGrams.add(nGram);
        }
        return nGrams;
    }

    /**
     * Prints an ArrayList of n-grams vertically, so each n-gram has its own line.
     * <p>
     * Use only one for-loop inside printNGrams. You should not use nested for-loops. Take advantage
     * of the fact that printing an ArrayList results in human-readable output automatically.
     * <p>
     * For example, if ngrams = [["one", "two", "three"], ["two", "three", "four"], ["three",
     * "four", "five"]] You would print the contents of ngrams in the following format:
     * <p>
     * [one, two, three] [two, three, four] [three, four, five]
     *
     * @param ngrams - The list of n-grams
     */
    public static void printNGrams(ArrayList<ArrayList<String>> ngrams) {
        for (ArrayList<String> nGram : ngrams) {
            System.out.println(nGram);
        }
    }

    /**
     * Reads the file path and value of n from the console, then calls the methods above in
     * sequence
     * <p>
     * Step 1. Read user input. This will be given in the form "[file path] [n]" in a single line,
     * where the two values are separated by a space
     * <p>
     * Step 2. Get the ArrayList of words from the file using readWords()
     * <p>
     * Step 3. Get the ArrayList of n-grams from the ArrayList of words using getNGrams()
     * <p>
     * Step 4. Print the ArrayList of n-grams using printNGrams()
     *
     * @param args unused
     */
    public static void main(String[] args) {

        // Do not edit anything in the main method.
        // only edit the methods readWords, getNGrams, and printNGrams.

        Scanner inputReader = new Scanner(System.in);

        String path = inputReader.next();
        int n = inputReader.nextInt();

        ArrayList<String> words = readWords(path);
        ArrayList<ArrayList<String>> ngrams = getNGrams(words, n);
        printNGrams(ngrams);
    }
}
