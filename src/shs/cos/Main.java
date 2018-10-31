package shs.cos;

import shs.cos.gui.GUILogin;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        Room.readRoomFile(new File("res/data/Rooms.txt"));

        GUILogin windowLogin = new GUILogin();
        windowLogin.main(null);

    }
}
