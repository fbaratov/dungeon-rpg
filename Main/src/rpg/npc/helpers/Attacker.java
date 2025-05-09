package rpg.npc.helpers;

import rpg.Player;

/**
 * Generic helper that attacks with the player.
 */
public class Attacker extends Helper {
    /**
     * Initializes attacker.
     *
     * @param description String Description of the attacker.
     * @param HP          Int maximum HP of the attacker.
     * @param AP          Int maximum Attack Power of the attacker.
     */
    public Attacker(String description, int HP, int AP) {
        super(description, HP, AP);
    }

    /**
     * Interaction between the attacker and the player.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        System.out.println("*Grunts*");
    }

    /**
     * Method that is called when the player is being helped.
     *
     * @param player Player.
     */
    public void help(Player player) {
        System.out.println(this.description + " Dealt " + ap + " damage to the enemy");
        player.getFighting().takeDamage(ap);
    }

    /**
     * Method that is called when the attacker joins the party.
     *
     * @param player Player.
     */
    @Override
    public void joinParty(Player player) {
        System.out.println(description + " joins your party as an Attacker!");
    }
}
