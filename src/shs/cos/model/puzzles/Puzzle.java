package shs.cos.model.puzzles;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static shs.cos.model.utils.io.IO.separator;

public abstract class Puzzle {
    private static ArrayList<Puzzle> listPuzzles;
    private static boolean isAttempting = false;
    private static Puzzle currentPuzzle = null;
    private String
            ID,
            location,
            description,
            clue,
            answer,
            boon;
    private int counter;

    public static boolean loadPuzzleFile(File f) {
        listPuzzles = new ArrayList<>();
        Scanner fileIn;
        try {
            fileIn = new Scanner(f);
        } catch (Exception e) {
            return false;
        }

        while (fileIn.hasNextLine()) {
            String[] nextLine = fileIn.nextLine().split(separator);
            OUTER_LOOP:
            if (nextLine[0].equals("ID")) {
                Puzzle newPuzzle = null;
                if (nextLine[1].contains("SAFE")) newPuzzle = new PuzzleSafe();
                else if (nextLine[1].contains("BOOK")) newPuzzle = new PuzzleRiddle();
                else if (nextLine[1].contains("SEQUENCE")) newPuzzle = new PuzzleSequence();
                else return false;
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
                        newPuzzle.counter = 0;
                        listPuzzles.add(newPuzzle);
                        break OUTER_LOOP;
                    }
                }
            }
        }
        fileIn.close();
        return true;
    }

    public static ArrayList<Puzzle> getPuzzles() {
        return listPuzzles;
    }

    public static boolean isAttempting() {
        return isAttempting;
    }

    public static void setAttempting(boolean attempting) {
        isAttempting = attempting;
    }

    public static Puzzle getCurrentPuzzle() {
        return currentPuzzle;
    }

    public static void setCurrentPuzzle(Puzzle currentPuzzle) {
        Puzzle.currentPuzzle = currentPuzzle;
    }

    public static void clearPuzzle(Puzzle p) {
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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public abstract boolean attempt(String input);

}