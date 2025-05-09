package rpg.actions;

import rpg.*;

/**
 * Action that allows the player to convert an Enemy into an Attacker and have them join the party.
 */
public class Charm extends Action {
    /**
     * Initializes the action with the description "Charm"
     */
    public Charm() {
        super("Charm");
    }

    /**
     * Gives the player a chance to charm the enemy into doing their bidding.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        int rand = Rand.randInt();
        if (rand > 6) {
            player.addToParty(player.getFighting().makeAttacker());
            player.setFighting(null);
        } else {
            player.getFighting().doDamage(player);
        }
    }
}
