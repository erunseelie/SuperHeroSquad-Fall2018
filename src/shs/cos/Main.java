package shs.cos;

import shs.cos.gui.GUIGame;
import shs.cos.rooms.Room;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Room.readRoomFile(new File("res/testRoomData.txt"));


        new GUIGame();
    }
}
