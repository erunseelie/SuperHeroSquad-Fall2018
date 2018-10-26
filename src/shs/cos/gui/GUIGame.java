package shs.cos.gui;

import shs.cos.commands.CommandLook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGame {
    JFrame gameWindow;
    Container contain;
    JPanel startPanel, startButtonPanel, gameLogPanel, userTextPanel, choiceButtonPanel1, cbp2, playerPanel, enemyPanel, inventoryPanel, iBtnPanel;
    JLabel startTextLabel, playerAttributesLabel, healthLabel, healthNumLabel, defenseLabel, defenseNumLabel, weaponLabel, weaponNameLabel, enemyLabel, eHealthLabel, eHealthNumLabel, eDefLabel, eDefNumLabel, eWeaponLabel, eWeaponNameLabel, inventoryLabel, inventoryListLabel;
    Font startTextFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 14);
    JButton startButton, lookButton, pickUpButton, enterButton, exitButton, saveButton, puzzleBtn, hintBtn, attackBtn, fleeBtn, useEquipBtn, dropBtn;
    JTextArea gameLog;
    JTextField inputTextArea;
    //private JTextPane textPane;
//private JTextField textInput;
    startHandler sHandler = new startHandler();

    //window method
    public GUIGame() {
//create new window
        gameWindow = new JFrame();
//set window size
        gameWindow.setSize(800, 600);
//for window to close properly
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//create backgrounds color
        gameWindow.getContentPane().setBackground(Color.black);
//create customized layout so this be null to do so
        gameWindow.setLayout(null);
//make created window visible
        gameWindow.setVisible(true);
//contain is base that can hold several things
        contain = gameWindow.getContentPane();
//creating space for text
        startPanel = new JPanel();
//change depending on title
//x, y, width, height
//can adjust later
        startPanel.setBounds(100, 100, 600, 150);
        startPanel.setBackground(Color.black);
//text
        startTextLabel = new JLabel("Lucky Strike Valley");
//text color
        startTextLabel.setForeground(Color.white);
        startTextLabel.setFont(startTextFont);
//panel for button
        startButtonPanel = new JPanel();
//where it will be on window
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
//startButton
        startButton = new JButton("START");
//color for background of button
        startButton.setBackground(Color.black);
//color for text on button
        startButton.setForeground(Color.white);
//text size on start button
        startButton.setFont(normalFont);
//click start button will call
        startButton.addActionListener(sHandler);
        startPanel.add(startTextLabel);
        startButtonPanel.add(startButton);
        contain.add(startPanel);
        contain.add(startButtonPanel);
    }

    //private String command = null;
    public static void main(String[] args) {
        new GUIGame();
//gameGui frame = new gameGui();
//gameGui.printTextLine("asdf");
//frame.clearText();
//System.out.println(frame.getCommand());

    }

    public void gameScreen() {
        startPanel.setVisible(false);
        startButtonPanel.setVisible(false);
////game log
        gameLogPanel = new JPanel();
        gameLogPanel.setBounds(20, 20, 300, 150);
        gameLogPanel.setBackground(Color.black);
        contain.add(gameLogPanel);
        gameLog = new JTextArea("game log area...");
        gameLog.setBounds(20, 20, 300, 150);
        gameLog.setBackground(Color.black);
        gameLog.setForeground(Color.white);
        gameLog.setFont(normalFont);
        gameLog.setLineWrap(true);
        gameLogPanel.add(gameLog);
//input text box
        userTextPanel = new JPanel();
        userTextPanel.setBounds(20, 170, 300, 40);
        userTextPanel.setBackground(Color.white);
        contain.add(userTextPanel);
// textInput = new JTextField();
// userTextPanel.add(textInput);
// textInput.setEnabled(false);
// textPane = new JTextPane();
// textPane.setEditable(false);
        inputTextArea = new JTextField("Text Input");
        inputTextArea.setBounds(20, 20, 300, 150);
        inputTextArea.setBackground(Color.white);
        inputTextArea.setForeground(Color.black);
        inputTextArea.setFont(normalFont);

        inputTextArea.setActionCommand("\n");
        inputTextArea.addActionListener(new handlerCommand());
        // this maybe works?

        userTextPanel.add(inputTextArea);
//buttons1
        choiceButtonPanel1 = new JPanel();
        choiceButtonPanel1.setBounds(20, 210, 150, 200);
        choiceButtonPanel1.setBackground(Color.black);
        contain.add(choiceButtonPanel1);
        lookButton = new JButton("Look");
        lookButton.setBackground(Color.black);
        lookButton.setForeground(Color.white);
        lookButton.setFont(normalFont);
        choiceButtonPanel1.add(lookButton);
        pickUpButton = new JButton("Pick-Up");
        pickUpButton.setBackground(Color.black);
        pickUpButton.setForeground(Color.white);
        pickUpButton.setFont(normalFont);
        choiceButtonPanel1.add(pickUpButton);
        enterButton = new JButton("Enter");
        enterButton.setBackground(Color.black);
        enterButton.setForeground(Color.white);
        enterButton.setFont(normalFont);
        choiceButtonPanel1.add(enterButton);
        exitButton = new JButton("Exit");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(normalFont);
        choiceButtonPanel1.add(exitButton);
        saveButton = new JButton("Save");
        saveButton.setBackground(Color.black);
        saveButton.setForeground(Color.white);
        saveButton.setFont(normalFont);
        choiceButtonPanel1.add(saveButton);
//buttons2
        cbp2 = new JPanel();
        cbp2.setBounds(170, 210, 150, 200);
        cbp2.setBackground(Color.black);
        contain.add(cbp2);
        puzzleBtn = new JButton("Puzzle");
        puzzleBtn.setBackground(Color.black);
        puzzleBtn.setForeground(Color.white);
        puzzleBtn.setFont(normalFont);
        cbp2.add(puzzleBtn);
        hintBtn = new JButton("Hint");
        hintBtn.setBackground(Color.black);
        hintBtn.setForeground(Color.white);
        hintBtn.setFont(normalFont);
        cbp2.add(hintBtn);
        attackBtn = new JButton("Attack");
        attackBtn.setBackground(Color.black);
        attackBtn.setForeground(Color.white);
        attackBtn.setFont(normalFont);
        cbp2.add(attackBtn);
        fleeBtn = new JButton("Flee");
        fleeBtn.setBackground(Color.black);
        fleeBtn.setForeground(Color.white);
        fleeBtn.setFont(normalFont);
        cbp2.add(fleeBtn);
//player
        playerPanel = new JPanel();
        playerPanel.setBounds(330, 20, 200, 190);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(8, 8));
        contain.add(playerPanel);
        playerAttributesLabel = new JLabel("Player Atrributes");
        playerAttributesLabel.setFont(normalFont);
        playerAttributesLabel.setForeground(Color.white);
        playerPanel.add(playerAttributesLabel);
        healthLabel = new JLabel("Health: ");
        healthLabel.setFont(normalFont);
        healthLabel.setForeground(Color.white);
        playerPanel.add(healthLabel);
        healthNumLabel = new JLabel("");
        healthNumLabel.setFont(normalFont);
        healthNumLabel.setForeground(Color.white);
        playerPanel.add(healthNumLabel);
        defenseLabel = new JLabel("Defense: ");
        defenseLabel.setFont(normalFont);
        defenseLabel.setForeground(Color.white);
        playerPanel.add(defenseLabel);
        defenseNumLabel = new JLabel("");
        defenseNumLabel.setFont(normalFont);
        defenseNumLabel.setForeground(Color.white);
        playerPanel.add(defenseNumLabel);
        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);
        weaponNameLabel = new JLabel("");
        weaponNameLabel.setFont(normalFont);
        weaponNameLabel.setForeground(Color.white);
        playerPanel.add(weaponNameLabel);
//enemy
        enemyPanel = new JPanel();
        enemyPanel.setBounds(330, 210, 200, 200);
        enemyPanel.setBackground(Color.black);
        enemyPanel.setLayout(new GridLayout(8, 8));
        contain.add(enemyPanel);
        enemyLabel = new JLabel("Enemy");
        enemyLabel.setFont(normalFont);
        enemyLabel.setForeground(Color.white);
        enemyPanel.add(enemyLabel);
        eHealthLabel = new JLabel("Health: ");
        eHealthLabel.setFont(normalFont);
        eHealthLabel.setForeground(Color.white);
        enemyPanel.add(eHealthLabel);
        eHealthNumLabel = new JLabel("");
        eHealthNumLabel.setFont(normalFont);
        eHealthNumLabel.setForeground(Color.white);
        enemyPanel.add(eHealthNumLabel);
        eDefLabel = new JLabel("Defense: ");
        eDefLabel.setFont(normalFont);
        eDefLabel.setForeground(Color.white);
        enemyPanel.add(eDefLabel);
        eDefNumLabel = new JLabel("");
        eDefNumLabel.setFont(normalFont);
        eDefNumLabel.setForeground(Color.white);
        enemyPanel.add(eDefNumLabel);
        eWeaponLabel = new JLabel("Weapon: ");
        eWeaponLabel.setFont(normalFont);
        eWeaponLabel.setForeground(Color.white);
        enemyPanel.add(eWeaponLabel);
        eWeaponNameLabel = new JLabel("");
        eWeaponNameLabel.setFont(normalFont);
        eWeaponNameLabel.setForeground(Color.white);
        enemyPanel.add(eWeaponNameLabel);
//inventory
//holds list
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(540, 20, 200, 190);
        inventoryPanel.setBackground(Color.black);
        contain.add(inventoryPanel);
        inventoryLabel = new JLabel("Inventory/Equipped Items");
        inventoryLabel.setFont(normalFont);
        inventoryLabel.setForeground(Color.white);
        inventoryPanel.add(inventoryLabel);
//inventory button panel
        iBtnPanel = new JPanel();
        iBtnPanel.setBounds(540, 210, 200, 200);
        iBtnPanel.setBackground(Color.black);
        iBtnPanel.setLayout(new GridLayout(4, 2));
        contain.add(iBtnPanel);
        useEquipBtn = new JButton("Use/Equip");
        useEquipBtn.setBackground(Color.black);
        useEquipBtn.setForeground(Color.white);
        useEquipBtn.setFont(normalFont);
        iBtnPanel.add(useEquipBtn);
        dropBtn = new JButton("Drop");
        dropBtn.setBackground(Color.black);
        dropBtn.setForeground(Color.white);
        dropBtn.setFont(normalFont);
        iBtnPanel.add(dropBtn);
    }

    //start button function
    public class startHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            gameScreen();
        }
    }

    // gets the command entered in the inputTextArea.
    public class handlerCommand implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTextField input = inputTextArea;
            // TODO: add ActionListener
            String[] command = input.getText().toLowerCase().split(" ");
            // TODO: add command parsing
            if (command[0] == "look") {
                gameLog.setText(CommandLook.runCommand());
            }

            input.setText("");
        }
    }
// //text box
// public void printTextLine(String text) {
// textPane.setText(textPane.getText() + text + "\n");
// textPane.selectAll();
// int x = textPane.getSelectionEnd();
// textPane.select(x, x);
// }
// public void clearText() {
// textPane.setText("");
// }
// public String getCommand() {
// command = null;
// textInput.setEnabled(true);
// textInput.requestFocusInWindow();
// do {
// try {
// Thread.sleep(50);
// }
// catch(Exception E) {}
// }while (command == null);
// textInput.setEnabled(false);
// return command;
// }
// public String getCommand(boolean printToScreen) {
// command = null;
// textInput.setEnabled(true);
// textInput.requestFocusInWindow();
// do {
// try {
// Thread.sleep(50);
// }catch(Exception E) {}
// }while(command == null);
//
// textInput.setEnabled(false);
// if(printToScreen)
// printTextLine(">>>" + command);
// return command;
// }
// public void actionPerformed(ActionEvent arg0) {
// command = textInput.getText();
// textInput.setText("");
// }

}
