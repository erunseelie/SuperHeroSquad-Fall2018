package shs.cos;

import shs.cos.entities.Monster;
import shs.cos.entities.Player;
import shs.cos.gui.GUIGame;
import shs.cos.gui.GUILogin;

import java.io.File;

public class Main {
    /**
     * This is what we call to run the game.
     * The loading process is then tossed off to GUILogin to handle logging into the game properly.
     */
    public static void main(String[] args) {

        // Transpose the comments on the following method calls to launch the game
        // without requiring a login. Useful for development purposes.
        GUILogin.main(null);
//        loadFinalize();

    }

    public static Player player;

    /**
     * GUILogin calls this method after successfully logging in.
     */
    public static void loadFinalize() {
        loadGameData();

        new GUIGame();
    }

    /**
     * Handles loading all our custom game data from the text files into memory.
     * Add to this method as necessary as more data is created.
     */
    private static void loadGameData() {
        String dataDirectory = "res/data/";
        String dataExtension = ".txt";

        Room.readRoomFile(new File(dataDirectory + "Rooms" + dataExtension));
        Monster.readMonsterFile(new File(dataDirectory + "Monsters" + dataExtension));
    }
}
