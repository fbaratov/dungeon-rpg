package rpg.npc;

import rpg.SpecialMenu;
import rpg.player_effects.Item;
import rpg.Player;

import java.util.ArrayList;

/**
 * NPC which sells items to the player.
 */
public class Merchant extends NPC {
    private final ArrayList<Item> catalogue;

    /**
     * Initializes Merchant NPc with a description and a catalogue
     *
     * @param description String description.
     * @param catalogue   ArrayList of items to add to the catalogue.
     */
    public Merchant(String description, ArrayList<Item> catalogue) {
        super(description, 999, 0);
        this.catalogue = catalogue;

    }

    /**
     * Gets the catalogue from the merchant.
     *
     * @return ArrayList of items.
     */
    public ArrayList<Item> getCatalogue() {
        return catalogue;
    }

    /**
     * Prints the merchant menu when you interact with him.
     *
     * @param player Player.
     */
    public void interact(Player player) {
        SpecialMenu.printMerchantMenu(this, player);
    }


}
