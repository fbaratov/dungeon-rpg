package rpg.npc.helpers;

import rpg.Rand;
import rpg.Player;

/**
 * Helper which heals the player when they need help.
 */
public class Healer extends Helper {

    /**
     * Initializes Healer.
     *
     * @param description String description of Healer.
     * @param HP          Int Maximum HP of Healer.
     * @param AP          Int Attack power of healer.
     */
    public Healer(String description, int HP, int AP) {
        super(description, HP, AP);
    }

    /**
     * Heals the player and helpers for an amount between 0 and AP
     *
     * @param player Player.
     */
    public void help(Player player) {
        if (player.isInParty(this)) {
            int rand = Rand.randInt();
            int amount = this.ap * rand / 10;
            player.getHeal(amount);
            System.out.println(description + " heals you and your party for " + amount + " HP!\n");
            for (Helper helper : player.getParty()) {
                helper.heal(amount);
            }
        }
    }

    /**
     * Effect of healer joining the party
     *
     * @param player Player.
     */
    @Override
    public void joinParty(Player player) {
        System.out.println("\"I'll keep you as alive as possible.\"");
    }
}
