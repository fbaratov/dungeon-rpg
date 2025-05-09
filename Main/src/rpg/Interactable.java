package rpg;

/**
 * Tells us if you can interact with something, it's redundant but required for the assignment.
 */
public interface Interactable {

    /**
     * Interaction between the interactable and the player.
     *
     * @param player Player.
     */
    void interact(Player player);

}
