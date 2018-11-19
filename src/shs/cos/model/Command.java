package shs.cos.model;

import shs.cos.model.items.Item;
import shs.cos.view.gui.GUIGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

// Gets the command entered.
public class Command implements ActionListener {
    private GUIGame gui;
    private String action;

    public Command(GUIGame gui, String action) {
        this.gui = gui;
        this.action = action;
    }

    // The default ActionEvent fired when the enter key is pressed for text input,
    // or when a button is clicked.
    public void actionPerformed(ActionEvent e) {
        String s;
        if (action != null) {
            switch (action) {
                case "look":
                    s = commandLook();
                    break;
                case "look item":
                    s = commandLookItem();
                    break;
                case "exit":
                    s = commandExit();
                    break;
                case "list":
                    s = commandList();
                    break;
                default:
                    s = "You don't know how to do that.";
                    break;
            }
            gui.addLogText(s);
        } else {
            // textual commands
            String[] command = gui.getInput().split(" ");
            int size = command.length;

            String verb = command[0];
            String object = "";
            if (size > 1) object = command[1];
//            String amount = "";
//            if (size > 2) amount = command[2];

            // TODO: add full command parsing
            switch (verb) {
                case "look":
                    s = (commandLook());
                    break;
                case "items":
                    s = (commandLookItem());
                    break;
                case "exit":
                    s = (commandExit());
                    break;
                case "go":
                    StringBuilder location = object == null ? null : new StringBuilder(object);
                    for (int i = 2; i < size; i++) {
                        location = (location == null ? new StringBuilder("null") : location).append(" ").append(command[i]);
                    }
                    s = commandGo(location == null ? null : location.toString());
                    break;
                case "list":
                    s = (commandList());
                    break;
                default:
                    s = ("You're unable to do that.");
                    break;
            }

            gui.addLogText(s);
        }
    }

    private TreeMap<String, Room> mapRooms = Room.getMap();

    private String commandLook() {
        return "LOOK: \n" + mapRooms.get(Room.getCurrentRoomKey()).getDesc();
    }

    private String commandLookItem() {
        String s = "";
        for (Map.Entry<String, Item> entryItem : Item.getItemIDList().entrySet()) {
            Item i = entryItem.getValue();
            if (i.getLocation() != null) {
                if (i.getLocation().equals(Room.getCurrentRoomKey())) {
                    s = i.getItemName() + ": " + i.getItemDesc();
                    break;
                } else s = "There doesn't seem to be anything around.";
            }
        }
        return "ITEMS: \n" + s;
    }

    private String commandExit() {
        Room.setCurrentRoom("B0R0");
        return "EXIT: \n" + "You have returned to the street.";
    }

    private String commandList() {
        ArrayList<String> rooms = getRoomConnections();
        StringBuilder s = new StringBuilder();

        for (String r : rooms) {
            Room room = mapRooms.get(r);
            s.append(room.getRoomName()).append(", ");
        }

        s.deleteCharAt(s.length() - 1);
        s.deleteCharAt(s.length() - 1);

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
        return mapRooms.get(Room.getCurrentRoomKey()).getConnections();
    }

//   private ArrayList<String> getLocation(){
//	   return listItems.get(Item.getLocation());
//   }

}
