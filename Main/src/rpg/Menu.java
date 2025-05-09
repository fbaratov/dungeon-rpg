package rpg;

import rpg.npc.NPC;
import rpg.npc.helpers.Helper;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * The menu class allows us to centralize printing so that individual classes don't do it and the code is cleaner.
 */
public class Menu {

    /**
     * Function that prints the menu for any given actionable.
     * Additionally it will call the interact function of any given actionable the player selects.
     *
     * @param actionables ArrayList of Actionables.
     * @param player      Player
     * @param noAction    Whether or not to print "    (-1) No action"
     */
    public static void printMenuActionable(ArrayList<Actionable> actionables, Player player, boolean noAction) {

        // Print all the actionable definitions.
        int count = 0;
        for (Actionable actionable : actionables) {
            System.out.print("    (" + count + ") ");
            actionable.inspect();
            count++;
        }
        if (noAction) System.out.println("    (-1) Take no action");

        //Collect the response from the player.
        int response = scanInt();

        //Evaluate the response by calling the interaction method.
        while (response != -1) {
            if (response < actionables.size()) {
                if (actionables.get(response) instanceof NPC) {
                    player.setInteractingNPC((NPC) actionables.get(response));
                }
                actionables.get(response).interact(player);
                break;
            } else {
                System.out.println("That is not an available action!");
                response = scanInt();
            }
        }
        player.setInteractingNPC(null);
    }

    /**
     * Function that prints the menu for any given actionable.
     * Additionally it will call the interact function of any given actionable the player selects.
     *
     * @param actionables ArrayList of Actionables.
     * @param player      Player
     */
    public static void printMenuActionable(ArrayList<Actionable> actionables, Player player) {
        printMenuActionable(actionables, player, true);
    }


    /**
     * Waits for input to stop certain menus for a moment
     */
    public static void goBack() {
        System.out.println("    (-1) Go back");
        Menu.scanString();
    }

    /**
     * Prints a menu of just inspectables.
     *
     * @param inspectables ArrayList of inspectables.
     */
    public static void printMenuInspectable(ArrayList<Inspectable> inspectables) {

        for (Inspectable inspectable : inspectables) {
            System.out.print("    (*) ");
            inspectable.inspect();
        }
        System.out.println();
    }

    /**
     * Prints a menu of NPCs
     *
     * @param NPCs ArrayList of NPCs.
     */
    public static void printMenuNPC(ArrayList<NPC> NPCs) {
        int count = 0;
        for (NPC NPC : NPCs) {
            System.out.println("    (" + count + ") " + NPC.getDescription() + "    HP: " +
                    NPC.getCurrentHP() + "/" + NPC.getMaxHP());
            count++;
        }
        System.out.println();
    }

    /**
     * Prints a menu of Helpers in your party.
     *
     * @param player Player.
     */
    public static void printMenuParty(Player player) {
        ArrayList<Helper> party = player.getParty();

        int count = 0;
        for (NPC helper : party) {
            System.out.println("    (" + count + ") " + helper.getDescription() + "    HP: " +
                    helper.getCurrentHP() + "/" + helper.getMaxHP());
            count++;
        }
        if (party.size() == 0) {
            System.out.println("    (*) Your party is empty.\n" +
                    "    (*) How disappointing.");
        }
        System.out.println("    (-1) Go back");

        int response = scanInt();
        while (response != -1) {
            if (response > party.size()) return;
            NPC helper = party.get(response);
            String role = helper.getClass().getName();
            System.out.println(helper.getDescription().toUpperCase());
            System.out.println("    (*) ROLE: " + role.substring(16));
            System.out.println("    (*) HP:   " + helper.getCurrentHP() + "/" + helper.getMaxHP());
            System.out.println("    (*) AP:   " + helper.getAp());
            System.out.println("    (-1) Go back");
            response = scanInt();

        }
    }

    /**
     * Prints an option of stings and returns the chosen option.
     *
     * @param strings ArrayList of strings to be chosen from.
     */
    public static String printStringOptions(ArrayList<String> strings, Boolean goBack) {
        int count = 0;
        for (String string : strings) {
            System.out.println("    (" + count + ") " + string);
            count++;
        }
        if(goBack) System.out.println("    (-1) Go back");
        int response = scanInt();
        while(response != -1){
            if (response < -1 || response < strings.size()){
                return strings.get(response);
            } else{
                System.out.println("That is a not an available action.");
                response = scanInt();
            }
        }
        return "Cancel";
    }

    /**
     * Serves as a scanner for the next int.
     * @return The integer scanned or a large integer if there was a mismatch exception.
     */
    public static int scanInt(){
        try{
            return Game.scanner.nextInt();
        } catch (InputMismatchException e){
            System.out.println("That is not the correct type of input!!");
            Game.scanner.nextLine();
            return 9999;
        } catch (Exception e){
            System.err.println(e.getMessage());
            Game.scanner.nextLine();
            return 9999;
        }
    }

    /**
     * Serves as a scanner for the next string.
     * @return The integer scanned or a large integer if there was a mismatch exception.
     */
    public static String scanString(){
        try{
            return Game.scanner.nextLine();
        } catch (InputMismatchException e){
            System.out.println("That is not the correct type of input!!");
            Game.scanner.nextLine();
            return "-1";
        } catch (Exception e){
            System.err.println(e.getMessage());
            Game.scanner.nextLine();
            return "-1";
        }
    }

}
