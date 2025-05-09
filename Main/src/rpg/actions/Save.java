package rpg.actions;

import rpg.Game;
import rpg.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Save extends Action {
    /**
     * Initializes the action with a specific description.
     */
    public Save() {
        super("Save");
    }

    /**
     * Interaction between the action and the player.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        System.out.println("Under what filename would you like to save?");
        String fName = Game.scanner.next();
        File saveDirectory = new File("savedgames");
        //noinspection ResultOfMethodCallIgnored
        saveDirectory.mkdir();
        try {
            FileOutputStream fOut = new FileOutputStream("savedgames/" + fName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(player);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
