package rpg.doors;

import rpg.Actionable;
import rpg.Player;
import rpg.Room;
import rpg.npc.NPC;

import java.io.Serializable;

/**
 * Doors which can be moved through when interacted with.
 */
public class Door implements Actionable, Serializable {
    /**
     * Setting serial version UID
     */
    protected static final long serialVersionUID = 22L;
    private final Room room1;
    private final Room room2;
    /**
     * Description of the door.
     */
    protected final String description;


    /**
     * Initializes a Door. Adds the door to the rooms.
     *
     * @param room1       The first Room it is connected to.
     * @param room2       The second Room it is connected to.
     * @param description String description of the door.
     */
    public Door(Room room1, Room room2, String description) {
        this.room1 = room1;
        this.room2 = room2;
        this.description = description;
        room1.addDoor(this);
        room2.addDoor(this);

    }

    /**
     * Prints the description.
     */
    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Player walks through the door and moves room.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        Room room = player.getCurrentRoom();

        if (room.miniBossStatus()) {
            NPC miniBoss = room.getMiniBoss();
            System.out.println("You cannot leave the room until " + miniBoss.getDescription() + " is dealt with!");
            return;
        }
        if (room == room1) {
            player.moveRoom(room2);
        } else if (room == room2) {
            player.moveRoom(room1);
        }
    }

    /**
     * Get the first room.
     *
     * @return Room room1
     */
    public Room getRoom1() {
        return room1;
    }

    /**
     * Get the second room.
     *
     * @return Room room2
     */
    public Room getRoom2() {
        return room2;
    }

    /**
     * Gets the description of the room.
     *
     * @return String description.
     */
    public String getDescription() {
        return this.description;
    }
}
