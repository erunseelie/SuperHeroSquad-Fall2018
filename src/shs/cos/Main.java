package shs.cos;

import shs.cos.gui.GUIGame;
import shs.cos.gui.GUILogin;

import java.io.File;

public class Main {
    /**
     * This is what we call to run the game.
     * The loading process is then tossed off to GUILogin to handle logging into the game properly.
     */
    public static void main(String[] args) {

//        GUIGame guiGame = new GUIGame();
        GUILogin.main(null);

    }

    /**
     * GUILogin calls this method after successfully logging in.
     */
    public static void loadFinalize() {
        loadGameData();

        new GUIGame();
    }

    /**
     * Handles loading all our custom game data from the text files into memory.
     */
    private static void loadGameData() {
        String dataDirectory = "res/data/";
        String dataExtension = ".txt";

        Room.readRoomFile(new File(dataDirectory + "Rooms" + dataExtension));
    }
}
