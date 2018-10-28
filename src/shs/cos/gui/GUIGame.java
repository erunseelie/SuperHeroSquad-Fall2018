package shs.cos.gui;

import shs.cos.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

public class GUIGame {
	JFrame gameWindow;

	Container contain;

	JPanel startPanel,
	startButtonPanel,
	gameLogPanel,
	userInputNavigationPanel,
	choiceButtonPanel1,
	cbp2,
	puzzlePanel,
	playerPanel,
	enemyPanel,
	inventoryPanel,
	iBtnPanel;

	JLabel startTextLabel,
	enterAnswerLabel,
	playerAttributesLabel,
	healthLabel,
	healthNumLabel,
	defenseLabel,
	defenseNumLabel,
	weaponLabel,
	weaponNameLabel,
	enemyLabel,
	eHealthLabel,
	eHealthNumLabel,
	eDefLabel,
	eDefNumLabel,
	eWeaponLabel,
	eWeaponNameLabel,
	inventoryLabel,
	inventoryListLabel;

	Font startTextFont = new Font("Times New Roman", Font.PLAIN, 30);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 14);
	Font gLogFont = new Font ("Times New Roman", Font.PLAIN, 14);


	JButton startButton,
	lookButton,
	pickUpButton,
	changeRoomButton,
	exitToStreetButton,
	saveGameButton,
	attackBtn,
	fleeBtn,
	puzzleBtn,
	hintBtn,
	submitBtn,
	useEquipBtn,
	dropBtn;

	static JTextArea gLogArea;
	JScrollPane scrollPane;
	
	static JTextField puzzleInput;
	
	static JTextField userInputNav;

	startHandler sHandler = new startHandler();
	lookHandler lHandler = new lookHandler();
	


	// window method
	public GUIGame() {

		// create new window
		gameWindow = new JFrame();
		//set window size
		gameWindow.setSize(800, 700);
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

	public void gameScreen() {
		startPanel.setVisible(false);
		startButtonPanel.setVisible(false);

		//game log
		gameLogPanel = new JPanel();
		gameLogPanel.setBounds(20, 20, 460, 160);
		gameLogPanel.setBackground(Color.black);
		contain.add(gameLogPanel);
		gLogArea = new JTextArea(6,35);
		scrollPane = new JScrollPane(gLogArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gameLogPanel.setLayout(new FlowLayout());
		//setContentPane(gameLogPanel);
		gLogArea.setEditable(false);
		gLogArea.setBounds(20, 20, 300, 100);
		gLogArea.setBackground(Color.black);
		gLogArea.setForeground(Color.white);
		gLogArea.setLineWrap(true);
		gLogArea.setWrapStyleWord(true);
		//gLogArea.setFont(normalFont);
		gLogArea.setFont(gLogFont);
		//TextFromFile(gLogArea);
		gameLogPanel.add(scrollPane);
		
		//user input navigation panel
		userInputNavigationPanel = new JPanel();
		userInputNavigationPanel.setBounds(30, 190, 200, 50);
		userInputNavigationPanel.setBackground(Color.black);
		userInputNavigationPanel.setLayout(new GridLayout(2, 2));
		contain.add(userInputNavigationPanel);
		//user input navigation text field
		userInputNav = new JTextField("");
		userInputNav.setBounds(50, 100, 130, 100);
		userInputNav.setBackground(Color.white);
		userInputNav.setForeground(Color.black);
		userInputNav.setFont(normalFont);
		userInputNavigationPanel.add(userInputNav);
		

		//buttons1
		choiceButtonPanel1 = new JPanel();
		choiceButtonPanel1.setBounds(20, 250, 150, 200);
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
		changeRoomButton = new JButton("List Rooms");
		changeRoomButton.setBackground(Color.black);
		changeRoomButton.setForeground(Color.white);
		changeRoomButton.setFont(normalFont);
		choiceButtonPanel1.add(changeRoomButton);
		exitToStreetButton = new JButton("Exit To Street");
		exitToStreetButton.setBackground(Color.black);
		exitToStreetButton.setForeground(Color.white);
		exitToStreetButton.setFont(normalFont);
		choiceButtonPanel1.add(exitToStreetButton);
		saveGameButton = new JButton("Save Game");
		saveGameButton.setBackground(Color.black);
		saveGameButton.setForeground(Color.white);
		saveGameButton.setFont(normalFont);
		choiceButtonPanel1.add(saveGameButton);

		//buttons2
		cbp2 = new JPanel();
		cbp2.setBounds(170, 250, 150, 80);
		cbp2.setBackground(Color.black);
		contain.add(cbp2);
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

		//puzzle text box
		puzzlePanel = new JPanel();
		puzzlePanel.setBounds(170, 350, 150, 100);
		puzzlePanel.setBackground(Color.black);
		puzzlePanel.setLayout(new GridLayout(4, 4));
		contain.add(puzzlePanel);
		enterAnswerLabel = new JLabel("Enter puzzle answer: ");
		enterAnswerLabel.setFont(normalFont);
		enterAnswerLabel.setForeground(Color.white);
		puzzlePanel.add(enterAnswerLabel);
		puzzleInput = new JTextField("");
		puzzleInput.setBounds(30, 30, 130, 100);
		puzzleInput.setBackground(Color.white);
		puzzleInput.setForeground(Color.black);
		puzzleInput.setFont(normalFont);
		puzzlePanel.add(puzzleInput);
		submitBtn = new JButton("Attempt");
		submitBtn.setBackground(Color.black);
		submitBtn.setForeground(Color.white);
		submitBtn.setFont(normalFont);
		puzzlePanel.add(submitBtn);

		//player
		playerPanel = new JPanel();
		playerPanel.setBounds(330, 250, 150, 100);
		playerPanel.setBackground(Color.black);
		playerPanel.setLayout(new GridLayout(8, 8));
		contain.add(playerPanel);
		playerAttributesLabel = new JLabel("Player Attributes");
		playerAttributesLabel.setFont(gLogFont);
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
		enemyPanel.setBounds(330, 350, 150, 100);
		enemyPanel.setBackground(Color.black);
		enemyPanel.setLayout(new GridLayout(8, 8));
		contain.add(enemyPanel);
		enemyLabel = new JLabel("Enemy");
		enemyLabel.setFont(gLogFont);
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
		inventoryPanel.setBounds(490, 20, 200, 320);
		inventoryPanel.setBackground(Color.black);
		contain.add(inventoryPanel);
		inventoryLabel = new JLabel("Inventory/Equipped Items");
		inventoryLabel.setFont(normalFont);
		inventoryLabel.setForeground(Color.white);
		inventoryPanel.add(inventoryLabel);

		//inventory button panel
		iBtnPanel = new JPanel();
		iBtnPanel.setBounds(490, 350, 200, 200);
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

        // Matthew's important sandbox, no touchy
        userInputNav.addActionListener(new Command( null));
        lookButton.addActionListener(new Command("look"));
        changeRoomButton.addActionListener(new Command( "list"));
        exitToStreetButton.addActionListener(new Command("exit"));
	}

	//start button function
	public class startHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			gameScreen();
		}
	}
	
	//look button function
	public class lookHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
		}
	}

//	public static void TextFromFile(JTextArea gLogPane)
//	{
//		try {
//			String path = "res/testRoomData.txt";
//			File file = new File(path);
//			FileReader fr = new FileReader(file);
//			while(fr.read() != -1) {
//				gLogPane.read(fr, null);
//			}
//			fr.close();
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}

	// Matthew's important sandbox, no touchy

    // Returns the entered input in lowercase form and clears the field.
	public static String getInput() {
		String s = userInputNav.getText().toLowerCase();
        userInputNav.setText("");
		return s;
	}
	// Adds a line of text to the game log and appends 2 newlines.
	public static void logAdd(String s) {
		gLogArea.append(s + "\n\n");
	}

}