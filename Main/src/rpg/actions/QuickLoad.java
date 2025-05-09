package rpg.actions;

import rpg.Game;
import rpg.Player;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Quick loading
 */
public class QuickLoad extends Action{
    /**
     * Initializes the action with a specific description.
     *
     */
    public QuickLoad() {
        super("Quick Load");
    }


    /**
     * Interaction between the player and action.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("savedgames/quicksave.ser"));
            Player savedPlayer = (Player) in.readObject();
            in.close();
            new Game(savedPlayer, savedPlayer.getBoss());
        } catch(Exception e){
            System.out.println("You probably do not have any quick-saved games! Check for savedgames/quicksave.ser!!");
            System.err.println(e.getMessage());
        }
        try {
            Game.scanner.close();
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
        System.exit(0);
    }
}
