package rpg;

import rpg.npc.*;
import rpg.npc.enemies.Boss;
import rpg.npc.enemies.Enemy;
import rpg.npc.helpers.Helper;
import rpg.player_effects.Buff;
import rpg.player_effects.Item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player class, this is the center of the gameplay.
 * The actual player loses if Player dies and wins if player wins.
 */
public class Player implements Serializable {
    // Setting serial version UID
    private static final long serialVersionUID = 18L;
    private final String name;
    private int maxHP;
    private int AP;
    private int currentHP;
    private int coins;
    private final ArrayList<Helper> party = new ArrayList<>();
    private final ArrayList<Buff> buffs = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();
    private Enemy fighting;
    private NPC interactingNPC;
    private Room currentRoom;
    private boolean invincible;
    private final double critMod;
    private Boss boss;


    /**
     * Initializes player with the parameters.
     *
     * @param name  String of the name.
     * @param room  Room which the player will start in.
     * @param maxHP Int of maximum player HP
     * @param AP    Int of player Attack Power
     * @param coins Int of players wealth in coins
     * @param critMod Double modifier of critical strikes for the player.
     */
    public Player(String name, Room room, int maxHP, int AP, int coins, double critMod) {
        this.name = name;
        this.currentRoom = room;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.AP = AP;
        this.coins = coins;
        this.critMod = critMod;
    }

    /**
     * Initializes player with the parameters.
     *
     * @param name  String of the name.
     * @param room  Room which the player will start in.
     * @param maxHP Int of maximum player HP
     * @param AP    Int of player Attack Power
     * @param coins Int of players wealth in coins
     */
    public Player(String name, Room room, int maxHP, int AP, int coins){
        this(name,room,maxHP,AP,coins,2.0);
    }

    /**
     * Gets the current HP of the player.
     * @return Int current HP.
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Gets the maximum HP of the player.
     * @return Int max HP of the player.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Gets AP from player.
     * @return Int player AP.
     */
    public int getAP() {
        return AP;
    }

    /**
     * Gets the list of buffs applied to player.
     * @return ArrayList of buffs.
     */
    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    /**
     * Moves the player from room.
     *
     * @param room Room in which you want to move.
     */
    public void moveRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Gets the room the player is in.
     *
     * @return Room in which the player is in.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Gets the name of the player
     *
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * Does damage to the enemy.
     *
     * @param enemy Enemy to which damage will be dealt.
     */
    public void doDamage(Enemy enemy) {
        if (Rand.randInt() > 6) {
            System.out.println("You perform a critical strike!!");
            enemy.takeDamage((int)(AP * critMod));
        } else {
            enemy.takeDamage(this.AP);
        }

    }

    /**
     * Evaluates the damage done to the player.
     *
     * @param damage Int of the damage to be dealt.
     */
    public void takeDamage(int damage) {
        if (invincible) {
            damage = 0;
        }
        this.currentHP -= damage;
        System.out.println("You take " + damage + " damage.");
        if (currentHP <= 0) {
            fighting = null;
        }
    }

    /**
     * Adds the NPC to the party.
     *
     * @param NPC NPC to be added to the party.
     */
    public void addToParty(Helper NPC) {
        party.add(NPC);
        NPC.joinParty(this);
        getCurrentRoom().removeNPC(NPC);
    }

    /**
     * Sets the current npc that the player is fighting.
     * Setting NPC to null ends the fight and resets the HP of all those involved.
     *
     * @param NPC Enemy that the player is fighting.
     */
    public void setFighting(Enemy NPC) {
        if (NPC == null) {
            this.resetHP();
            fighting.resetHP();
        }
        fighting = NPC;
    }

    /**
     * Gets the npc that the player is fighting.
     *
     * @return Enemy that is fighting the player.
     */
    public Enemy getFighting() {
        return fighting;
    }

    /**
     * Gets the coins that the player has.
     *
     * @return Int the amount of coins.
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Gets the party.
     *
     * @return ArrayList Party
     */
    public ArrayList<Helper> getParty() {
        return party;
    }

    /**
     * Adds the coins to the players inventory.
     *
     * @param coins Int amount of coins.
     */
    public void addCoins(int coins) {
        this.coins += coins;
        System.out.println("You get " + coins + " coins!");
    }

    /**
     * Removes coins from the inventory.
     *
     * @param coins Int amount of coins to withdraw.
     */
    public void removeCoins(int coins) {
        this.coins -= coins;
        System.out.println("You lose " + coins + " coins!");
    }

    /**
     * Performs all the help functions of all the helpers.
     */
    public void combatHelp() {
        for (Helper helper : party) {
            if (!helper.getKOStatus() && fighting != null) {
                System.out.println(helper.getDescription() + " helps out!!");
                helper.help(this);
            }
        }
        for (Buff buff : buffs) {
            System.out.println(buff.getDescription() + " affects you!");
            buff.passive(this);
        }
        for (Item item : items) {
            System.out.println(item.getDescription() + " affects you!");
            item.passive(this);
        }
    }

    /**
     * Gets the NPC with which the player is interacting.
     *
     * @return NPC with which the player is interacting.
     */
    public NPC getInteractingNPC() {
        return interactingNPC;
    }

    /**
     * Sets the NPC that the player is interacting with.
     *
     * @param interactingNPC NPC interacting with player.
     */
    public void setInteractingNPC(NPC interactingNPC) {
        this.interactingNPC = interactingNPC;
    }

    /**
     * Sees if a helper is in the party of the player.
     *
     * @param helper Helper to query if in party.
     * @return Boolean.
     */
    public boolean isInParty(Helper helper) {
        return party.contains(helper);
    }

    /**
     * Healing received by the player.
     *
     * @param amount Int the amount of healing recieved by the player.
     */
    public void getHeal(int amount) {
        this.currentHP += amount;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    /**
     * Add max HP to the player.
     *
     * @param health Int health to be added.
     */
    public void addHealth(int health) {
        maxHP += health;
        currentHP += health;
    }

    /**
     * Adds Attack Power to the player.
     *
     * @param buffAP int attack power to add.
     */
    public void addAP(int buffAP) {
        AP += buffAP;
    }

    /**
     * Updates the invincibility parameter to be most favorable to the player.
     *
     * @param invincible boolean.
     */
    public void setInvincible(boolean invincible) {
        if (!this.invincible) {
            this.invincible = invincible;
        }
    }

    /**
     * Adds a buff to the list of buffs.
     *
     * @param buff Buff
     */
    public void addBuff(Buff buff) {
        buffs.add(buff);
        System.out.println("You get a buff:");
        SpecialMenu.printBuff(buff);
    }

    /**
     * Gets a list of items from the player.
     *
     * @return ArrayList of Items.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Removes item from the players list.
     *
     * @param item Item to be removed.
     */
    public void removeItem(Item item) {
        items.remove(item);
        item.removeBuff(this);
    }

    /**
     * Adds an Item to the players item list.
     *
     * @param item Item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes ap from the player
     *
     * @param ap int ap to be removed
     */
    public void removeAP(int ap) {
        this.AP -= ap;
    }

    /**
     * Removes HP from the player.
     *
     * @param hp int hp to be removed.
     */
    public void removeMaxHP(int hp) {
        this.maxHP -= hp;
        if (currentHP > maxHP){
            currentHP = maxHP;
        }
    }

    /**
     * removes invincibility, there will only be one item that gives invincibility if any.
     */
    public void removeInvincibility() {
        invincible = false;
    }

    /**
     * Prints player's HP and party HP for combat purposes
     */
    public void printHealth() {
        System.out.println("Your HP: " + currentHP + "/" + maxHP);
        for (Helper helper : party) {
            System.out.println(helper.getDescription() + "'s HP: " + helper.getCurrentHP() + "/" + helper.getMaxHP());
        }
    }

    /**
     * Resets HP of player and party
     */
    public void resetHP() {
        currentHP = maxHP;
        for (Helper helper : party) {
            helper.resetHP();
        }
    }

    /**
     * Gets the boss mob.
     * @return Boss boss.
     */
    public Boss getBoss() {
        return boss;
    }

    /**
     * Sets the boss mob.
     * @param boss Boss boss mob.
     */
    public void setBoss(Boss boss) {
        this.boss = boss;
    }

}
