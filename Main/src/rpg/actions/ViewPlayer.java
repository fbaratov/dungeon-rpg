package rpg.actions;

import rpg.Player;
import rpg.SpecialMenu;

/**
 * Action that allows the player to inspect themselves.
 */
public class ViewPlayer extends Action {
    /**
     * Initializes Action with definition
     */
    public ViewPlayer() {
        super("Take a look at yourself");
    }

    /**
     * Allows player to enter player menu.
     *
     * @param player Player.
     */
    public void interact(Player player) {
        SpecialMenu.printPlayerMenu(player);
    }
}
