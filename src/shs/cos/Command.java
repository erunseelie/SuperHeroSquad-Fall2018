package shs.cos;

import shs.cos.gui.GUIGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

// Gets the command entered.
public class Command implements ActionListener {
    private String action;

    TreeMap<String, Room> mapRooms = Room.getMap();

    public Command(String action) {
//        this.gui = gui;
        this.action = action;
    }

    // The default ActionEvent fired when the enter key is pressed for text input,
    // or when a button is clicked.
    public void actionPerformed(ActionEvent e) {
        String s = null;
        if (action != null) {
            if (action.equals("look")) s = commandLook();
            else if (action.equals("exit")) s = commandExit();
            else if (action.equals("list")) s = commandList();
            else s = "You don't know how to do that.";
            GUIGame.logAdd(s);
        } else {
            // textual commands
            String[] command = GUIGame.getInput().split(" ");
            int size = command.length;

            String verb = command[0];
            String object = "";
            if (size > 1) object = command[1];
//            String amount = "";
//            if (size > 2) amount = command[2];

            // TODO: add full command parsing
            if (verb.equals("look")) { s = (commandLook()); }
            else if (verb.equals("exit")) { s = (commandExit()); }
            else if (verb.equals("go")) {
                String location = object;
                for (int i = 2; i < size; i++) {
                    location += " " + command[i];
                }
                s = commandGo(location);
            }
            else if (verb.equals("list")) { s = (commandList()); }
            else { s = ("You're unable to do that."); }

            GUIGame.logAdd(s);
        }
    }

    private String commandLook() {
        return "LOOK: \n" + Room.getMap().get(Room.getCurrentRoomKey()).getDesc();
    }

    private String commandExit() {
        Room.setCurrentRoom("B0R0");
        return "EXIT: \n" + "You have returned to the street.";
    }

    private String commandList() {
        ArrayList<String> rooms = getRoomConnections();
        String s = "";

        for (String r : rooms) {
            Room room = mapRooms.get(r);
            s += room.getRoomName() + ", ";
        }

        return "LIST: \n" + s;
    }

    private String commandGo(String location) {
        ArrayList<String> rooms = getRoomConnections();
        String s = "";

        for (String r : rooms) {
            if (mapRooms.get(r).getRoomName().toLowerCase().equals(location)) {
                Room.setCurrentRoom(r);
                s = ("You mosey through the dust. You have arrived at your destination...");
                break;
            } else s = "That place doesn't seem to be nearby.";
        }
        return "GO: \n" + s;
    }

    private ArrayList<String> getRoomConnections() {
        return Room.getMap().get(Room.getCurrentRoomKey()).getConnections();
    }

}
