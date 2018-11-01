package shs.cos;

import shs.cos.gui.GUIGame;
import shs.cos.gui.GUILogin;

import java.io.File;
import java.util.TreeMap;

public class Main {
    private TreeMap<String, String> mapGameSaveData;

    // This is what we call to run the game.
    // The loading process is then tossed off to GUILogin to handle logging into the game properly.
    public static void main(String[] args) {

        GUILogin windowLogin = new GUILogin();
        windowLogin.main(null);

    }

    // GUILogin calls this method after logging in has completed.
    public static void loadFinalize() {
        loadGameData();
        new GUIGame();
    }

    private static String dataDirectory = "res/data/", dataExtension = ".txt";

    private static void loadGameData() {
        Room.readRoomFile(new File(dataDirectory + "Rooms" + dataExtension));
    }
}
