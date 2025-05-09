package rpg.npc;

import rpg.Actionable;
import rpg.Player;
import rpg.Room;
import rpg.npc.helpers.Helper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract NPC class to implement all methods of all NPCs.
 */
public abstract class NPC implements Actionable, Serializable {
    /**
     * Setting ther serialVersionUID for saving.
     */
    protected static final long serialVersionUID = 23L;
    /**
     * Description of the NPC
     */
    protected String description;
    /**
     * The total maximum hp of the NPC
     */
    protected final int maxHP;
    /**
     * The ap of the NPC
     */
    protected final int ap;
    /**
     * The current HP of the NPC
     */
    protected int currentHP;
    /**
     * Boolean that allows us to evaluate the status of the npc
     */
    protected boolean alive = true;
    /**
     * The current room of the npc
     */
    protected Room currentRoom;
    /**
     * The actions the player can take upon interacting with the NPC.
     */
    protected final ArrayList<Actionable> actions = new ArrayList<>();

    /**
     * Initializes abstract NPC
     *
     * @param description String description.
     * @param maxHP       Int maximum HP.
     * @param ap          Int Attack Power.
     */
    public NPC(String description, int maxHP, int ap) {
        this.description = description;
        this.maxHP = maxHP;
        this.ap = ap;
        this.currentHP = maxHP;
    }

    /**
     * Prints the description.
     */
    public void inspect() {
        String type = getClass().getName().substring(8);
        System.out.println(type.toUpperCase() + ": " + description);
    }

    /**
     * Interaction with the player
     *
     * @param player Player.
     */
    public void interact(Player player) {
        System.out.println("Hey boss!");
    }

    /**
     * Gets the alive status of the NPC
     *
     * @return Boolean alive.
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * Sets the current room of the NPC
     *
     * @param room Room the room to set the current room to.
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Gets the current HP of the NPC.
     *
     * @return int Current hp.
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Gets the AP of the NPC
     *
     * @return Int AP
     */
    public int getAp() {
        return ap;
    }

    /**
     * Returns the maximum HP of the NPC
     *
     * @return int Max HP.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Gets the description of the NPC.
     *
     * @return String description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Adds an action to the list of actions
     *
     * @param action Actionable to be added to the ArrayList.
     */
    public void addAction(Actionable action) {
        actions.add(action);
    }

    /**
     * Removes a particular action from the action list.
     *
     * @param action Action to be removed.
     */
    public void removeAction(Actionable action) {
        actions.remove(action);
    }

    /**
     * Evaluates the damage done to the NPC.
     *
     * @param damage Int damage to be taken.
     */
    public void takeDamage(int damage) {
        if (this.alive) {
            this.currentHP -= damage;
            System.out.println(this.description + " has taken " + damage + " damage.");
            if (currentHP <= 0) {
                this.alive = false;
                System.out.println(description + " has succumbed to their wounds!");
                if (this instanceof Helper) {
                    ((Helper) this).becomeKO();
                } else {
                    currentRoom.removeNPC(this);
                }
            }
        }
    }

    /**
     * Resets NPC HP
     */
    public void resetHP() {
        currentHP = maxHP;
    }

    /**
     * Prints NPC's HP
     */
    public void printHealth() {
        System.out.println(description + "'s HP: " + currentHP + "/" + maxHP);
    }

    /**
     * Heals the NPC
     *
     * @param heal int heal amount.
     */
    public void heal(int heal) {
        this.currentHP += heal;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }
}
