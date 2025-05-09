package rpg.actions;

import rpg.*;

/**
 * Action to get room description.
 */
public class LookAround extends Action {

    /**
     * Initializes action with definition "Look around"
     */
    public LookAround() {
        super("Look around");
    }

    /**
     * Interacts
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        System.out.println("You see: " + player.getCurrentRoom().getDescription() + "\n");
    }
}
