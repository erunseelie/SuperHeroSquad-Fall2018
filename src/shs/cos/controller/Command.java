package shs.cos.controller;

import shs.cos.model.Room;
import shs.cos.model.items.Item;
import shs.cos.model.puzzles.Puzzle;
import shs.cos.model.entities.*;
import shs.cos.view.gui.GUIGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

// Gets the command entered.
public class Command implements ActionListener
{
	private int turnCount;
	private GUIGame gui;
	private String action;

	public Command(GUIGame gui, String action)
	{
		this.gui = gui;
		this.action = action;
	}

	// The default ActionEvent fired when the enter key is pressed for text input,
	// or when a button is clicked.
	public void actionPerformed(ActionEvent e)
	{
		String s;
		if (action != null)
		{
			switch (action)
			{
			case "look":
				s = cmdLook();
				break;
			case "items":
				s = cmdItems();
				break;
			case "take":
				s = cmdTake();
				break;
			case "exit":
				s = cmdExit();
				break;
			case "list":
				s = cmdList();
				break;
			case "puzzleAccess":
				s = cmdPuzzleEnter(false);
				break;
			case "puzzleLeave":
				s = cmdPuzzleEnter(true);
				break;
			case "puzzleAttempt":
				s = cmdPuzzleAttempt();
				break;
			case "puzzleHint":
				s = cmdPuzzleHint();
				break;
			case "attack":
				s = cmdAttack();
				break;
			default:
				s = "You don't know how to do that.";
				break;
			}
			gui.addLogText(s);
		} else
		{
			// textual commands
			String[] command = gui.getInput().split(" ");
			int size = command.length;

			String verb = command[0];
			String object = "";
			if (size > 1)
				object = command[1];
			// String amount = "";
			// if (size > 2) amount = command[2];

			// TODO: add full command parsing
			switch (verb)
			{
			case "look":
				s = cmdLook();
				break;
			case "items":
				s = cmdItems();
				break;
			case "take":
				s = cmdTake();
				break;
			case "exit":
				s = cmdExit();
				break;
			case "go":
				StringBuilder location = object == null ? null : new StringBuilder(object);
				for (int i = 2; i < size; i++)
				{
					location = (location == null ? new StringBuilder("null") : location).append(" ").append(command[i]);
				}
				s = commandGo(location == null ? null : location.toString());
				break;
			case "list":
				s = cmdList();
				break;
			default:
				s = "You're unable to do that.";
				break;
			}

			gui.addLogText(s);
		}

		// check for inCombat, and if so, give monster a turn to deal damage. If there
		// is no monster, will set inCombat to false
		Room currentRoom = mapRooms.get(Room.getCurrentRoomKey());
		if (!currentRoom.getMonsters().isEmpty())
		{
			turnCount++;
			Main.player.setInCombat(true);
			Monster currentMonster = mapMonsters.get(currentRoom.getMonsters().get(0));

			if (turnCount > 0)
			{
				int currentArmor;

				if (Main.player.getCurrentArmor().isEmpty())
				{
					currentArmor = 0;
				}

				else
				{
					currentArmor = mapItems.get(Main.player.getCurrentArmor()).getItemStat();
				}

				int damageTaken = currentMonster.getMonAtkValue() - currentArmor;

				if (currentMonster.getMonSpeed() == 2)
				{
					damageTaken = damageTaken * 2;
				}

				if (damageTaken < 0)
				{
					damageTaken = 0;
				}

				Main.player.applyDamage(damageTaken);

				if (Main.player.getHealth() <= 0)
				{
					// player loses game at this point. Should exit game, or reset or whatever needs
					// to happen.
					s = "You have died. Later nerd";
				}

				if (currentMonster.getMonSpeed() == 2)
				{
					s = "You have taken " + damageTaken + " damage from " + currentMonster.getName() + "'s "
							+ currentMonster.getAtkName() + "\n He's so quick, he hit you twice!";
				} else
				{
					s = "You have taken " + damageTaken + " damage from " + currentMonster.getName() + "'s "
							+ currentMonster.getAtkName();
				}

				gui.addLogText(s);
				gui.update(gui, action);
			}
		}

		else
		{
			Main.player.setInCombat(false);
		}

	}

	private String cmdAttack()
	{
		if(!(mapRooms.get(Room.getCurrentRoomKey()).getMonsters().isEmpty()))
		{
			Monster currentMonster = mapMonsters.get(mapRooms.get(Room.getCurrentRoomKey()).getMonsters().get(0));

			int damageToDeal = (int) (mapItems.get(Main.player.getCurrentWeapon()).getItemStat()
					* (100 - currentMonster.getMonDefense()) / 100);

			// if
			// (Item.getPlayerItems().contains(mapItems.get(currentMonster.getMonWeakness())));
			// {
			// damageToDeal = damageToDeal * 2;
			// gui.addLogText("Critical!");
			// }

			if (currentMonster.getMonSpeed() == 0)
			{
				damageToDeal = damageToDeal * 2;
				gui.addLogText("He's so slow you hit him twice!");
			}

			currentMonster.applyDamage(damageToDeal);

			return "ATTACK: \n" + "You dealt " + damageToDeal + " damage to " + currentMonster.getName() + "\n"
					+ currentMonster.getName() + " has " + currentMonster.getHealth() + " health left.";
		}
		return "There is no one here to fight.";
	}

	private TreeMap<String, Room> mapRooms = Room.getMap();
	private TreeMap<String, Monster> mapMonsters = Monster.getMonsterList();
	private TreeMap<String, Item> mapItems = Item.getItemIDList();

	private String cmdLook()
	{
		return "LOOK: \n" + mapRooms.get(Room.getCurrentRoomKey()).getDesc();
	}

	private String cmdItems()
	{
		String s = "";
		for (Map.Entry<String, Item> entryItem : Item.getItemIDList().entrySet())
		{
			Item i = entryItem.getValue();
			if (i.getLocation() != null)
			{
				if (i.getLocation().equals(Room.getCurrentRoomKey()))
				{
					s = i.getItemName() + ": " + i.getItemDesc();
					break;
				} else
					s = "There doesn't seem to be anything around.";
			}
		}
		return "ITEMS:\n" + s;
	}

	private String cmdExit()
	{
		Room.setCurrentRoom("B0R0");
		gui.enablePuzzleAccess(false);
		Main.player.setInCombat(false);
		return "EXIT:\n" + "You have returned to the street.";
	}

	private String cmdList()
	{
		StringBuilder s = new StringBuilder();

		// if (!Main.player.getInCombat())
		// {
		ArrayList<String> rooms = getRoomConnections();

		for (String r : rooms)
		{
			Room room = mapRooms.get(r);
			s.append(room.getRoomName()).append(", ");
		}

		s.deleteCharAt(s.length() - 1);
		s.deleteCharAt(s.length() - 1);
		// }

		// else
		// {
		// s.append("You're a little busy fighting right now");
		// }

		return "LIST: \n" + s;

	}

	private String commandGo(String location)
	{
		ArrayList<String> rooms = getRoomConnections();
		String s = "";

		for (String r : rooms)
		{
			if (mapRooms.get(r).getRoomName().toLowerCase().equals(location))
			{
				Room.setCurrentRoom(r);
				ArrayList<Puzzle> listP = Puzzle.getPuzzles();
				String curRoom = Room.getCurrentRoomKey();
				for (Puzzle p : listP)
				{
					if (p.getLocation().equals(curRoom))
					{
						gui.enablePuzzleAccess(true);
						break;
					} else
						gui.enablePuzzleAccess(false);
				}
				turnCount = -1;
				s = "You mosey through the dust. You have arrived at your destination...";
				s += "\n" + cmdLook().substring(6);

				break;
			} else
				s = "That place doesn't seem to be nearby.";
		}
		return "GO: " + mapRooms.get(Room.getCurrentRoomKey()).getRoomName() + "\n" + s;
	}

	private ArrayList<String> getRoomConnections()
	{
		return mapRooms.get(Room.getCurrentRoomKey()).getConnections();
	}

	private String cmdPuzzleEnter(boolean exit)
	{
		String s = "";
		if (exit)
		{
			Puzzle.setAttempting(false);
			gui.enterPuzzle(false);
			gui.enablePuzzleAccess(true);
			s = "You leave the puzzle for now.";
		} else
		{
			ArrayList<Puzzle> listP = Puzzle.getPuzzles();
			String curRoom = Room.getCurrentRoomKey();
			for (Puzzle p : listP)
			{
				if (p.getLocation().equals(curRoom))
				{
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

	private String cmdPuzzleAttempt()
	{
		String input = gui.getPuzzleInput();
		AtomicReference<String> s = new AtomicReference<>("");
		Puzzle p = Puzzle.getCurrentPuzzle();
		if (p.attempt(input))
		{
			Item i = Item.getItemIDList().get(p.getBoon());
			if (i == null)
				i = new Item();
			Item.addPlayerItem(i);
			Puzzle.clearPuzzle(p);
			cmdPuzzleEnter(true);
			gui.enablePuzzleAccess(false);
			s.set("You solved the puzzle!\nYou found:\n" + i.getItemName());
		} else
		{
			if (p.getCounter() >= 3)
			{
				Main.player.applyDamage(5);
				s.set("Ouch! Careful! Something's jabbed you in the finger.");
			} else
			{
				s.set("That's not right - best take caution.");
				p.setCounter(p.getCounter() + 1);
			}
		}
		return "ATTEMPT:\n" + s;
	}

	private String cmdPuzzleHint()
	{
		Main.player.applyDamage(10);
		return "HINT:\n" + Puzzle.getCurrentPuzzle().getClue();
	}

	private String cmdTake()
	{
		// TODO: "The player must have already located the item using the CMD_Look." ???
		// NYI
		AtomicReference<String> s = new AtomicReference<>("");
		AtomicReference<String> i = new AtomicReference<>("");
		for (Map.Entry<String, Item> entry : Item.getItemIDList().entrySet())
		{
			String k = entry.getKey();
			Item v = entry.getValue();
			if (v.getLocation().equals(Room.getCurrentRoomKey()))
			{
				Item.addPlayerItem(v);
				// TODO: check for multiple instances of one item
				i.set(k);
				s.set("You picked up: " + v.getItemName().toLowerCase() + ". It can be used as: "
						+ v.getItemType().toLowerCase() + ".");
				break;
			} else
			{
				s.set("There's nothing around that you can take.");
			}
		}
		Item.getItemIDList().remove(i.toString());
		return "TAKE:\n" + s;
	}

}
