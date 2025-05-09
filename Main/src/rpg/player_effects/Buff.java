package rpg.player_effects;

import rpg.Actionable;
import rpg.Player;

import java.io.Serializable;

/**
 * Buffs which can be given to the player and have various effects on them.
 */
public class Buff implements Actionable, Serializable {
    /**
     * Setting ther serialVersionUID for saving.
     */
    protected static final long serialVersionUID = 19L;
    final private int ap;
    final private boolean invincible;
    final private int regen;
    final private int maxHealth;
    final private String description;

    /**
     * Initializes the buff.
     *
     * @param ap          Int Ap buff
     * @param regen       Int Regeneration buff
     * @param maxHealth   Int Maximum hp buff
     * @param invincible  Boolean of invincibility
     * @param description String description.
     */
    public Buff(int ap, int regen, int maxHealth, boolean invincible, String description) {
        this.ap = ap;
        this.regen = regen;
        this.maxHealth = maxHealth;
        this.invincible = invincible;
        this.description = description;
    }

    /**
     * Gets AP increase of the buff
     *
     * @return AP Buff
     */
    public int getAP() {
        return ap;
    }

    /**
     * Gets HP Regeneration of the buff
     *
     * @return HP Regeneration
     */
    public int getRegen() {
        return regen;
    }

    /**
     * Gets max health increase of the buff
     *
     * @return Max Health
     */
    public int getHealth() {
        return maxHealth;
    }

    /**
     * Gets whether or not buff grants invincibility
     *
     * @return Invincibility
     */
    public boolean getInvincible() {
        return invincible;
    }


    /**
     * Allows the player to receive the buffs.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        player.addHealth(maxHealth);
        player.addAP(ap);
        player.setInvincible(invincible);
    }

    /**
     * Applies passive buffs to player.
     *
     * @param player Player.
     */
    public void passive(Player player) {
        player.getHeal(regen);
    }

    /**
     * Gets the description of the buff.
     *
     * @return String description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Prints the description
     */
    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Removes a buff from the player
     *
     * @param player Player.
     */
    public void removeBuff(Player player) {
        player.removeAP(ap);
        player.removeMaxHP(maxHealth);
        if (invincible) {
            player.removeInvincibility();
        }
    }
}
