package rpg.doors;


import rpg.Player;
import rpg.Room;
import rpg.player_effects.Item;

/**
 * A Locked version of Door.
 */
public class LockedDoor extends Door {
    private final String keyType;
    private final String  skeletonKey = "Skeleton key"; //key that unlocks all doors
    private boolean locked;
    /**
     * Initializes a door that is locked until a player unlocks it using an Item (a key) of a specified type
     * @param room1 Room 1
     * @param room2 Room 2
     * @param description Door description
     * @param keyType Item type that unlocks door
     */
    public LockedDoor(Room room1, Room room2, String description, String keyType) {
        super(room1,room2,description);
        locked = true;
        this.keyType = keyType;
    }

    /**
     * Checks if the player has a key to the door
     * @param player Player
     * @return The key, or, if the player does not have one, null
     */
    private Item playerHasKey(Player player) {
        for(Item item: player.getItems()) {
            if(item.getType().equals(keyType) ||
               item.getType().equals(skeletonKey)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Unlocks door and removes key from player inventory (unless it is a master key)
     * @param player Player
     * @param key Key to the door
     */
    private void unlock(Player player, Item key) {
        locked = false;
        if(!key.getType().equals(skeletonKey)) {
            player.removeItem(key);
        }
    }

    /**
     * Prints the door description plus whether or not it is locked
     */
    public void inspect() {
        if(locked) {
            System.out.println("LOCKED: " + description);
        } else {
            System.out.println(description);
        }
    }

    /**
     * Checks if the player can unlock/pass through the door and if so, lets them through
     * @param player Player.
     */
    public void interact(Player player) {
        if(locked) {
            Item key = playerHasKey(player);
            if(key == null) {
                System.out.println("This door is locked! Get the key to unlock it!");
                return;
            } else {
                System.out.println("You unlock the door!");
                unlock(player,key);
            }
        }
        super.interact(player);
    }
}