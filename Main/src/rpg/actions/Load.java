package rpg.actions;

import rpg.Game;
import rpg.Menu;
import rpg.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Load extends Action{

    /**
     * Initializes the action with a specific description.
     *
     */
    public Load() {
        super("Load");
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
        ArrayList<String> saves = new ArrayList<>(Arrays.asList(Objects.requireNonNull(saveDirectory.list())));
        System.out.println("What file would you like to open?");
        String choice = Menu.printStringOptions(saves, true);
        if (!choice.equals("Cancel")) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("savedgames/" + choice));
                Player savedPlayer = (Player) in.readObject();
                in.close();
                new Game(savedPlayer, savedPlayer.getBoss());
                try {
                    Game.scanner.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                System.exit(0);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Canceled.");

    }
}
