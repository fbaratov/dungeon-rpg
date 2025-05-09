package rpg.doors;

import rpg.Player;
import rpg.Room;
import rpg.doors.Door;


/**
 * A version of Door that has a cutscene.
 */
public class CutsceneDoor extends Door {
    private final String[] cutscene;
    private boolean firstTime;

    /**
     * Initializes a door where special dialogue is printed the first time a player passes through it
     * @param room1 Room 1
     * @param room2 Room 2
     * @param description Description of door
     * @param cutscene Door cutscene
     */
    public CutsceneDoor(Room room1, Room room2, String description, String[] cutscene) {
        super(room1,room2,description);
        this.cutscene = cutscene;
        firstTime = true;
    }

    /**
     * Player passes through door, dialogue is printed first time around
     */
    public void interact(Player player) {
        if(firstTime) {
            for (String str : cutscene) {
                System.out.println(str);
            }
            firstTime = false;
        }
        super.interact(player);
    }
}