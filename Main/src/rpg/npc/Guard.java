package rpg.npc;

import rpg.Menu;
import rpg.Player;
import rpg.actions.OpenEndDoor;

/**
 * NPC that guards the alternative ending door.
 */
public class Guard extends NPC {
    /**
     * Initializes abstract NPC as a guard
     *
     * @param description String description.
     * @param maxHP       Int maximum HP.
     * @param AP          Int Attack Power.
     */
    public Guard(String description, int maxHP, int AP) {
        super(description, maxHP, AP);
        this.addAction(new OpenEndDoor());
    }

    /**
     * Prints the actionable menu only if the player has enough members in their party.
     *
     * @param player Player.
     */
    @Override
    public void interact(Player player) {
        int minParty = 5;
        if (player.getParty().size() > minParty) {
            System.out.println(description + " looks at your party and smiles. \"You are worthy to enter.\"");
            System.out.println("They wave their hand and a door appears in front of you.");
            System.out.println();
            Menu.printMenuActionable(this.actions, player, false);
        } else {
            System.out.println("\"I see that you are not the type to make friends. I shall not assist you.\"");
            System.out.println("\"Come back when you're a little, mmmm, friendlier.\"");
            System.out.println("You need at least " + minParty + "party members to enter this room!");
        }
    }
}
