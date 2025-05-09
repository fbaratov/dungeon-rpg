package rpg.actions;

import rpg.Actionable;
import rpg.Menu;
import rpg.npc.NPC;
import rpg.Player;

import java.util.ArrayList;

/**
 * Action to inspect all NPCs in the room.
 */
public class FindNPC extends Action {
    /**
     * Initializes the action with description "Look for company"
     */
    public FindNPC() {
        super("Look for company");
    }


    /**
     * Prints menu of all NPCs in the players current room.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        ArrayList<NPC> NPCs = player.getCurrentRoom().getNPCs();
        ArrayList<Actionable> actionables = new ArrayList<>(NPCs);
        System.out.println("Observing the entities around you, you see:");
        Menu.printMenuActionable(actionables, player);
    }
}
