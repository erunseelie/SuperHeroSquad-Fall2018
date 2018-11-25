package shs.cos.controller;

import shs.cos.model.Room;
import shs.cos.model.entities.Player;
import shs.cos.model.items.Item;
import shs.cos.view.gui.GUILogin;
import shs.cos.model.utils.io.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

import static shs.cos.controller.Main.player;

public class SessionManager {

    private static TreeMap<String, String> mapSaveData;
    public static String currentUser;
    private static String currentPassword;

    private final static String
            saveDirectory = System.getProperty("user.home") + "/Games/CitadelOfStorms/saves/",
            saveExtension = ".txt";
    public final static String idPassword = "pw";
    private final static String idRoom = "rm";
    private final static String idHealth = "hp";

    /**
     * TODO: Add to this method as necessary in order to save data to the current file.
     */
    public static void updateFile() {
        updatePlayerData(idRoom, Room.getCurrentRoomKey());
        updatePlayerData(idHealth, String.valueOf(player.getHealth()));
        if (!(Item.getPlayerItems().isEmpty())) {
            for (Item i : Item.getPlayerItems()) {
                if (i != null) updatePlayerData("ITEM_" + i.getItemID(), "1");
            }
        }
        saveToFile(mapSaveData, currentUser);
    }

    /**
     * TODO: Add to this method as necessary in order to load data into memory when a file has been successfully read.
     */
    private static void loadPlayerData() {
        player = new Player();
        Room.setCurrentRoom(mapSaveData.get(idRoom));
        if (mapSaveData.get(idHealth) != null)
            player.applyDamage(Player.healthDefault - Integer.parseInt(mapSaveData.get(idHealth)));
        for (Map.Entry<String, String> entry : mapSaveData.entrySet()) {
            String key = entry.getKey();
            if (key.length() >= 5 && key.substring(0, 5).equals("ITEM_")) {
                Item i = Item.getItemIDList().get(key.substring(5));
                if (i != null) Item.addPlayerItem(i);
                // TODO: remove item from the world
            }
        }
    }

    /**
     * Copies all the data from a TreeMap to the designated file.
     *
     * @param username the name of the file to write to.
     */
    private static void saveToFile(TreeMap<String, String> map, String username) {
        File dir = new File(saveDirectory);
        dir.mkdirs();

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
        player = new Player();
    }

    public static void createNewSaveFile(String username, String password) {
        currentUser = username;
        currentPassword = password;
        updateFile();
    }

    /**
     * Attempts to load the save file associated with the given username.
     * If the file is found and the password is correct, the save file's data
     * is copied into this class' TreeMap.
     *
     * @param username Username
     * @param password Password
     * @return Whether the login attempt was successful.
     */
    public static boolean attemptLogIn(String username, String password) {
        mapSaveData = new TreeMap<>();

        // attempt to load the file
        Scanner fileIn;
        try {
            fileIn = new Scanner(new File(saveDirectory + username + saveExtension));
        } catch (FileNotFoundException e) {
            GUILogin.displayWarning("That user doesn't exist.\nTry again, or start a new game.");
            return false;
        }

        // attempt successful, read it in
        while (fileIn.hasNextLine()) {
            String[] nextLine = fileIn.nextLine().split(IO.separator);
            String key = nextLine[0];
            String val = nextLine[1];
            // TODO: add any "special" read functionality here
            mapSaveData.put(key, val);
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
