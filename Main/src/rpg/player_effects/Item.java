package rpg.player_effects;

import rpg.Player;

import java.util.ArrayList;

/**
 * Item class which is sold by the merchant.
 */
public class Item extends Buff{
    private final String type;
    private int cost;

    /**
     * Initializes the buff.
     *
     * @param ap          Int Ap buff
     * @param regen       Int Regeneration buff
     * @param maxHealth   Int Maximum hp buff
     * @param invincible  Boolean of invincibility
     * @param description String description.
     */
    public Item(int ap, int regen, int maxHealth, boolean invincible, String description, String type, int cost) {
        super(ap, regen, maxHealth, invincible, description);
        this.type = type;
        this.cost = cost;
    }

    /**
     * Adds the item to the player and adds the buffs.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        if(cost < 0) {
            System.out.println("This item is sold out");
            return;
        }
        player.removeCoins(this.cost);
        ArrayList<Item> items = player.getItems();
        for (Item item : items) {
            if (item.getType().equals(this.getType())) {
                player.removeItem(item);
                break;
            }
        }
        player.addItem(this);
        cost = -1;
        super.interact(player);
    }


    /**
     * Gets the item type.
     *
     * @return String item type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets item cost.
     *
     * @return Item cost in coins.
     */
    public int getCost() {
        return cost;
    }
}
