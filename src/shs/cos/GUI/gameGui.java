package shs.cos.GUI;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class gameGui {
	JFrame gameWindow;
	Container contain;
	JPanel startPanel, startButtonPanel, gameLogPanel, choiceButtonPanel;
	JLabel startTextLabel;
	Font startTextFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 14);
	JButton startButton, choice1, choice2, choice3, choice4;
	JTextArea gameLog;
	
	startHandler sHandler = new startHandler();
	
	
	public static void main(String[] args) {
		new gameGui();
	}
	
	//window method
	public gameGui() {
		
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
	
	public void gameScreen() {
		
		startPanel.setVisible(false);
		startButtonPanel.setVisible(false);
		
		gameLogPanel = new JPanel();
		gameLogPanel.setBounds(100, 100, 500, 250);
		gameLogPanel.setBackground(Color.black);
		contain.add(gameLogPanel);
		
		
		gameLog = new JTextArea("game log area");
		gameLog.setBounds(100, 100, 500, 250);
		gameLog.setBackground(Color.black);
		gameLog.setForeground(Color.white);
		gameLog.setFont(normalFont);
		gameLog.setLineWrap(true);
		gameLogPanel.add(gameLog);
		
		
		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(250, 350, 300, 150);
		choiceButtonPanel.setBackground(Color.black);
		contain.add(choiceButtonPanel);
		
		choice1 = new JButton("Choice 1");
		choice1.setBackground(Color.black);
		choice1.setForeground(Color.white);
		choice1.setFont(normalFont);
		choiceButtonPanel.add(choice1);
		choice2 = new JButton("Choice2");
		choice2.setBackground(Color.black);
		choice2.setForeground(Color.white);
		choice2.setFont(normalFont);
		choiceButtonPanel.add(choice2);
		choice3 = new JButton("Choice 3");
		choice3.setBackground(Color.black);
		choice3.setForeground(Color.white);
		choice3.setFont(normalFont);
		choiceButtonPanel.add(choice3);
		choice4 = new JButton("Choice 4");
		choice4.setBackground(Color.black);
		choice4.setForeground(Color.white);
		choice4.setFont(normalFont);
		choiceButtonPanel.add(choice4);
		
	}
	
	//start button function
	public class startHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			gameScreen();
		}
	}

}
