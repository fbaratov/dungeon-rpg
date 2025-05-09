package rpg.actions;

import rpg.Player;
import rpg.Menu;

/**
 * Action to inspect the players party.
 */
public class InspectParty extends Action {
    /**
     * Initializes Action with definition "Check out your party"
     */
    public InspectParty() {
        super("Check out your party");
    }

    /**
     * Prints menu of players party.
     *
     * @param player Player.
     */
    public void interact(Player player) {
        System.out.println("You take a look at your comrades.");
        Menu.printMenuParty(player);
    }
}
