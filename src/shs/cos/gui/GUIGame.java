package shs.cos.gui;

import shs.cos.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

public class GUIGame {
	private JFrame windowGame;

	private Container container;

    private Font
        fontStart  = new Font("Times New Roman", Font.PLAIN, 30),
        fontNormal = new Font("Times New Roman", Font.PLAIN, 14),
        fontLog    = new Font("Times New Roman", Font.BOLD,  14);

    private JTextArea gameLog;
    private JScrollPane gameLogScroller;

    private JTextField
            inputUser,
            inputPuzzle;

    ArrayList<JPanel> panels = new ArrayList<>();
	private JPanel
        pnlStart,
        pnlStartButton,

        pnlGameLog,
        pnlUserInputNavigation,

        pnlChoiceButton1,
        pnlChoiceButton2,

        pnlPuzzle,

        pnlPlayer,
        pnlEnemy,

        pnlInventory,
	    pnlButtonsItem;

    private ArrayList<JLabel> labels = new ArrayList<>();
    private JLabel
        lblStartText,

        lblGoToRoom,

        lblEnterAnswer,

        lblPlayerAttributes,
        lblHealth,  lblHealthNumber,
        lblDefense, lblDefenseNumber,
        lblWeapon,  lblWeaponName,

        lblEnemyAttributes,
        lblEnemyHealth,  lblEnemyHealthNumber,
        lblEnemyDefense, lblEnemyDefenseNumber,
        lblEnemyWeapon,  lblEnemyWeaponName,

        lblInventory,
        lblInventoryList;

    private ArrayList<JButton> buttons = new ArrayList<>();
	private JButton
        btnStart,
        btnSaveGame,

        btnLook,
        btnChangeRoom,
        btnExitToStreet,

        btnAttack,
        btnFlee,

        btnPuzzle,
        btnHint,
        btnSubmit,

        btnPickUp,
        btnUseOrEquip,
        btnDropItem;

	private handlerStart handler = new handlerStart();

	private TreeMap<String, String> mapSaveData;

	// window method
	public GUIGame(TreeMap<String, String> mapSaveData) {

	    this.mapSaveData = mapSaveData;

		// create new window
		windowGame = new JFrame();
		//set window size
		windowGame.setSize(750, 700);
		//for window to close properly
		windowGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//create backgrounds color
		windowGame.getContentPane().setBackground(Color.black);
		//create customized layout so this be null to do so
		windowGame.setLayout(null);
		//make created window visible
		windowGame.setVisible(true);
		//container is base that can hold several things
		container = windowGame.getContentPane();

		//creating space for text
		pnlStart = new JPanel();
		//change depending on title
		//x, y, width, height
		//can adjust later
		pnlStart.setBounds(100, 100, 600, 150);
		pnlStart.setBackground(Color.black);
		//text
		lblStartText = new JLabel("Lucky Strike Valley");
		//text color
		lblStartText.setForeground(Color.white);
		lblStartText.setFont(fontStart);
		//panel for button
		pnlStartButton = new JPanel();
		//where it will be on window
		pnlStartButton.setBounds(300, 400, 200, 100);
		pnlStartButton.setBackground(Color.black);
		//btnStart
		btnStart = new JButton("START");
		//color for background of button
		btnStart.setBackground(Color.black);
		//color for text on button
		btnStart.setForeground(Color.white);
		//text size on start button
		btnStart.setFont(fontNormal);
		//click start button will call
		btnStart.addActionListener(handler);
		pnlStart.add(lblStartText);
		pnlStartButton.add(btnStart);

		container.add(pnlStart);
		container.add(pnlStartButton);

	}

	protected void makeGameWindow() {
		pnlStart.setVisible(false);
		pnlStartButton.setVisible(false);

		//game log
		pnlGameLog = new JPanel();
		pnlGameLog.setBounds(20, 20, 670, 250);
		pnlGameLog.setBackground(Color.black);
		container.add(pnlGameLog);
		gameLog = new JTextArea(13,55);
		gameLogScroller = new JScrollPane(gameLog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlGameLog.setLayout(new FlowLayout());
		gameLog.setEditable(false);
		gameLog.setBounds(20, 20, 300, 100);
		gameLog.setBackground(Color.black);
		gameLog.setForeground(Color.white);
		gameLog.setLineWrap(true);
		gameLog.setWrapStyleWord(true);
		gameLog.setFont(fontLog);
		pnlGameLog.add(gameLogScroller);
		
		//user input navigation panel
		pnlUserInputNavigation = new JPanel();
		pnlUserInputNavigation.setBounds(50, 280, 380, 50);
		pnlUserInputNavigation.setBackground(Color.black);
		pnlUserInputNavigation.setLayout(new GridLayout(2,2));
		container.add(pnlUserInputNavigation);
		//user input navigation text field
		lblGoToRoom = new JLabel("Enter 'go' followed by a listed room name:");
		lblGoToRoom.setFont(fontLog);
		lblGoToRoom.setForeground(Color.white);
		pnlUserInputNavigation.add(lblGoToRoom);
		inputUser = new JTextField("");
		inputUser.setBounds(50, 100, 130, 100);
		inputUser.setBackground(Color.white);
		inputUser.setForeground(Color.black);
		inputUser.setFont(fontNormal);
		pnlUserInputNavigation.add(inputUser);
		

		//buttons1
		pnlChoiceButton1 = new JPanel();
		pnlChoiceButton1.setBounds(20, 350, 150, 200);
		pnlChoiceButton1.setBackground(Color.black);
		container.add(pnlChoiceButton1);
		btnLook = new JButton("Look");
		btnLook.setBackground(Color.black);
		btnLook.setForeground(Color.white);
		btnLook.setFont(fontNormal);
//		btnLook.setEnabled(false);
//		btnLook.setBackground(Color.red);
		
		pnlChoiceButton1.add(btnLook);
		btnPickUp = new JButton("Pick Up");
		btnPickUp.setBackground(Color.black);
		btnPickUp.setForeground(Color.white);
		btnPickUp.setFont(fontNormal);
		pnlChoiceButton1.add(btnPickUp);
		btnChangeRoom = new JButton("List Rooms");
		btnChangeRoom.setBackground(Color.black);
		btnChangeRoom.setForeground(Color.white);
		btnChangeRoom.setFont(fontNormal);
		pnlChoiceButton1.add(btnChangeRoom);
		btnExitToStreet = new JButton("Exit To Street");
		btnExitToStreet.setBackground(Color.black);
		btnExitToStreet.setForeground(Color.white);
		btnExitToStreet.setFont(fontNormal);
		pnlChoiceButton1.add(btnExitToStreet);
		btnSaveGame = new JButton("Save Game");
		btnSaveGame.setBackground(Color.black);
		btnSaveGame.setForeground(Color.white);
		btnSaveGame.setFont(fontNormal);
		pnlChoiceButton1.add(btnSaveGame);

		//buttons2
		pnlChoiceButton2 = new JPanel();
		pnlChoiceButton2.setBounds(170, 350, 150, 80);
		pnlChoiceButton2.setBackground(Color.black);
		container.add(pnlChoiceButton2);
		btnAttack = new JButton("Attack");
		btnAttack.setBackground(Color.black);
		btnAttack.setForeground(Color.white);
		btnAttack.setFont(fontNormal);
		pnlChoiceButton2.add(btnAttack);
		btnFlee = new JButton("Flee");
		btnFlee.setBackground(Color.black);
		btnFlee.setForeground(Color.white);
		btnFlee.setFont(fontNormal);
		pnlChoiceButton2.add(btnFlee);
		btnPuzzle = new JButton("Puzzle");
		btnPuzzle.setBackground(Color.black);
		btnPuzzle.setForeground(Color.white);
		btnPuzzle.setFont(fontNormal);
		pnlChoiceButton2.add(btnPuzzle);
		btnHint = new JButton("Hint");
		btnHint.setBackground(Color.black);
		btnHint.setForeground(Color.white);
		btnHint.setFont(fontNormal);
		pnlChoiceButton2.add(btnHint);

		//puzzle text box
		pnlPuzzle = new JPanel();
		pnlPuzzle.setBounds(170, 440, 150, 100);
		pnlPuzzle.setBackground(Color.black);
		pnlPuzzle.setLayout(new GridLayout(4, 4));
		container.add(pnlPuzzle);
		lblEnterAnswer = new JLabel("Enter puzzle answer: ");
		lblEnterAnswer.setFont(fontNormal);
		lblEnterAnswer.setForeground(Color.white);
		pnlPuzzle.add(lblEnterAnswer);
		inputPuzzle = new JTextField("");
		inputPuzzle.setBounds(30, 30, 130, 100);
		inputPuzzle.setBackground(Color.white);
		inputPuzzle.setForeground(Color.black);
		inputPuzzle.setFont(fontNormal);
		pnlPuzzle.add(inputPuzzle);
		btnSubmit = new JButton("Attempt");
		btnSubmit.setBackground(Color.black);
		btnSubmit.setForeground(Color.white);
		btnSubmit.setFont(fontNormal);
		pnlPuzzle.add(btnSubmit);

		//player
		pnlPlayer = new JPanel();
		pnlPlayer.setBounds(330, 350, 150, 100);
		pnlPlayer.setBackground(Color.black);
		pnlPlayer.setLayout(new GridLayout(8, 8));
		container.add(pnlPlayer);
		lblPlayerAttributes = new JLabel("Player Attributes");
		lblPlayerAttributes.setFont(fontLog);
		lblPlayerAttributes.setForeground(Color.white);
		pnlPlayer.add(lblPlayerAttributes);
		lblHealth = new JLabel("Health: ");
		lblHealth.setFont(fontNormal);
		lblHealth.setForeground(Color.white);
		pnlPlayer.add(lblHealth);
		lblHealthNumber = new JLabel("");
		lblHealthNumber.setFont(fontNormal);
		lblHealthNumber.setForeground(Color.white);
		pnlPlayer.add(lblHealthNumber);
		lblDefense = new JLabel("Defense: ");
		lblDefense.setFont(fontNormal);
		lblDefense.setForeground(Color.white);
		pnlPlayer.add(lblDefense);
		lblDefenseNumber = new JLabel("");
		lblDefenseNumber.setFont(fontNormal);
		lblDefenseNumber.setForeground(Color.white);
		pnlPlayer.add(lblDefenseNumber);
		lblWeapon = new JLabel("Weapon: ");
		lblWeapon.setFont(fontNormal);
		lblWeapon.setForeground(Color.white);
		pnlPlayer.add(lblWeapon);
		lblWeaponName = new JLabel("");
		lblWeaponName.setFont(fontNormal);
		lblWeaponName.setForeground(Color.white);
		pnlPlayer.add(lblWeaponName);

		//enemy
		pnlEnemy = new JPanel();
		pnlEnemy.setBounds(330, 460, 150, 100);
		pnlEnemy.setBackground(Color.black);
		pnlEnemy.setLayout(new GridLayout(8, 8));
		container.add(pnlEnemy);
		lblEnemyAttributes = new JLabel("Enemy");
		lblEnemyAttributes.setFont(fontLog);
		lblEnemyAttributes.setForeground(Color.white);
		pnlEnemy.add(lblEnemyAttributes);
		lblEnemyHealth = new JLabel("Health: ");
		lblEnemyHealth.setFont(fontNormal);
		lblEnemyHealth.setForeground(Color.white);
		pnlEnemy.add(lblEnemyHealth);
		lblEnemyHealthNumber = new JLabel("");
		lblEnemyHealthNumber.setFont(fontNormal);
		lblEnemyHealthNumber.setForeground(Color.white);
		pnlEnemy.add(lblEnemyHealthNumber);
		lblEnemyDefense = new JLabel("Defense: ");
		lblEnemyDefense.setFont(fontNormal);
		lblEnemyDefense.setForeground(Color.white);
		pnlEnemy.add(lblEnemyDefense);
		lblEnemyDefenseNumber = new JLabel("");
		lblEnemyDefenseNumber.setFont(fontNormal);
		lblEnemyDefenseNumber.setForeground(Color.white);
		pnlEnemy.add(lblEnemyDefenseNumber);
		lblEnemyWeapon = new JLabel("Weapon: ");
		lblEnemyWeapon.setFont(fontNormal);
		lblEnemyWeapon.setForeground(Color.white);
		pnlEnemy.add(lblEnemyWeapon);
		lblEnemyWeaponName = new JLabel("");
		lblEnemyWeaponName.setFont(fontNormal);
		lblEnemyWeaponName.setForeground(Color.white);
		pnlEnemy.add(lblEnemyWeaponName);

		//inventory
		//holds list
		pnlInventory = new JPanel();
		pnlInventory.setBounds(490, 280, 200, 190);
		pnlInventory.setBackground(Color.black);
		container.add(pnlInventory);
		lblInventory = new JLabel("Inventory/Equipped Items");
		lblInventory.setFont(fontNormal);
		lblInventory.setForeground(Color.white);
		pnlInventory.add(lblInventory);

		//inventory button panel
		pnlButtonsItem = new JPanel();
		pnlButtonsItem.setBounds(490, 480, 200, 40);
		pnlButtonsItem.setBackground(Color.black);
		pnlButtonsItem.setLayout(new FlowLayout());
		container.add(pnlButtonsItem);
		btnUseOrEquip = new JButton("Use/Equip");
		btnUseOrEquip.setBackground(Color.black);
		btnUseOrEquip.setForeground(Color.white);
		btnUseOrEquip.setFont(fontNormal);
		pnlButtonsItem.add(btnUseOrEquip);
		btnDropItem = new JButton("Drop");
		btnDropItem.setBackground(Color.black);
		btnDropItem.setForeground(Color.white);
		btnDropItem.setFont(fontNormal);
		pnlButtonsItem.add(btnDropItem);

        /* Matthew's important sandbox, no touchy */
        inputUser.addActionListener(new Command(this,null));
        btnLook.addActionListener(new Command(this,"look"));
        btnChangeRoom.addActionListener(new Command(this,"list"));
        btnExitToStreet.addActionListener(new Command(this,"exit"));
	}

	//start button function
	public class handlerStart implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			makeGameWindow();
		}
	}

	/* Matthew's important sandbox, no touchy */

    // Returns the entered input in lowercase form and clears the field.
	public String getInput() {
		String s = inputUser.getText().toLowerCase();
        inputUser.setText("");
		return s;
	}
	// Adds a line of text to the game log and appends 2 newlines.
	public void logAdd(String s) {
		gameLog.append(s + "\n\n");
	}

}