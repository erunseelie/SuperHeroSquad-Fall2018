package shs.cos.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class IO {
    private Scanner input;
    // The separating characters for a key-value pair in our files are '::'.
    // Example: "C1::You enter a room that smells faintly of old shoes."
    public static final String separator = "::";

    public TreeMap<String, String> readFile(File f) {
        // Try to read the supplied file.
        try { input = new Scanner(f); }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        // TODO: Display error to user if file doesn't exist. Break?

        // If read successful, copy it into memory.
        TreeMap<String, String> map = new TreeMap<>();
        while (input.hasNextLine()) {
            String[] nextLine = input.nextLine().split(separator);
            map.put(nextLine[0], nextLine[1]);
        }
        input.close();
        return map;
    }

    public void writeFile(TreeMap<String, String> map, File f) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } // TODO: Properly display error.

        // For each key-value pair in the TreeMap, print it to a file in the specified format.
        // The lambda expression beneath wants a 'final' variable for its operations... this seems to work.
        PrintWriter finalPw = pw;
        if (!(finalPw == null)) {
            map.forEach((k, v) -> finalPw.write(k + separator + v + "\n"));
            finalPw.close();
        }
        if (!(pw == null)) pw.close();
    }
}
