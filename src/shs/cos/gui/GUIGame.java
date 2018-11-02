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
        fontNormal = new Font("Times New Roman", Font.PLAIN, 14),
        fontLog    = new Font("Times New Roman", Font.BOLD,  14);

    private JTextArea gameLog;
    private JScrollPane gameLogScroller;

    private JTextField inputUser, inputPuzzle;

    ArrayList<JPanel> panels = new ArrayList<>();
	private JPanel
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


	// window method
    public GUIGame() {

		// create new window
		windowGame = new JFrame();
		//set window size
		windowGame.setSize(750, 700);
		//for window to close properly
		windowGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//create backgrounds color
		windowGame.getContentPane().setBackground(Color.black);
		//create customized layout so this be null to do so
		windowGame.setLayout(null);
		//make created window visible
		windowGame.setVisible(true);
		//container is base that can hold several things
		container = windowGame.getContentPane();

        makeGameWindow();
	}

	protected void makeGameWindow() {
		//game log
		pnlGameLog = new JPanel();
		pnlGameLog.setBounds(20, 20, 670, 250);
		pnlGameLog.setBackground(Color.black);
		pnlGameLog.setLayout(new FlowLayout());
		container.add(pnlGameLog);

		gameLog = new JTextArea(13,55);
		gameLogScroller = new JScrollPane(
		        gameLog,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
//		btnLook.setEnabled(false);
//		btnLook.setBackground(Color.red);
		pnlChoiceButton1.add(btnLook);
		buttons.add(btnLook);

		btnPickUp = new JButton("Pick Up");
		pnlChoiceButton1.add(btnPickUp);
        buttons.add(btnPickUp);

		btnChangeRoom = new JButton("List Rooms");
		pnlChoiceButton1.add(btnChangeRoom);
        buttons.add(btnChangeRoom);

		btnExitToStreet = new JButton("Exit To Street");
		pnlChoiceButton1.add(btnExitToStreet);
        buttons.add(btnExitToStreet);

		btnSaveGame = new JButton("Save Game");
		pnlChoiceButton1.add(btnSaveGame);
        buttons.add(btnSaveGame);

		//buttons2
		pnlChoiceButton2 = new JPanel();
		pnlChoiceButton2.setBounds(170, 350, 150, 80);
		pnlChoiceButton2.setBackground(Color.black);
		container.add(pnlChoiceButton2);

		btnAttack = new JButton("Attack");
		pnlChoiceButton2.add(btnAttack);
        buttons.add(btnAttack);

		btnFlee = new JButton("Flee");
		pnlChoiceButton2.add(btnFlee);
        buttons.add(btnFlee);

		btnPuzzle = new JButton("Puzzle");
		pnlChoiceButton2.add(btnPuzzle);
        buttons.add(btnPuzzle);

		btnHint = new JButton("Hint");
		pnlChoiceButton2.add(btnHint);
        buttons.add(btnHint);

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
		pnlPuzzle.add(btnSubmit);
        buttons.add(btnSubmit);

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
		pnlButtonsItem.add(btnUseOrEquip);
        buttons.add(btnUseOrEquip);

		btnDropItem = new JButton("Drop");
		pnlButtonsItem.add(btnDropItem);
        buttons.add(btnDropItem);

        /* Matthew's important sandbox, no touchy */

        // quick-set all the buttons to the same colors and font style.
        for(JButton b : buttons) {
            b.setBackground(Color.BLACK);
            b.setForeground(Color.WHITE);
            b.setFont(fontNormal);
//			b.setPreferredSize(new Dimension(120, 30));
        }

        inputUser.addActionListener(new Command(this,null));
        btnExitToStreet.addActionListener(new Command(this,"exit"));
        btnChangeRoom.addActionListener(new Command(this,"list"));
        btnLook.addActionListener(new Command(this,"look"));
	}

    /**
     * Returns the entered input in lowercase form and clears the field.
     * @return A lowercase String, the entered input.
     */
	public String getInput() {
		String s = inputUser.getText().toLowerCase();
        inputUser.setText("");
		return s;
	}

    /**
     * Adds a line of text to the game log and appends 2 newlines.
     * @param s The text to append.
     */
	public void logAdd(String s) {
		gameLog.append(s + "\n\n");
	}

}