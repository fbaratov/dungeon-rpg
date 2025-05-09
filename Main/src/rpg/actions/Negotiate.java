package rpg.actions;

import rpg.*;
import rpg.npc.enemies.Enemy;

/**
 * Action to add an NPC to the players party using currency.
 */
public class Negotiate extends Action {
    /**
     * Initializes action with description "Negotiate"
     */
    public Negotiate() {
        super("Negotiate");
    }

    /**
     * Makes player negotiate with the enemy to join party.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        //get relevant info
        Enemy enemy = player.getFighting();
        double cost = (double) 5 * enemy.getCurrentHP() / 20;
        int rand = 10 - Rand.randInt() + (int) (cost + 0.5);
        int coins = player.getCoins();


        if (rand > coins) { //if not enough coins, then negotiation fails
            System.out.println("That costs " + rand + " coins! (You have " + coins + ")");
            enemy.doDamage(player);
        } else { //if the player has enough coins, negotiation succeeds
            //remove coins
            player.removeCoins(rand);
            System.out.println("You now have " + (coins - rand) + " coins!");

            //add enemy to party
            player.addToParty(enemy.makeAttacker());
            player.setFighting(null);
        }
    }
}
