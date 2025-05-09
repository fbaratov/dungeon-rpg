package rpg.actions;

import rpg.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Quick saving
 */
public class QuickSave extends Action{
    /**
     * Initializes the action with a specific description.
     *
     */
    public QuickSave() {
        super("Quick Save");
    }

    /**
     * Interaction between the player and action.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        File saveDirectory = new File("savedgames");
        //noinspection ResultOfMethodCallIgnored
        saveDirectory.mkdir();
        try {
            FileOutputStream fOut = new FileOutputStream("savedgames/quicksave.ser");
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(player);
            out.flush();
            out.close();
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

}
