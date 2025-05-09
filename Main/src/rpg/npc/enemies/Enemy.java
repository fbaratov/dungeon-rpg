package rpg.npc.enemies;


import rpg.Menu;
import rpg.Player;
import rpg.Rand;
import rpg.actions.Attack;
import rpg.actions.Charm;
import rpg.actions.Negotiate;
import rpg.actions.Retreat;
import rpg.npc.helpers.Attacker;
import rpg.npc.helpers.Helper;
import rpg.npc.NPC;

import java.util.ArrayList;

/**
 * Generic enemy NPC that has all necessary methods to interact with the player.
 */
public class Enemy extends NPC {
    /**
     * A special introduction that the Enemy will proclaim when they are interacted with.
     */
    protected String introduction;

    /**
     * Initializes enemy with default attacks and what not.
     *
     * @param description String description of the Enemy.
     * @param HP          Int maximum HP of the Enemy.
     * @param AP          Int Attack power of Enemy.
     */
    public Enemy(String description, int HP, int AP) {
        super(description, HP, AP);
        addAction(new Attack());
        addAction(new Charm());
        addAction(new Negotiate());
        addAction(new Retreat());
        introduction = "You encounter an enemy!";
    }

    /**
     * Prints role and description of the enemy.
     */
    public void inspect() {
        System.out.println("ENEMY: " + description);
    }

    /**
     * Deals damage to the player.
     *
     * @param player Player.
     */
    public void doDamage(Player player) {
        if (this instanceof HasAbilities) {
            HasAbilities me = (HasAbilities) this;
            int rand = Rand.randInt();
            if (rand < 4) {
                me.abilityBasic(player);
            } else if (rand < 7) {
                me.abilityFocus(player);
            } else {
                me.abilityAOE(player);
            }
        } else {
            player.takeDamage(this.ap);
        }
    }

    /**
     * Interaction between the enemy and the player.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        if (this.alive) {
            player.setFighting(this);
            System.out.println(introduction);
            while (player.getFighting() != null) {
                System.out.println("*** YOUR PARTY ***");
                player.printHealth();
                System.out.println("*** THE  ENEMY ***");
                this.printHealth();
                System.out.println("What will you do?");
                Menu.printMenuActionable(actions, player, false);
            }
            for (Helper helper: player.getParty()){
                helper.endKO();
            }
        } else {
            System.out.println(description + " is already dead.");
        }
    }

    /**
     * Transforms this Enemy into an attacker
     *
     * @return Returns the attacker
     */
    public Attacker makeAttacker() {
        Attacker me = new Attacker(description, maxHP, ap);
        currentRoom.removeNPC(this);
        return me;
    }

    /**
     * Deals damage to the player and their party.
     *
     * @param player Player.
     * @param damage Int damage to be dealt.
     */
    public void doGroupDamage(Player player, int damage) {
        ArrayList<Helper> party = player.getParty();
        player.takeDamage(damage);
        for (Helper helper : party) {
            helper.takeDamage(damage);
        }
    }
}
