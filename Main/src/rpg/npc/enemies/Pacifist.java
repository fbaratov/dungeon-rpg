package rpg.npc.enemies;

import rpg.Player;

/**
 * Version of Enemy that has abilities.
 */
public class Pacifist extends Enemy implements HasAbilities{

    /**
     * Creates a punching bag NPC
     * @param description description of punching bag
     */
    public Pacifist(String description) {
        super(description, 10, 0);
        this.description = description;
        //peace is not an option
        for(int i = 0; i < 3; i++) actions.remove(1);
        introduction = "The kindest person you've ever seen greets you and offers you some freshly baked cookies!";
    }

    /**
     * Performs a basic attack to the player.
     *
     * @param player Player.
     */
    public void abilityBasic(Player player) {
        System.out.println(description + " tells you about his fishing trip with his son!");
    }

    /**
     * Deals AOE damage to the player and their party.
     *
     * @param player Player.
     */
    public void abilityAOE(Player player) {
        System.out.println(description + " says you remind him of Mr. Rogers, because you're so nice!");
    }

    /**
     * Deals double damage to the player.
     *
     * @param player Player.
     */
    public void abilityFocus(Player player) {
        System.out.println(description + " calls you a very nice person!");
    }
}
