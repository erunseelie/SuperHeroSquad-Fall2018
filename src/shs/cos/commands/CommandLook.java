package shs.cos.commands;

import shs.cos.rooms.Room;

public class CommandLook extends Command {

    public static String runCommand() {
        return Room.getMap().get(Room.getCurrentRoomKey()).getDesc();
//        return "hello";
    }

}