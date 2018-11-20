/**
 * Matthew Stiller
 */

package shs.cos.model.puzzles;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import static shs.cos.model.utils.io.IO.separator;

public class Puzzle {
    private String
        ID,
        location,
        description,
        clue,
        answer,
        boon;

    private static ArrayList<Puzzle> listPuzzles;
    private static boolean isAttempting = false;
    private static Puzzle currentPuzzle = null;

    public static boolean loadPuzzleFile(File f) {
        listPuzzles = new ArrayList<>();
        Scanner fileIn;
        try { fileIn = new Scanner(f); }
        catch (Exception e) { return false; }

        while (fileIn.hasNextLine()) {
            String[] nextLine = fileIn.nextLine().split(separator);
            OUTER_LOOP: if (nextLine[0].equals("ID")) {
                Puzzle newPuzzle = new Puzzle();
                newPuzzle.ID = nextLine[1];

                while (fileIn.hasNextLine()) {
                    nextLine = fileIn.nextLine().split(separator);

                    if (nextLine[0].equals("location")) {
                        newPuzzle.location = nextLine[1];
                    }

                    if (nextLine[0].equals("desc")) {
                        newPuzzle.description = nextLine[1];
                    }

                    if (nextLine[0].equals("clue")) {
                        newPuzzle.clue = (nextLine[1]);
                    }

                    if (nextLine[0].equals("answer")) {
                        newPuzzle.answer = (nextLine[1]);
                    }

                    if (nextLine[0].equals("boon")) {
                        newPuzzle.boon = (nextLine[1]);
                    }

                    if (nextLine[0].equals("ID") || nextLine[0].equals("end")) {
                        listPuzzles.add(newPuzzle);
                        break OUTER_LOOP;
                    }
                }
            }
        }
        return true;
    }

    public static ArrayList<Puzzle> getPuzzles() {
        return listPuzzles;
    }

    public static void solvePuzzle(Puzzle p) {
        listPuzzles.remove(p);
    }

    public String getID() {
        return ID;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getClue() {
        return clue;
    }

    public String getAnswer() {
        return answer;
    }

    public String getBoon() {
        return boon;
    }

    public static void setAttempting(boolean attempting) {
        isAttempting = attempting;
    }

    public static boolean isAttempting() {
        return isAttempting;
    }

    public static Puzzle getCurrentPuzzle() {
        return currentPuzzle;
    }

    public static void setCurrentPuzzle(Puzzle currentPuzzle) {
        Puzzle.currentPuzzle = currentPuzzle;
    }
}