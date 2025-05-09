package rpg.npc.enemies;

import rpg.Player;

/**
 * Interface so we can cast certain enemies to perform their abilities.
 */
public interface HasAbilities {

    /**
     * Performs a basic attack to the player.
     *
     * @param player Player.
     */
    void abilityBasic(Player player);

    /**
     * Deals AOE damage to the player and their party.
     *
     * @param player Player.
     */
    void abilityAOE(Player player);

    /**
     * Deals double damage to the player.
     *
     * @param player Player.
     */
    void abilityFocus(Player player);
}
