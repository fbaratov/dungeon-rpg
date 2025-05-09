package rpg.actions;

import rpg.Menu;
import rpg.npc.NPC;
import rpg.Player;

import java.util.ArrayList;

/**
 * Action that evaluates nearby enemies.
 */
public class EvaluateNearby extends Action {
    /**
     * Initializes the action with the description "Status of enemies in this room"
     */
    public EvaluateNearby() {
        super("Status of enemies in this room");
    }

    /**
     * Prints menu of nearby NPCs.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        ArrayList<NPC> NPCs = player.getCurrentRoom().getNPCs();
        System.out.println("In this room:");
        Menu.printMenuNPC(NPCs);
    }
}
