package rpg.npc;

import rpg.Player;

/**
 * NPC which gives a hint to the player.
 */
public class Hint extends NPC {
    private final String hint;

    /**
     * Initializes NPC hind with a desciption and a hint
     *
     * @param description String description of hint NPC
     * @param hint        String hint they provide
     */
    public Hint(String description, String hint) {
        super(description, 999, 0);
        this.hint = hint;
    }

    /**
     * Interaction between hint NPC and player
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        System.out.println(hint);
    }
}
