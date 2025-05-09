package rpg;

import rpg.actions.*;
import rpg.npc.enemies.Boss;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Center of where the game is happening, adds default actions and what not.
 * It ends and starts the game and plays into the main loop.
 */
public class Game{
    /**
     * Scanner variable for the entire game this way it is centralized and can be closed with ease.
     */
    public final static Scanner scanner = new Scanner(System.in);
    final ArrayList<Actionable> defaultActions = new ArrayList<>(); //default menu actions

    /**
     * Initializes game and default actions
     *
     * @param player       Player.
     */
    public Game(Player player, Boss boss) {
        setDefaultActions();

        title_card(player.getName());
        while (boss.getAlive() &&
               !player.getCurrentRoom().getDescription().equals("End room.") &&
               !player.getCurrentRoom().getDescription().startsWith("Outside") &&
                player.getCurrentHP() > 0) {
            System.out.println("What do you want to do?");
            Menu.printMenuActionable(defaultActions, player, false);
        }
        if (player.getCurrentRoom().getDescription().startsWith("Outside")) {
            StoryTeller.epilogueLeave();
            System.out.println("Thank you for playing!");
            System.exit(0);
        } else if (!boss.getAlive()) {
            StoryTeller.epilogueKill();
        } else if (player.getCurrentHP() > 0 && boss.getAlive()){
            StoryTeller.epilogueFriend();
        } else {
            StoryTeller.gameOver();
        }
        scanner.close();
    }

    /**
     * Prints a cool title card and prologue
     * @param name String name of player.
     */
    private void title_card(String name) {
        // False for no intro true to keep the intro
        boolean intro = false;
        //stuff for printing a title card below
        //noinspection ConstantConditions
        if (intro) {
            StoryTeller.printEmptyLines(4);

            String[] lines = new String[] {"THERE EXISTS AN EVIL CREATURE IN THIS DUNGEON.",
                    "IT  THREATENS THE VERY FABRIC OF  OUR REALITY.",
                    "DURING OUR REALM'S DARKEST HOUR, AN ADVENTURER",
                    "ANSWERS THE CALL TO GLORY AND SETS FOOT INSIDE",
                    "THE DUNGEON, READY TO FACE THE DANGERS WITHIN."};

            StoryTeller.printStory(lines);
            StoryTeller.printEmptyLines(3);
            System.out.println("THIS IS THE STORY OF " + name.toUpperCase() + ".");
            StoryTeller.printEmptyLines(1);
        }
    }

    private void setDefaultActions(){
        defaultActions.add(new LookAround());
        defaultActions.add(new FindDoors());
        defaultActions.add(new FindNPC());
        defaultActions.add(new InspectParty());
        defaultActions.add(new ViewPlayer());
        defaultActions.add(new QuickSave());
        defaultActions.add(new QuickLoad());
        defaultActions.add(new Save());
        defaultActions.add(new Load());
    }

}
