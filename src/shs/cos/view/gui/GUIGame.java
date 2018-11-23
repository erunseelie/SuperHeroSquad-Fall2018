package shs.cos.view.gui;

import shs.cos.controller.SessionManager;
import shs.cos.controller.Main;
import shs.cos.controller.Command;
import shs.cos.model.Room;
import shs.cos.model.entities.Monster;
import shs.cos.model.items.Item;
import shs.cos.model.puzzles.Puzzle;
import sun.plugin.javascript.JSContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GUIGame extends Observable implements Observer {
    private Container container;

    private Font fontNormal = new Font("Times New Roman", Font.PLAIN, 14);
    private Font fontLog = new Font("Times New Roman", Font.BOLD, 14);

    private JTextArea log;
    private JTextField inputUser, inputPuzzle;

    // player
    private JLabel lblHealth, lblDefense, lblWeapon;
    // enemy
    private JLabel lblEnemyHealth, lblEnemyDefense, lblEnemyWeapon;

    // navigation
    private JButton btnFind, btnSaveGame, btnLook, btnListRooms, btnExitToStreet;
    // combat
    private JButton btnAttack, btnFlee;
    // puzzles
    private JButton btnPuzzleAccess, btnPuzzleHint, btnPuzzleAttempt, btnPuzzleExit;
    // inventory
    private JButton btnUseOrEquip, btnDropItem;

    private ArrayList<JComponent> grpAll = new ArrayList<>();
    private ArrayList<JComponent> grpPuzzle = new ArrayList<>();
    private ArrayList<JComponent> grpPlayer = new ArrayList<>();
    private JList listInventory;

    private int wWidth = 750, wHeight = 550;

    public GUIGame() {

        // create new window
        JFrame windowGame = new JFrame();
        // set window size
        windowGame.setSize(wWidth, wHeight);
        // for window to close properly
        windowGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // create background color
        windowGame.getContentPane().setBackground(Color.BLACK);
        // create customized layout so this be null to do so
        windowGame.setLayout(null);
        // make created window visible
        windowGame.setVisible(true);

        // container is base that can hold several things
        container = windowGame.getContentPane();

        windowGame.setLocationRelativeTo(null);
//        windowGame.setResizable(false);
        this.addObserver(this);

        makeGameWindow();
    }

    private void makeGameWindow() {
        {
            BorderLayout b = new BorderLayout();
            b.setVgap(5);
            container.setLayout(b);
        }

        // game log panel
        JPanel pnlGameLog = new JPanel();
        {
            pnlGameLog.setLayout(new BoxLayout(pnlGameLog, BoxLayout.PAGE_AXIS));
            container.add(pnlGameLog, BorderLayout.NORTH);

            log = new JTextArea(13, 55);
            {
                log.setMaximumSize(new Dimension(wWidth, 20));
                log.setBackground(Color.BLACK);
                log.setForeground(Color.WHITE);
                log.setFont(fontLog);
                {
                    log.setEditable(false);
                    log.setLineWrap(true);
                    log.setWrapStyleWord(true);
                    pnlGameLog.add(new JScrollPane(
                            log,
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                    ));
                }
            }

            inputUser = new JTextField(" Enter a command. To travel, type 'go', followed by a listed room name...");
            {
                inputUser.setMaximumSize(new Dimension(wWidth, 50));
                inputUser.setFont(fontNormal);
                inputUser.addActionListener(new Command(this, null));
                inputUser.addActionListener(a -> {
                    new Command(this, null);
                    updateGUI();
                });
                inputUser.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        inputUser.setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        inputUser.setText(" Enter 'go', followed by a listed room name...");
                    }
                });
                pnlGameLog.add(inputUser);
                grpAll.add(inputUser);
            }
        }


        // main panel
        JPanel pnlMain = new JPanel();
        {
            {
                GridLayout g = new GridLayout(1, 5);
                g.setHgap(15);
                pnlMain.setLayout(g);
            }
            grpAll.add(pnlMain);
            container.add(pnlMain, BorderLayout.CENTER);

            // navigation panel
            JPanel pnlNavigation = new JPanel();
            {
                GridLayout g = new GridLayout(5, 1);
                g.setVgap(3);
                pnlNavigation.setLayout(g);
                grpAll.add(pnlNavigation);
                pnlMain.add(pnlNavigation);

                btnLook = new JButton("Look");
                btnLook.addActionListener(new Command(this, "look"));
                pnlNavigation.add(btnLook);
                grpAll.add(btnLook);

                btnFind = new JButton("Find Items");
                btnFind.addActionListener(new Command(this, "items"));
                pnlNavigation.add(btnFind);
                grpAll.add(btnFind);

                btnListRooms = new JButton("List Rooms");
                btnListRooms.addActionListener(new Command(this, "list"));
                pnlNavigation.add(btnListRooms);
                grpAll.add(btnListRooms);

                btnExitToStreet = new JButton("Return To Street");
                btnExitToStreet.addActionListener(new Command(this, "exit"));
                pnlNavigation.add(btnExitToStreet);
                grpAll.add(btnExitToStreet);

                btnSaveGame = new JButton("Save Game");
                btnSaveGame.addActionListener(a -> {
                    SessionManager.updateFile();
                    addLogText("SAVE:\nSaved the game.");
                });
                pnlNavigation.add(btnSaveGame);
                grpAll.add(btnSaveGame);
            }

            // puzzles panel
            JPanel pnlPuzzle = new JPanel();
            {
                pnlPuzzle.setBounds(170, 450, 150, 150);
                GridLayout g = new GridLayout(5, 1);
                g.setVgap(3);
                pnlPuzzle.setLayout(g);
                grpAll.add(pnlPuzzle);
                pnlMain.add(pnlPuzzle);

                btnPuzzleAccess = new JButton("Seek Puzzle");
                btnPuzzleAccess.addActionListener(new Command(this, "puzzleAccess"));
                pnlPuzzle.add(btnPuzzleAccess);
                btnPuzzleAttempt = new JButton("Attempt");
                btnPuzzleAttempt.addActionListener(new Command(this, "puzzleAttempt"));
                pnlPuzzle.add(btnPuzzleAttempt);
                btnPuzzleHint = new JButton("Hint");
                btnPuzzleHint.addActionListener(new Command(this, "puzzleHint"));
                pnlPuzzle.add(btnPuzzleHint);
                btnPuzzleExit = new JButton("Leave Puzzle");
                btnPuzzleExit.addActionListener(new Command(this, "puzzleLeave"));
                pnlPuzzle.add(btnPuzzleExit);

                inputPuzzle = new JTextField(" Enter puzzle answer...");
                {
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
                    inputPuzzle.addActionListener(new Command(this, "puzzleAttempt"));
                    pnlPuzzle.add(inputPuzzle);
                }

                for (Component c : pnlPuzzle.getComponents()) {
                    grpPuzzle.add((JComponent) c);
                    c.setEnabled(false);
                }
                grpAll.addAll(grpPuzzle);
            }

            // combat panel
            JPanel pnlCombat = new JPanel();
            {
                pnlCombat.setLayout(new GridLayout(3, 1));
                grpAll.add(pnlCombat);
                pnlMain.add(pnlCombat);

                // player attributes
                JPanel pnlPlayer = new JPanel();
                JLabel lblPlayerAttributes;
                {
                    GridLayout g = new GridLayout(4, 1);
                    g.setVgap(3);
                    pnlPlayer.setLayout(g);
                    grpAll.add(pnlPlayer);
                    pnlCombat.add(pnlPlayer);

                    lblPlayerAttributes = new JLabel("Player Attributes");
                    pnlPlayer.add(lblPlayerAttributes);
                    lblHealth = new JLabel();
                    pnlPlayer.add(lblHealth);
                    lblDefense = new JLabel();
                    pnlPlayer.add(lblDefense);
                    lblWeapon = new JLabel();
                    pnlPlayer.add(lblWeapon);
                }

                for (Component c : pnlPlayer.getComponents()) {
                    c.setFont(fontNormal);
                    c.setForeground(Color.WHITE);
                    c.setBackground(Color.BLACK);
                    grpPlayer.add((JComponent) c);
                }
                lblPlayerAttributes.setFont(fontLog);

                // enemy attributes
                JPanel pnlEnemy = new JPanel();
                {
                    GridLayout g = new GridLayout(4, 1);
                    g.setVgap(3);
                    pnlEnemy.setLayout(g);
                    grpAll.add(pnlEnemy);
                    pnlCombat.add(pnlEnemy);

                    JLabel lblEnemyAttributes = new JLabel("Enemy Attributes:");
                    pnlEnemy.add(lblEnemyAttributes);
                    lblEnemyHealth = new JLabel();
                    pnlEnemy.add(lblEnemyHealth);
                    lblEnemyDefense = new JLabel();
                    pnlEnemy.add(lblEnemyDefense);
                    lblEnemyWeapon = new JLabel();
                    pnlEnemy.add(lblEnemyWeapon);

                    for (Component c : pnlEnemy.getComponents()) {
                        c.setFont(fontNormal);
                        c.setForeground(Color.WHITE);
                        c.setBackground(Color.BLACK);
//                    grpPlayer.add((JComponent) c);
                    }
                    lblEnemyAttributes.setFont(fontLog);
                }

                // monster buttons
                JPanel pnlButtonsMonster = new JPanel();
                {
                    GridLayout g = new GridLayout(4, 1);
                    g.setVgap(3);
                    pnlButtonsMonster.setLayout(g);
                    grpAll.add(pnlButtonsMonster);
                    pnlCombat.add(pnlButtonsMonster);

                    btnAttack = new JButton("Attack");
                    pnlButtonsMonster.add(btnAttack);
                    grpAll.add(btnAttack);

                    btnFlee = new JButton("Flee");
                    pnlButtonsMonster.add(btnFlee);
                    grpAll.add(btnFlee);
                }
            }

            // inventory panel
            JPanel pnlInventory = new JPanel();
            {
                pnlInventory.setBackground(Color.black);
                {
                    GridLayout g = new GridLayout(3, 1);
                    g.setVgap(3);
//                    BoxLayout b = new BoxLayout(pnlInventory, BoxLayout.PAGE_AXIS);
                    pnlInventory.setLayout(g);
                }
                pnlMain.add(pnlInventory);

                JLabel lblInventory = new JLabel("Inventory/Equipped Items");
                lblInventory.setFont(fontNormal);
                lblInventory.setForeground(Color.white);
                pnlInventory.add(lblInventory);

                listInventory = new JList<>();
                {
                    listInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listInventory.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                    JScrollPane listScroller = new JScrollPane(listInventory);
                    listScroller.setPreferredSize(new Dimension(250, 80));

                    pnlInventory.add(listInventory);
                    grpAll.add(listInventory);
                }

                JPanel pnlButtonsItem = new JPanel();
                {
                    GridLayout g = new GridLayout(2, 1);
                    g.setVgap(3);
                    pnlButtonsItem.setLayout(g);
                    pnlButtonsItem.setBackground(Color.black);
                    pnlInventory.add(pnlButtonsItem);

                    btnUseOrEquip = new JButton("Use/Equip");
                    pnlButtonsItem.add(btnUseOrEquip);
                    grpAll.add(btnUseOrEquip);

                    btnDropItem = new JButton("Drop");
                    pnlButtonsItem.add(btnDropItem);
                    grpAll.add(btnDropItem);
                }
            }
        }

        // quick-set all the buttons to the same colors and font style.
        for (JComponent c : grpAll) {
            if (c instanceof JButton) {
                c.setBackground(Color.BLACK);
                c.setForeground(Color.WHITE);
                c.setFont(fontNormal);
                c.setPreferredSize(new Dimension(150, 30));
                JButton b = (JButton) c;
                b.addActionListener(a -> updateGUI());
            }
            if (c instanceof JPanel) {
                JPanel p = (JPanel) c;
                p.setBackground(Color.BLACK);
            }
            if (c instanceof JTextField) {
                ((JTextField) c).addActionListener(a -> updateGUI());
            }
        }

        ArrayList<Puzzle> listP = Puzzle.getPuzzles();
        String curRoom = Room.getCurrentRoomKey();
        for (Puzzle p : listP) {
            if (p.getLocation().equals(curRoom)) {
                enablePuzzleAccess(true);
                break;
            }
        }

        updateGUI();
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
        log.append(s + "\n\n");
    }

    public void enablePuzzleAccess(boolean b) {
        btnPuzzleAccess.setEnabled(b);
    }

    public void enterPuzzle(boolean b) {
        for (JComponent c : grpAll) c.setEnabled(!b);
        for (JComponent c : grpPuzzle) c.setEnabled(b);
    }

    public String getPuzzleInput() {
        return inputPuzzle.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        lblHealth.setText("Health: " + Main.player.getHealth());
        lblDefense.setText("Defense: " + Main.player.getCurrentArmor());
        lblWeapon.setText("Weapon: " + Main.player.getCurrentWeapon());

        lblEnemyHealth.setText("Health: "); // + Monster.getCurrentMonster().getHealth());
        lblEnemyDefense.setText("Defense: "); // + Monster.getCurrentMonster().getMonDefense());
        lblEnemyWeapon.setText("Weapon: "); // + Monster.getCurrentMonster().getMonAtkValue());

        // send to the GUI
        addItems(Item.getPlayerItems());
    }

    private void updateGUI() {
        setChanged();
        notifyObservers();
    }

    private void addItems(ArrayList<Item> a) {
        DefaultListModel l = new DefaultListModel();
        for (Item i : a) {
            l.addElement(i.getItemName());
        }
        listInventory = new JList<>(l);
    }
}