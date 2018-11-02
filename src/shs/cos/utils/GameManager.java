package shs.cos.utils;

import shs.cos.Room;
import shs.cos.gui.GUILogin;
import shs.cos.utils.io.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class GameManager {

    private static TreeMap<String, String> mapSaveData;
    public static String currentUser;
    private static String currentPassword;

    private static String saveDirectory = "res/saves/", saveExtension = ".txt";
    public static String idPassword = "pw";
    public static String idRoom = "rm";

    /**
     * Add to this method as necessary in order to save data to the current file.
     */
    public static void updateFile() {
        updatePlayerData(idRoom, Room.getCurrentRoomKey());
        saveToFile(mapSaveData, currentUser);
    }

    /**
     * Add to this method as necessary in order to load data into memory when
     * a file has been successfully read.
     */
    public static void loadPlayerData() {
        Room.setCurrentRoom(mapSaveData.get(idRoom));
    }

    /**
     * Copies all the data from a TreeMap to the designated file.
     * @param username the name of the file to write to.
     */
    private static void saveToFile(TreeMap<String, String> map, String username) {
        File f = new File(saveDirectory + username + saveExtension);
        PrintWriter fileOut;
        try {
            fileOut = new PrintWriter(f);
        } catch (FileNotFoundException e) {
            return;
        }
        map.forEach((k, v) -> fileOut.println(k + IO.separator + v));
        fileOut.close();
    }

    public static void updatePlayerData(String k, String v) {
        mapSaveData.put(k, v);
    }

    public static void clearPlayerData() {
        mapSaveData = new TreeMap<>();
        updatePlayerData(idPassword, currentPassword);
//        updateFile();
    }

    public static void createNewSaveFile(String username) {
        currentUser = username;
        updateFile();
    }

    /**
     * Attempts to load the save file associated with the given username.
     * If the file is found and the password is correct, the save file's data
     * is copied into this class' TreeMap.
     * @param username Username
     * @param password Password
     * @return Whether the login attempt was successful.
     */
    public static boolean attemptLogIn(String username, String password) {
        mapSaveData = new TreeMap<>();

        // attempt to load the file
        Scanner fileIn;
        try {
            fileIn = new Scanner(new File( saveDirectory + username + saveExtension));
        } catch (FileNotFoundException e) {
            GUILogin.displayWarning("That user doesn't exist.\nTry again, or start a new game.");
            return false;
        }

        // attempt successful, read it in
        while (fileIn.hasNextLine()) {
            String[] nextLine = fileIn.nextLine().split(IO.separator);
            mapSaveData.put(nextLine[0], nextLine[1]);
        }
        fileIn.close();



        // check that passwords match
        if (password.equals(mapSaveData.get(idPassword))) {
            currentUser = username;
            currentPassword = password;
            loadPlayerData();
            return true;
        } else {
            mapSaveData.clear();
            GUILogin.displayWarning("Your password was incorrect.\nTry again.");
            return false;
        }
    }
}
