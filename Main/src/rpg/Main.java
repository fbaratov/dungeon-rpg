package rpg;

import rpg.actions.Load;
import rpg.npc.enemies.*;

public class Main {
    /**
     * Main function for initializing everything.
     *
     * @param args Standard.
     */
    public static void main(String[] args) {
        int response;
        do {
            System.out.println("Would you like to load a game(0) or create a new game(1)?");
            response = Menu.scanInt();
            if (response == 0) {
                new Load().interact(new Player("default", new Room("void"), 1, 1, 1));
            }
        } while (response != 1);

        //initialize player and starting room
        Room room0 = new Room("Clearing with a door seemingly leading to a dungeon.");


        Player player = Initializer.initialize_player(room0);
        Boss gura = Initializer.initializeDungeon(room0,player);
        new Game(player, gura);
    }





}
