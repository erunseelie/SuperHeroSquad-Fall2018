package shs.cos.commands;

import shs.cos.rooms.Room;

public class CommandExit {

    public static String runCommand() {
        Room.setCurrentRoom("B0R0");
        return "You have returned to the street.";
    }
}
