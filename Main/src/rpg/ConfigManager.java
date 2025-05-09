package rpg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

/**
 * A class to implement methods to load and save configurations.
 */
public class ConfigManager {

    /**
     * Saves a default config under the config directory.
     */
    public static void saveDefaultConfig() {
        File saveDirectory = new File("config");
        //noinspection ResultOfMethodCallIgnored
        saveDirectory.mkdir();
        Properties rpgProperties = new Properties();
        rpgProperties.setProperty("playerTotalHP", "100");
        rpgProperties.setProperty("playerName", "John");
        rpgProperties.setProperty("playerAP", "50");
        rpgProperties.setProperty("playerCoins", "20");
        rpgProperties.setProperty("playerCritMod", "2.0");
        try {
            FileOutputStream fOut = new FileOutputStream("config/rpgConfig.properties");
            rpgProperties.store(fOut, "Properties of a player in the RPG.");
            System.out.println("Config saved!!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads a player from a config.
     * @param roomStart Room, The starting room of the player.
     * @return Player, the player loaded with the config.
     */
    public static Player getPlayerFromConfig(Room roomStart) {
        File configDir = new File("config");
        //noinspection ResultOfMethodCallIgnored
        configDir.mkdir();
        ArrayList<String> saves = new ArrayList<>(Arrays.asList(Objects.requireNonNull(configDir.list())));
        System.out.println("What file would you like to open?");
        String choice = Menu.printStringOptions(saves, true);
        try {
            FileInputStream in = new FileInputStream("config/" + choice);
            Properties rpgProperties = new Properties();
            rpgProperties.load(in);
            String playerName = rpgProperties.getProperty("playerName");
            int totalHp = Integer.parseInt(rpgProperties.getProperty("playerTotalHP"));
            int playerAP = Integer.parseInt(rpgProperties.getProperty("playerAP"));
            int playerCoins = Integer.parseInt(rpgProperties.getProperty("playerCoins"));
            double playerCritMod = Double.parseDouble(rpgProperties.getProperty("playerCritMod"));
            return new Player(playerName, roomStart, totalHp, playerAP, playerCoins, playerCritMod);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("FAILED TO LOAD THE FILE!");
        }
        return new Player("LoadingError", roomStart, 100, 50, 20);
    }
}
