package rpg.actions;

import rpg.*;

/**
 * Action that allows the player to exit combat
 */
public class Retreat extends Action {
    /**
     * Initializes action with description "Retreat"
     */
    public Retreat() {
        super("Retreat");
    }

    /**
     * Stops combat between the npc and player.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        int rand = Rand.randInt();
        if (rand * 3 < player.getFighting().getCurrentHP()) {
            player.getFighting().doDamage(player);
        }
        player.setFighting(null);
        System.out.println("You escape like a coward!");
    }
}
