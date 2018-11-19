package shs.cos.controller;

import shs.cos.model.items.Item;
import shs.cos.model.Room;
import shs.cos.model.entities.Monster;
import shs.cos.model.entities.Player;
import shs.cos.view.gui.GUIGame;
import shs.cos.view.gui.GUILogin;

import java.io.File;

public class Main {
    /**
     * This is what we call to run the game.
     * The loading process is then tossed off to GUILogin to handle logging into the game properly.
     */
    public static void main(String[] args) {

        GUILogin.main(null);

    }

    static GUIGame gui;

    public static Player player;

    /**
     * GUILogin calls this method after successfully logging in.
     */
    public static void loadFinalize() {
        loadGameData();

        gui = new GUIGame();
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
        Item.readItemFile(new File(dataDirectory + "Items" + dataExtension));
    }
}
