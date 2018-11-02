package shs.cos;

import shs.cos.gui.GUIGame;
import shs.cos.gui.GUILogin;

import java.io.File;
import java.util.TreeMap;

public class Main {
    private static TreeMap<String, String> mapGameSaveData;
    private static GUILogin guiLogin;

    /**
     * This is what we call to run the game.
     * The loading process is then tossed off to GUILogin to handle logging into the game properly.
     */
    public static void main(String[] args) {

//        GUIGame guiGame = new GUIGame();
        guiLogin = new GUILogin(); guiLogin.main(null);

    }

    /**
     * GUILogin calls this method after successfully logging in.
     */
    public static void loadFinalize() {
        loadGameData();

        new GUIGame();
    }

    private static String dataDirectory = "res/data/", dataExtension = ".txt";

    /**
     * Handles loading all our custom game data from the text files into memory.
     */
    private static void loadGameData() {
        Room.readRoomFile(new File(dataDirectory + "Rooms" + dataExtension));
    }

    /**
     * Handles saving the current game data from memory into a text file.
     */
    private static void saveUserData() {
        guiLogin.mapSaveData = mapGameSaveData;
        guiLogin.saveToFile(guiLogin.currentUser);
    }
    public static void updateUserData(TreeMap<String, String> map) {
        mapGameSaveData = map;
    }
}
