package rpg.actions;

import rpg.Actionable;
import rpg.doors.Door;
import rpg.Menu;
import rpg.Player;

import java.util.ArrayList;

/**
 * Action to get all the doors in a room.
 */
public class FindDoors extends Action {
    /**
     * Initializes the action with the definition "Look for a way out"
     */
    public FindDoors() {
        super("Look for a way out");
    }

    /**
     * Prints menu of all doors.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        ArrayList<Door> doors = player.getCurrentRoom().findDoors();
        ArrayList<Actionable> doorsActionable = new ArrayList<>(doors);
        System.out.println("You look around for a way out. You see:");
        Menu.printMenuActionable(doorsActionable, player);
    }
}
