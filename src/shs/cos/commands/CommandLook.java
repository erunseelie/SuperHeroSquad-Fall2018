package shs.cos.commands;

import shs.cos.rooms.Room;

public class CommandLook {

    public static String runCommand() {
        return Room.getMap().get(Room.getCurrentRoomKey()).getDesc();
    }

}