package rpg.npc.enemies;

import rpg.Player;

/**
 * Version of Enemy that has abilities.
 * Also the boss name tells us that this is a boss (for the coders)
 */
public class Boss extends Enemy implements HasAbilities {

    /**
     * Initializes the boss
     *
     * @param description String description.
     * @param maxHP       Int maximum HP.
     * @param AP          Int Attack Power.
     */
    public Boss(String description, int maxHP, int AP) {
        super(description, maxHP, AP);
        this.removeAction(this.actions.get(1));
        this.removeAction(this.actions.get(1));

        introduction = "I AM GURA FEAR ME MERE MORTAL";
    }

    /**
     * Prints boss description
     */
    public void inspect() {
        System.out.println("BOSS: " + description);
    }

    /**
     * Deals heavy damage to the player.
     *
     * @param player Player.
     */
    public void abilityBasic(Player player) {
        System.out.println(description + " strikes you with lightning!");
        player.takeDamage(ap * 2);
    }

    /**
     * Deals damage to the whole party.
     *
     * @param player Player.
     */
    public void abilityAOE(Player player) {
        System.out.println(description + " sends a giant wave at you and your party!");
        int damage = ap - 6;
        doGroupDamage(player, damage);
    }

    /**
     * Deals normal damage to the player.
     *
     * @param player Player.
     */
    public void abilityFocus(Player player) {
        System.out.println(description + " strikes you with a spear!");
        player.takeDamage(ap);
    }
}
