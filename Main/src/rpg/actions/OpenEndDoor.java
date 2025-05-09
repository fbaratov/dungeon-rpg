package rpg.actions;

import rpg.doors.Door;
import rpg.Player;
import rpg.Room;

/**
 * Action that will move the player through a new room that will end the game, alternative ending.
 */
public class OpenEndDoor extends Action {
    /**
     * Initializes the action with a specific description.
     */
    public OpenEndDoor() {
        super("Enter guarded room.");
    }

    /**
     * Creates an end door at the players location. And makes the player interact with it.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        Door endDoor = new Door(player.getCurrentRoom(), new Room("End room."), "A mysterious heavy black door.");
        endDoor.interact(player);
    }
}
