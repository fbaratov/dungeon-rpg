package rpg;

import rpg.npc.Merchant;
import rpg.player_effects.Buff;
import rpg.player_effects.Item;

import java.util.ArrayList;

/**
 * Like the menu class but only the unique menus
 */
public class SpecialMenu {
    /**
     * Prints the stat increases granted by the buff. Used by other print functions.
     *
     * @param buff Buff
     */
    public static void printBuff(Buff buff) {
        if (buff.getHealth() > 0) {
            System.out.println("       * MAX HP: +" + buff.getHealth());
        }
        if (buff.getAP() > 0) {
            System.out.println("       * AP: +" + buff.getAP());
        }
        if (buff.getRegen() > 0) {
            System.out.println("       * HP REGEN: +" + buff.getRegen());
        }
        if (buff.getInvincible()) {
            System.out.println("       * SPECIAL: GRANTS INVINCIBILITY");
        }
    }

    /**
     * Prints the merchants catalogue, including item stats.
     *
     * @param catalogue ArrayList Items in catalogue.
     */
    private static void printMerchantCatalogue(ArrayList<Item> catalogue) {
        int count = 0;
        for (Item item : catalogue) {
            if (item.getCost() > 0) {
                System.out.println("    (" + count + ") " + item.getDescription() + "  COST: " + item.getCost());
            } else {
                System.out.println("    (" + count + ") " + item.getDescription() + "  SOLD OUT");
            }
            printBuff(item);
            count++;
        }
        System.out.println("    (-1) Go back");
    }

    /**
     * Prints the merchant's menu and allows the player to buy something
     *
     * @param merchant Merchant that's selling the catalogue
     * @param player   Player that's doing the buying
     */
    public static void printMerchantMenu(Merchant merchant, Player player) {
        System.out.println("\"Got some rare things on sale, stranger!\"");
        ArrayList<Item> catalogue = merchant.getCatalogue();
        printMerchantCatalogue(catalogue);
        //player selects items, menu stays open until player leaves
        int response = Menu.scanInt();
        while (response != -1) {
            if (response > catalogue.size()) return;
            Item item = catalogue.get(response);

            //player selects an item, has to confirm that they
            // want to buy it (or are told they don't have enough money)
            if (item.getCost() <= player.getCoins()) {
                for (Item oldItem : player.getItems()) {
                    if (item.getType().equals(oldItem.getType())) {
                        System.out.println("This item will replace " + oldItem.getDescription());
                        break;
                    }
                }
                System.out.println("Would you like to buy " + item.getDescription().toUpperCase() + " for " + item.getCost() + "?");
                System.out.println("    (0) No\n" +
                        "    (1) Yes");
                response = Menu.scanInt();
                if (response == 1) {
                    item.interact(player);
                    System.out.println("\"Heh heh heh... Thank you!\"\n" +
                            "You have acquired " + item.getDescription().toUpperCase() + "!\n" +
                            "You now have " + player.getCoins() + "coins!");
                }
            } else {
                System.out.println("\"Not enough cash, stranger!\"");
            }
            System.out.println("\"Anything else?\"");
            printMerchantCatalogue(catalogue);
            response = Menu.scanInt();
        }
        System.out.println("\"Come back anytime!\"");
    }

    /**
     * Prints player HP and AP
     *
     * @param player Player
     */
    private static void printPlayerStats(Player player) {
        System.out.println("    (*) HP: " + player.getCurrentHP() + "/" + player.getMaxHP() + "\n" +
                "    (*) AP: " + player.getAP());
        Menu.goBack();
    }

    /**
     * Prints list of buffs
     *
     * @param buffs List of buffs
     */
    private static void printBuffMenu(ArrayList<Buff> buffs) {
        if (buffs.size() == 0) {
            System.out.println("There's nothing here!");
        }
        for (Buff buff : buffs) {
            System.out.println("    (*) " + buff.getDescription());
            printBuff(buff);
        }
        Menu.goBack();
    }

    /**
     * Prints hardcoded main player menu with options
     *
     * @param player Player
     */
    private static void printPlayerMenuList(Player player) {
        System.out.println("    (*) NAME:  " + player.getName() + "\n" +
                "    (*) COINS: " + player.getCoins() + "\n" +
                "    (0) Stats\n" +
                "    (1) Inventory\n" +
                "    (2) Buffs\n" +
                "    (-1) Go back");
    }

    /**
     * Prints the player menu that tells the player about their stats and buffs/inventory.
     *
     * @param player Player
     */
    public static void printPlayerMenu(Player player) {
        System.out.println("What would you like to know about yourself?");
        printPlayerMenuList(player);
        int response = Menu.scanInt();
        while (response != -1) {
            if (response > 2) return;
            if (response == 0) {
                printPlayerStats(player);
            } else if (response == 1) {
                ArrayList<Buff> buffList = new ArrayList<>(player.getItems());
                printBuffMenu(buffList);
            } else if (response == 2) {
                printBuffMenu(player.getBuffs());
            }
            System.out.println("What else would you like to know about yourself?");
            printPlayerMenuList(player);
            response = Menu.scanInt();
        }
    }



}
