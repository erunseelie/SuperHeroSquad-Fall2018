package shs.cos.view.gui;

import shs.cos.controller.SessionManager;
import shs.cos.controller.Main;
import shs.cos.controller.Command;
import shs.cos.model.Room;
import shs.cos.model.puzzles.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GUIGame extends Observable implements Observer {
    public ArrayList<JComponent> groupAll = new ArrayList<>();
    ArrayList<JPanel> panels = new ArrayList<>();
    private JFrame windowGame;
    private Container container;
    private Font
            fontNormal = new Font("Times New Roman", Font.PLAIN, 14),
            fontLog = new Font("Times New Roman", Font.BOLD, 14);
    private JTextArea gameLog;
    private JScrollPane gameLogScroller;
    private JTextField inputUser, inputPuzzle;
    private JPanel
            pnlGameLog,
            pnlUserInputNavigation,

    pnlChoiceButton1,
            pnlButtonsMonster,

    pnlPuzzle,

    pnlPlayer,
            pnlEnemy,

    pnlInventory,
            pnlButtonsItem;
    private ArrayList<JLabel> labels = new ArrayList<>();
    private JLabel
            lblGoToRoom,

    lblPlayerAttributes,
            lblHealth, lblHealthNumber,
            lblDefense, lblDefenseNumber,
            lblWeapon, lblWeaponName,

    lblEnemyAttributes,
            lblEnemyHealth, lblEnemyHealthNumber,
            lblEnemyDefense, lblEnemyDefenseNumber,
            lblEnemyWeapon, lblEnemyWeaponName,

    lblInventory,
            lblInventoryList;
    private JButton
            btnSaveGame,

    btnLook,
            btnChangeRoom,
            btnExitToStreet,

    btnAttack,
            btnFlee,

    btnPuzzleAccess,
            btnPuzzleHint,
            btnPuzzleAttempt,
            btnPuzzleExit,

    btnLookItem,
            btnUseOrEquip,
            btnDropItem;
    private ArrayList<JComponent> groupPuzzle = new ArrayList<>();

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

        windowGame.setLocationRelativeTo(null);
        windowGame.setResizable(false);

        //container is base that can hold several things
        container = windowGame.getContentPane();

        this.addObserver(this);

        makeGameWindow();
    }

    private void makeGameWindow() {
        //game log
        pnlGameLog = new JPanel();
        pnlGameLog.setBounds(20, 20, 670, 250);
        pnlGameLog.setBackground(Color.black);
        pnlGameLog.setLayout(new FlowLayout());
        container.add(pnlGameLog);

        gameLog = new JTextArea(13, 55);
        gameLogScroller = new JScrollPane(
                gameLog,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gameLog.setEditable(false);
        gameLog.setBounds(20, 20, 300, 100);
        gameLog.setBackground(Color.black);
        gameLog.setForeground(Color.white);
        gameLog.setLineWrap(true);
        gameLog.setWrapStyleWord(true);
        gameLog.setFont(fontLog);
        gameLog.setMaximumSize(new Dimension(300, 100));
        pnlGameLog.add(gameLogScroller);

        //user input navigation panel
        pnlUserInputNavigation = new JPanel();
        pnlUserInputNavigation.setBounds(50, 280, 380, 50);
        pnlUserInputNavigation.setBackground(Color.black);
        pnlUserInputNavigation.setLayout(new GridLayout(2, 2));
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
        groupAll.add(btnLook);

        btnLookItem = new JButton("Look for Items");
        pnlChoiceButton1.add(btnLookItem);
        groupAll.add(btnLookItem);

        btnChangeRoom = new JButton("List Rooms");
        pnlChoiceButton1.add(btnChangeRoom);
        groupAll.add(btnChangeRoom);

        btnExitToStreet = new JButton("Exit To Street");
        pnlChoiceButton1.add(btnExitToStreet);
        groupAll.add(btnExitToStreet);

        btnSaveGame = new JButton("Save Game");
        pnlChoiceButton1.add(btnSaveGame);
        groupAll.add(btnSaveGame);

        // monster buttons
        pnlButtonsMonster = new JPanel();
        pnlButtonsMonster.setBounds(170, 350, 150, 300);
        pnlButtonsMonster.setBackground(Color.BLACK);
        container.add(pnlButtonsMonster);

        btnAttack = new JButton("Attack");
        pnlButtonsMonster.add(btnAttack);
        groupAll.add(btnAttack);

        btnFlee = new JButton("Flee");
        pnlButtonsMonster.add(btnFlee);
        groupAll.add(btnFlee);

        // Puzzles (@ Matthew)
        pnlPuzzle = new JPanel();
        {
            pnlPuzzle.setBounds(170, 450, 150, 150);
            GridLayout g = new GridLayout(5, 1);
            g.setVgap(3);
            pnlPuzzle.setLayout(g);
        }

        btnPuzzleAccess = new JButton("Seek Puzzle");
        pnlPuzzle.add(btnPuzzleAccess);
        btnPuzzleAttempt = new JButton("Attempt");
        pnlPuzzle.add(btnPuzzleAttempt);
        btnPuzzleHint = new JButton("Hint");
        pnlPuzzle.add(btnPuzzleHint);

        btnPuzzleExit = new JButton("Leave Puzzle");
        pnlPuzzle.add(btnPuzzleExit);

        inputPuzzle = new JTextField(" Enter puzzle answer...");
        inputPuzzle.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                inputPuzzle.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                inputPuzzle.setText(" Enter puzzle answer...");
            }
        });
        pnlPuzzle.add(inputPuzzle);

        for (Component c : pnlPuzzle.getComponents()) {
            groupPuzzle.add((JComponent) c);
            c.setEnabled(false);
        }
        groupAll.addAll(groupPuzzle);
        container.add(pnlPuzzle);

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

        lblHealthNumber = new JLabel("" + Main.player.getHealth());
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
        groupAll.add(btnUseOrEquip);

        btnDropItem = new JButton("Drop");
        pnlButtonsItem.add(btnDropItem);
        groupAll.add(btnDropItem);

        /* Matthew's important sandbox, no touchy */

        // quick-set all the buttons to the same colors and font style.
        for (JComponent b : groupAll) {
            if ((b instanceof JButton)) {
                b.setBackground(Color.BLACK);
                b.setForeground(Color.WHITE);
                b.setFont(fontNormal);
                b.setPreferredSize(new Dimension(120, 30));
            }
        }

        inputUser.addActionListener(new Command(this, null));
        inputUser.addActionListener(a -> {
            new Command(this, null);
            setChanged();
            notifyObservers();
        });
        groupAll.add(inputUser);

        for (JComponent c : groupAll) {
            if (c instanceof JButton) {
                JButton b = (JButton) c;
                b.addActionListener(a -> {
                    setChanged();
                    notifyObservers();
                });
            }
        }

        btnChangeRoom.addActionListener(new Command(this, "list"));
        btnExitToStreet.addActionListener(new Command(this, "exit"));
        btnLook.addActionListener(new Command(this, "look"));
        btnLookItem.addActionListener(new Command(this, "items"));

        btnPuzzleAccess.addActionListener(new Command(this, "puzzleAccess"));
        btnPuzzleExit.addActionListener(new Command(this, "puzzleLeave"));
        btnPuzzleAttempt.addActionListener(new Command(this, "puzzleAttempt"));
        btnPuzzleHint.addActionListener(new Command(this, "puzzleHint"));

        btnSaveGame.addActionListener(a -> {
            SessionManager.updateFile();
            addLogText("SAVE:\nSaved the game.");
        });


        ArrayList<Puzzle> listP = Puzzle.getPuzzles();
        String curRoom = Room.getCurrentRoomKey();
        for (Puzzle p : listP) {
            if (p.getLocation().equals(curRoom)) {
                enablePuzzleAccess(true);
                break;
            }
        }
    }

    /**
     * Returns the entered input in lowercase form and clears the field.
     *
     * @return A lowercase String, the entered input.
     */
    public String getInput() {
        String s = inputUser.getText().toLowerCase();
        inputUser.setText("");
        return s;
    }

    /**
     * Adds a line of text to the game log and appends 2 newlines.
     *
     * @param s The text to append.
     */
    public void addLogText(String s) {
        gameLog.append(s + "\n\n");
    }

    public void enablePuzzleAccess(boolean b) {
        btnPuzzleAccess.setEnabled(b);
    }

    public void enterPuzzle(boolean b) {
        for (JComponent c : groupAll) c.setEnabled(!b);
        for (JComponent c : groupPuzzle) c.setEnabled(b);
    }

    public String getPuzzleInput() {
        return inputPuzzle.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        lblHealthNumber.setText("" + Main.player.getHealth());
    }
}