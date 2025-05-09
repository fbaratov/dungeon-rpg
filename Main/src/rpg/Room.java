package rpg;

import rpg.doors.Door;
import rpg.npc.NPC;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Room class these are rooms in which the player can navigate.
 */
public class Room implements Inspectable, Serializable {
    // Setting serial version UID
    private static final long serialVersionUID = 21L;
    private final String description;
    private ArrayList<NPC> NPCs;
    private final ArrayList<Door> doors;
    private NPC miniBoss; //miniBoss must die or not exist for player to be able to leave room

    /**
     * Initializes room with the description
     *
     * @param description string description of the room.
     */
    public Room(String description) {
        NPCs = new ArrayList<>();
        doors = new ArrayList<>();
        miniBoss = null;
        this.description = description;
    }

    /**
     * Adds NPC as miniboss and makes it so that the player cant leave the room until the miniBoss NPC is removed from the Room
     *
     * @param npc Boss.
     */
    public void addMiniBoss(NPC npc) {
        addNPC(npc);
        miniBoss = npc;
    }

    /**
     * @return miniBoss
     */
    public NPC getMiniBoss() {
        return miniBoss;
    }

    /**
     * Checks if the miniBoss is alive
     *
     * @return Boolean status of mini_boss in room and alive.
     */
    public boolean miniBossStatus() {
        if (miniBoss == null) return false;
        return miniBoss.getAlive();
    }

    /**
     * Prints the description and number of doors.
     */
    public void inspect() {
        System.out.println(this.description + " The room has " + this.doors.size() + " doors.");
    }

    /**
     * Adds a door to the room.
     *
     * @param door A door object to add.
     */
    public void addDoor(Door door) {
        this.doors.add(door);
    }

    /**
     * Returns a list of all doors in the room.
     *
     * @return ArrayList of all doors in the room.
     */
    public ArrayList<Door> findDoors() {
        return this.doors;
    }

    /**
     * Sets the npcs into the room
     *
     * @param NPCs ArrayList of NPCs1
     */
    public void setNPCs(ArrayList<NPC> NPCs) {
        this.NPCs = NPCs;
        for (NPC npc: NPCs){
            npc.setCurrentRoom(this);
        }
    }

    /**
     * Gets all NPCs
     *
     * @return ArrayList of NPCs
     */
    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    /**
     * Adds the NPC to the room, and sets the current room for the NPC to this one.
     *
     * @param NPC NPC object
     */
    public void addNPC(NPC NPC) {
        this.NPCs.add(NPC);
        NPC.setCurrentRoom(this);
    }

    /**
     * Gets the description of the room
     *
     * @return String description
     */
    public String getDescription() {
        return this.description + " The room has " + this.doors.size() + " doors.";
    }

    /**
     * Removes the NPC from the room.
     *
     * @param NPC NPC to be removed.
     */
    public void removeNPC(NPC NPC) {
        NPCs.remove(NPC);
        if (NPC.equals(miniBoss)) {
            miniBoss = null;
        }
    }
}
