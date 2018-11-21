package shs.cos.controller;

import shs.cos.model.Room;
import shs.cos.model.items.Item;
import shs.cos.model.puzzles.Puzzle;
import shs.cos.view.gui.GUIGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

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
                case "items":
                    s = commandLookItem();
                    break;
                case "take":
                    s = commandTake();
                    break;
                case "exit":
                    s = commandExit();
                    break;
                case "list":
                    s = commandList();
                    break;
                case "puzzleAccess":
                    s = commandEnterPuzzle(false);
                    break;
                case "puzzleLeave":
                    s = commandEnterPuzzle(true);
                    break;
                case "puzzleAttempt":
                    s = commandAttemptPuzzle();
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
                    s = commandLook();
                    break;
                case "items":
                    s = commandLookItem();
                    break;
                case "exit":
                    s = commandExit();
                    break;
                case "go":
                    StringBuilder location = object == null ? null : new StringBuilder(object);
                    for (int i = 2; i < size; i++) {
                        location = (location == null ? new StringBuilder("null") : location).append(" ").append(command[i]);
                    }
                    s = commandGo(location == null ? null : location.toString());
                    s += "\n" + commandLook().substring(6);
                    break;
                case "list":
                    s = commandList();
                    break;
                case "take":
                    s = commandTake();
                    break;
                default:
                    s = "You're unable to do that.";
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
//        Item.getItemIDList().get()
        for (Map.Entry<String, Item> entryItem : Item.getItemIDList().entrySet()) {
            Item i = entryItem.getValue();
            if (i.getLocation() != null) {
                if (i.getLocation().equals(Room.getCurrentRoomKey())) {
                    s = i.getItemName() + ": " + i.getItemDesc();
                    break;
                } else s = "There doesn't seem to be anything around.";
            }
        }
        return "ITEMS:\n" + s;
    }

    private String commandExit() {
        Room.setCurrentRoom("B0R0");
        gui.enablePuzzleAccess(false);
        return "EXIT:\n" + "You have returned to the street.";
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
                ArrayList<Puzzle> listP = Puzzle.getPuzzles();
                String curRoom = Room.getCurrentRoomKey();
                for (Puzzle p : listP) {
                    if (p.getLocation().equals(curRoom)) {
                        gui.enablePuzzleAccess(true);
                    } else
                        gui.enablePuzzleAccess(false);
                }
                s = "You mosey through the dust. You have arrived at your destination...";

                break;
            } else s = "That place doesn't seem to be nearby.";
        }
        return "GO: " + mapRooms.get(Room.getCurrentRoomKey()).getRoomName() + "\n" + s;
    }

    private ArrayList<String> getRoomConnections() {
        return mapRooms.get(Room.getCurrentRoomKey()).getConnections();
    }

    private String commandEnterPuzzle(boolean exit) {
        String s = "";
        if (exit) {
            Puzzle.setAttempting(false);
            gui.enterPuzzle(false);
            gui.enablePuzzleAccess(true);
            s = "You leave the puzzle for now.";
        } else {
            ArrayList<Puzzle> listP = Puzzle.getPuzzles();
            String curRoom = Room.getCurrentRoomKey();
            for (Puzzle p : listP) {
                if (p.getLocation().equals(curRoom)) {
                    Puzzle.setAttempting(true);
                    gui.enterPuzzle(true);
                    gui.enablePuzzleAccess(false);
                    Puzzle.setCurrentPuzzle(p);
                    s = "Something catches your eye...\n";
                    s += p.getDescription();
                    break;
                }
            }
        }
        return "PUZZLE:\n" + s;
    }

    private String commandAttemptPuzzle() {
        String input = gui.getPuzzleInput();
        AtomicReference<String> s = new AtomicReference<>("");
        Puzzle p = Puzzle.getCurrentPuzzle();
        if (p.attempt(input)) {
            Item i = Item.getItemIDList().get(p.getBoon());
            Item.addPlayerItem(i);
            s.set("You solved the puzzle!\nYou found:\n" + i.getItemName());
        } else {
            s.set("That's not right - best take caution.");
            p.setCounter(p.getCounter() + 1);
            if (p.getCounter() >= 3) {
                Main.player.applyDamage(5);
                s.set(s.get() + "\nOuch! Careful! Something's jabbed you in the finger.");
            }
        }
        return "ATTEMPT:\n" + s;
    }

    private String commandTake() {
        // TODO: "The player must have already located the item using the CMD_Look." ??? NYI
        AtomicReference<String> s = new AtomicReference<>("");
        AtomicReference<String> i = new AtomicReference<>("");
        for (Map.Entry<String, Item> entry : Item.getItemIDList().entrySet()) {
            String k = entry.getKey();
            Item v = entry.getValue();
            if (v.getLocation().equals(Room.getCurrentRoomKey())) {
                Item.addPlayerItem(v);
                // TODO: check for multiple instances of one item
                i.set(k);
                s.set("You picked up: " + v.getItemName().toLowerCase() + ".");
                break;
            } else {
                s.set("There's nothing around that you can take.");
            }
        }
        Item.getItemIDList().remove(i.toString());
        return "TAKE:\n" + s;
    }

}
