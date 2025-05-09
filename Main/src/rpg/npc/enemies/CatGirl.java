package rpg.npc.enemies;

import rpg.Player;

/**
 * Version of Enemy that has abilities.
 */
public class CatGirl extends Enemy implements HasAbilities {
    /**
     * Initializes enemy with default attacks and what not.
     *
     * @param description String description of the Enemy.
     * @param HP          Int maximum HP of the Enemy.
     * @param AP          Int Attack power of Enemy.
     */
    public CatGirl(String description, int HP, int AP) {
        super(description, HP, AP);
        introduction = "The enemy stares you down...";
    }

    /**
     * Performs a basic attack to the player.
     *
     * @param player Player.
     */
    @Override
    public void abilityBasic(Player player) {
        System.out.println(description + " scratches you!");
        player.takeDamage(ap);
    }

    /**
     * Deals AOE damage to the player and their party.
     *
     * @param player Player.
     */
    @Override
    public void abilityAOE(Player player) {
        System.out.println(description + " attacks your party!");
        doGroupDamage(player, ap / 2);
    }

    /**
     * Deals double damage to the player.
     *
     * @param player Player.
     */
    @Override
    public void abilityFocus(Player player) {
        System.out.println(description + " slaps you!");
        player.takeDamage(ap * 2);
    }
}
