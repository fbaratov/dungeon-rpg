package rpg.npc.helpers;

import rpg.Menu;
import rpg.Player;
import rpg.actions.Recruit;
import rpg.npc.NPC;

/**
 * Helper extension of NPC, used for NPCs that can join the player's side.
 */
public abstract class Helper extends NPC {
    /**
     * Boolean that tells us if the helper is KO or not.
     */
    protected boolean ko;

    /**
     * Initializes helper.
     *
     * @param description String description of the helper.
     * @param maxHP       Int maximum HP of the helper.
     * @param AP          Int Attack Power of the helper.
     */
    public Helper(String description, int maxHP, int AP) {
        super(description, maxHP, AP);
        ko = false;
        addAction(new Recruit());
    }

    /**
     * Player interacts with the helper.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        if (!player.isInParty(this)) {
            System.out.println("What do you wish to do?");
            Menu.printMenuActionable(actions, player, true);
        }
    }

    /**
     * Prints role and description of the helper.
     */
    public void inspect() {
        String role = getClass().getName().substring(16);
        System.out.println(role.toUpperCase() + ": " + description);
    }

    /**
     * Help function for the helper.
     *
     * @param player Player.
     */
    public void help(Player player) {
        System.out.println("I'll help you for a spoonful of crack.\n");
    }

    /**
     * Effect of joining the party.
     *
     * @param player Player.
     */
    public void joinParty(Player player) {
        System.out.println(getDescription() + " has joined your party!\n");
    }

    /**
     * Makes the helper KO
     */
    public void becomeKO() {
        ko = true;
    }

    /**
     * Makes the helper not KO anymore
     */
    public void endKO() {
        ko = false;
        alive = true;
    }

    /**
     * Returns the KO status of the Helper.
     *
     * @return Boolean of whether they are ko or not.
     */
    public boolean getKOStatus() {
        return ko;
    }


}
