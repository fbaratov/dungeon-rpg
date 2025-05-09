package rpg.npc;

import rpg.Menu;
import rpg.Player;
import rpg.actions.EvaluateNearby;
import rpg.actions.ScoutNearby;

import java.util.ArrayList;

/**
 * Scout NPC which will tell the player what surrounds him by default.
 */
public class Scout extends NPC {

    /**
     * Initializes scout.
     *
     * @param description String description of the scout.
     * @param HP          Int maximum HP of the scout.
     * @param AP          Int Attack power of the scout.
     */
    public Scout(String description, int HP, int AP) {
        super(description, HP, AP);
        this.actions.add(new ScoutNearby());
        this.actions.add(new EvaluateNearby());
    }

    /**
     * Scouts interaction with the player.
     *
     * @param player Player.
     */
    public void interact(Player player) {
        System.out.println("What would you like to know?");
        Menu.printMenuActionable(new ArrayList<>(actions), player);
        System.out.println();
    }
}
