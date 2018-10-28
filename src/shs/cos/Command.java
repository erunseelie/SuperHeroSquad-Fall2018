package shs.cos;

import shs.cos.gui.GUIGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

// Gets the command entered.
public class Command implements ActionListener {
    private GUIGame gui;
    private String action;

    public Command(GUIGame gui, String action) {
        this.gui = gui;
        this.action = action;
    }

    // The default ActionEvent fired when the enter key is pressed.
    public void actionPerformed(ActionEvent e) {
        String s = null;
        if (action != null) {
            if (action.equals("look")) s = commandLook();
            else if (action.equals("exit")) s = commandExit();
            else if (action.equals("list")) s = commandList();
            else s = "You don't know how to do that.";
            gui.logAdd(s);

        } else {

            // textual commands
            String[] command = gui.getInput().split(" ");
            int size = command.length;

            String verb = command[0];
            String object = "";
            if (size > 1) object = command[1];
            String amount = "";
            if (size > 2) amount = command[2];

            // TODO: add full command parsing
            if (verb.equals("look")) {
                s = (commandLook());
            } else if (verb.equals("exit")) {
                s = (commandExit());
            } else if (verb.equals("go")) {
                ArrayList<String> rooms = Room.getMap().get(Room.getCurrentRoomKey()).getConnections();
                if (rooms.contains(object.toUpperCase())) {
                    Room.setCurrentRoom(object.toUpperCase());
                    s = ("You mosey through the dust. You have arrived at your destination...");
                } else s = ("You can't go there.");
            } else if (verb.equals("list")) {
                s = (commandList());
            } else {
                s = ("You're unable to do that.");
            }

            gui.logAdd(s);
        }
    }

    public String commandLook() {
        return Room.getMap().get(Room.getCurrentRoomKey()).getDesc();
    }

    public String commandExit() {
        Room.setCurrentRoom("B0R0");
        return "You have returned to the street.";
    }

    public String commandList() {
        ArrayList<String> rooms = Room.getMap().get(Room.getCurrentRoomKey()).getConnections();
        String s = "";

        TreeMap<String, Room> map = Room.getMap();
        for (String r : rooms) {
            Room room = map.get(r);
            s += room.getRoomName() + ", ";
        }

        return s;
    }

}
