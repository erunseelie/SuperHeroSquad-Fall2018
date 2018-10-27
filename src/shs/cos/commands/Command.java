package shs.cos.commands;

import shs.cos.gui.GUIGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Gets the command entered.
public class Command implements ActionListener {
    private GUIGame gui;

    public Command(GUIGame gui) {
        this.gui = gui;
    }

    // The default ActionEvent fired when the enter key is pressed.
    public void actionPerformed(ActionEvent e) {
        String[] command = gui.getInput().split(" ");
        int size = command.length;

        String verb = command[0];
        String object; if (size > 1) object = command[1];
        String amount; if (size > 2) amount = command[2];

        String error = "You're unable to do that.";

        // TODO: add full command parsing
        if (verb.equals("look")) {
            gui.logAdd(CommandLook.runCommand());
        } else if (verb.equals("exit")) {
            gui.logAdd(CommandExit.runCommand());
        } else {
            gui.logAdd(error);
        }

        gui.logAdd("\n");
    }
}
