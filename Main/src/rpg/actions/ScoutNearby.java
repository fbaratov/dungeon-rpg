package rpg.actions;

import rpg.*;
import rpg.doors.Door;
import rpg.npc.NPC;

import java.util.ArrayList;

/**
 * Action that tells the player about nearby NPCs
 */
public class ScoutNearby extends Action {
    /**
     * Initializes Action with "Nearby entities" as a description.
     */
    public ScoutNearby() {
        super("Nearby entities");
    }

    /**
     * Interaction with the player. Showing menus of NPCs in nearby rooms.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        System.out.println("In this room:");
        ArrayList<NPC> NPCs = player.getCurrentRoom().getNPCs();
        ArrayList<Inspectable> NPCsInspectable = new ArrayList<>(NPCs);
        Menu.printMenuInspectable(NPCsInspectable);
        System.out.println();
        for (Door door : player.getCurrentRoom().findDoors()) {
            Room room = getNextRoom(door, player);
            System.out.print("Through the ");
            door.inspect();
            NPCs = room.getNPCs();
            NPCsInspectable = new ArrayList<>(NPCs);
            Menu.printMenuInspectable(NPCsInspectable);
        }
    }

    /**
     * Private method to get the room at the other side of the door.
     *
     * @param door   Door of interest.
     * @param player Player.
     * @return Room behind the door.
     */
    private Room getNextRoom(Door door, Player player) {
        Room room1 = door.getRoom1();
        Room room2 = door.getRoom2();
        Room current = player.getCurrentRoom();
        if (current == room1) {
            return room2;
        }
        return room1;
    }
}
