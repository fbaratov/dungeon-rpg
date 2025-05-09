package rpg.actions;

import rpg.Player;
import rpg.npc.helpers.Helper;
import rpg.npc.NPC;

/**
 * Action that puts an NPC into the party of the player.
 */
public class Recruit extends Action {

    /**
     * Initializes Action with description "Recruit to party"
     */
    public Recruit() {
        super("Recruit to party");
    }

    /**
     * Gives the player a chance to convince the enemy to join their party.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        NPC npc = player.getInteractingNPC();
        System.out.println("You recruited " + npc.getDescription() + " to your party!");
        player.addToParty((Helper) npc);
    }

}
