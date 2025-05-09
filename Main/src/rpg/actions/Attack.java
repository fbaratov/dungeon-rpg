package rpg.actions;

import rpg.Rand;
import rpg.npc.enemies.Enemy;
import rpg.Player;

/**
 * Action to perform a basic attack on the enemy.
 */
public class Attack extends Action {
    /**
     * Initializes action with description "Attack"
     */
    public Attack() {
        super("Attack");
    }

    /**
     * Player attacks the NPC they are targeting.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        Enemy enemy = player.getFighting();
        System.out.println("You attack!");
        player.doDamage(enemy);
        if (enemy.getAlive()) {
            enemy.doDamage(player);
        }
        player.combatHelp();
        if (!enemy.getAlive()) {
            player.setFighting(null);
            player.addCoins((int) (Rand.randInt() * enemy.getAp() / 10.0));
        }
    }
}
