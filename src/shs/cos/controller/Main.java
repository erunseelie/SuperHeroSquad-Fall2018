package shs.cos.controller;

import shs.cos.model.items.Item;
import shs.cos.model.Room;
import shs.cos.model.entities.Monster;
import shs.cos.model.entities.Player;
import shs.cos.model.puzzles.Puzzle;
import shs.cos.view.gui.GUIGame;
import shs.cos.view.gui.GUILogin;

import java.io.File;

public class Main {
    /**
     * This is what we call to run the game.
     * The loading process is then tossed off to GUILogin to handle logging into the game properly.
     */
    public static void main(String[] args) {
//        GUILogin.main(null);
        player = new Player();
        loadFinalize();
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

        Room.readRoomFile(new File(selectFile("Rooms")));
        Monster.readMonsterFile(new File(selectFile("Monsters")));
        Item.readItemFile(new File(selectFile("Items")));
        if (!(Puzzle.loadPuzzleFile(new File(selectFile("Puzzles")))))
            GUILogin.displayWarning("The following data file was not found, or contains invalid data: PUZZLES.TXT");
    }

    private static String selectFile(String s) {
        String dataDirectory = "res/data/";
        String dataExtension = ".txt";
        return dataDirectory + s + dataExtension;
    }
}
