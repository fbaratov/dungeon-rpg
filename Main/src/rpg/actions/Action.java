package rpg.actions;

import rpg.*;

import java.io.Serializable;

/**
 * Abstract class that will implement all basic functions for actions.
 */
public abstract class Action implements Actionable, Serializable{
    /**
     * Description of the action.
     */
    protected final String description;

    /**
     * Setting the serialVersion
     */
    protected static final long serialVersionUID = 24L;

    /**
     * Initializes the action with a specific description.
     *
     * @param description String description
     */
    public Action(String description) {
        this.description = description;
    }

    /**
     * Prints the description of the action
     */
    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Interaction between the player and action.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
    }

    /**
     * Gets the description, I know it says it isn't being used but I'm pretty sure it is...
     *
     * @return String description
     */
    @Override
    public String getDescription() {
        return this.description;
    }
}
