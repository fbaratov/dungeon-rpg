package rpg.npc.enemies;

import rpg.Player;

/**
 * Version of Enemy that has abilities.
 */
public class SpaceCowboy extends Enemy implements HasAbilities {
    /**
     * Initializes enemy with default attacks and what not.
     *
     * @param description String description of the Enemy.
     * @param HP          Int maximum HP of the Enemy.
     * @param AP          Int Attack power of Enemy.
     */
    public SpaceCowboy(String description, int HP, int AP) {
        super(description, HP, AP);
        introduction = "This dungeon ain't big enough for the two of us!";
    }

    /**
     * Performs a basic attack to the player.
     *
     * @param player Player.
     */
    @Override
    public void abilityBasic(Player player) {
        System.out.println(description + " throws his bullets at you!");
        player.takeDamage(ap);
    }

    /**
     * Deals AOE damage to the player and their party.
     *
     * @param player Player.
     */
    @Override
    public void abilityAOE(Player player) {
        System.out.println(description + " chucks a fistful of dollars!");
        doGroupDamage(player, ap / 2);
    }

    /**
     * Deals double damage to the player.
     *
     * @param player Player.
     */
    @Override
    public void abilityFocus(Player player) {
        System.out.println(description + " accidentally shoots you...");
    }
}
