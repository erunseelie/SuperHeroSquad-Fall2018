package shs.cos.commands;

import shs.cos.rooms.Room;

public class CommandLook extends Command {

    public static String runCommand() {
        String roomKey = Room.getCurrentRoomKey();
        Room roomCurrent = Room.getMap().get(roomKey);
        return roomCurrent.getDesc();
    }

}