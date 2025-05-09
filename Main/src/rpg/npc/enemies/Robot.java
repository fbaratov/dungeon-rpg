package rpg.npc.enemies;

import rpg.Player;

/**
 * Version of Enemy that has abilities.
 */
public class Robot extends Enemy implements HasAbilities {

    /**
     * Initializes enemy with default attacks and what not.
     *
     * @param description String description of the Enemy.
     * @param HP          Int maximum HP of the Enemy.
     * @param AP          Int Attack power of Enemy.
     */
    public Robot(String description, int HP, int AP) {
        super(description, HP, AP);
        introduction = "Beep boop. The robot moves mechanically, yet menacingly.";
    }

    /**
     * Performs a basic attack to the player.
     *
     * @param player Player.
     */
    @Override
    public void abilityBasic(Player player) {
        System.out.println(description + " extends its punch to hit you!");
        player.takeDamage(ap);
    }

    /**
     * Deals AOE damage to the player and their party.
     *
     * @param player Player.
     */
    @Override
    public void abilityAOE(Player player) {
        System.out.println(description + " vapes on you and your party!");
        doGroupDamage(player, ap / 2);
    }

    /**
     * Deals double damage to the player.
     *
     * @param player Player.
     */
    @Override
    public void abilityFocus(Player player) {
        System.out.println(description + " shoots you with a gun.");
        player.takeDamage(ap * 2);
    }
}
