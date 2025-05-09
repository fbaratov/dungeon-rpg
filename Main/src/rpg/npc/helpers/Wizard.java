package rpg.npc.helpers;


import rpg.player_effects.Buff;
import rpg.Player;

/**
 * NPC that gives buffs to the player.
 */
public class Wizard extends Helper {
    private final Buff buff;

    /**
     * Initializes wizard
     *
     * @param description String of the description.
     * @param HP          Int maximum HP.
     * @param AP          Int Attack Power.
     * @param buff        Buff of the wizard.
     */
    public Wizard(String description, int HP, int AP, Buff buff) {
        super(description, HP, AP);
        this.buff = buff;
    }

    /**
     * Performs the helping action on the player.
     *
     * @param player Player.
     */
    @Override
    public void help(Player player) {
        player.getFighting().takeDamage(ap);
    }

    /**
     * Performs actions to join the party.
     *
     * @param player Player.
     */
    @Override
    public void joinParty(Player player) {
        System.out.println("I will make you stronger!");
        player.addBuff(buff);
        buff.interact(player);
    }
}