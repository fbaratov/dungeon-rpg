package rpg;

import rpg.doors.CutsceneDoor;
import rpg.doors.Door;
import rpg.doors.LockedDoor;
import rpg.npc.*;
import rpg.npc.enemies.*;
import rpg.npc.helpers.*;
import rpg.player_effects.*;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Initializer {
    /**
     * Initializes the player.
     *
     * @param room The starting room.
     * @return Player initialized.
     */
    public static Player initialize_player(Room room) {
        Player player = null;
        String response;
        do{
            System.out.println("You are about to start a new game, would you like to configure it?");
            ArrayList<String> options = new ArrayList<>(Arrays.asList("Play", "Load config", "Save default config"));
            response = Menu.printStringOptions(options, false);
            if(response.equals("Load config")) {
                player = ConfigManager.getPlayerFromConfig(room);
            } else if(response.equals("Save default config")){
                ConfigManager.saveDefaultConfig();
            }
        }while (!response.equals("Play"));
        if(player == null) {
            System.out.println("> What is your name adventurer?");
            String name = Menu.scanString();
            player = new Player(name, room, 200, 50, 100);
        }
        return player;
    }

    /**
     * Initializes the whole dungeon
     *
     * @param room0 Starting room
     * @param player Player.
     * @return The boss mob.
     */
    public static Boss initializeDungeon(Room room0, Player player) {

        String name = player.getName();

        Room out = new Room("Outside"); //leave condition

        initializeRoom0(out,room0,player.getName());
        Room room1 = initializeRoom1(room0);
        Room room2 = initializeRoom2(room1);
        Room room3 = initializeRoom3(room2);
        Room room4 = initializeRoom4(room3);
        Room room5 = initializeRoom5(room4);
        Room room6 = initializeRoom6(room5);

        // -------------------- Boss room --------------------
        Room bossRoom = new Room("You are waist-high in water, the water is restless.");
        Door door6boss = new Door(room6, bossRoom, "A large, impressive, azure door (DOOR TO BOSS ROOM).");
        Boss gura = new Boss("A child in a shark hoodie", 500, 50);
        bossRoom.addMiniBoss(gura);
        player.setBoss(gura);
        return gura;
    }

    /**
     * Initializes the first room of the dungeon.
     * @param out Room, The room to leave the dungeon.
     * @param room0 Room this room.
     * @param name String name of player.
     */
    private static void initializeRoom0(Room out, Room room0, String name){
        // -------------------- Room0 --------------------
        // Room and doors
        //room0 initialized at the top
        Door doorOut0 = new Door(out, room0, "Leave the dungeon and never return.");

        // NPCs
        Hint tutorial = new Hint("TUTORIAL",
                "Welcome to " + name + "'S BIZARRE ADVENTURE!\n" +
                        "You can use money to buy stuff later!\n" +
                        "You heal after fighting (&) running away!\n" +
                        "HINT NPCs provide you with useful information!\n" +
                        "Only ENEMYs (and the BOSS) will fight you!\n" +
                        "You can recruit ATTACKERs, HEALERs, and WIZARDs!\n" +
                        "Some rooms are inescapable until you defeat the enemy within!\n" +
                        "There are some side rooms, but you shouldn't get lost!\n" +
                        "You'll get more info later on!");
        Hint master = new Hint("Your Master", "You must kill the Aquatic Evil that lies beyond the Azure Door! Enter the dungeon and claim your glory!");
        room0.addNPC(tutorial);
        room0.addNPC(master);
        Item locked01key = new Item(0,0,0,false,"Diamond shaped key","Diamond key",1);
        ArrayList<Item> merchCatalogue = new ArrayList<>();
        merchCatalogue.add(locked01key);
        Merchant merch = new Merchant("Little Goblin",merchCatalogue);
        room0.addNPC(merch);
    }

    /**
     * Initializes a room from the previous room.
     *
     * @param prevRoom previous room.
     * @return This room.
     */
    private static Room initializeRoom1(Room prevRoom) {
        // -------------------- Room1 --------------------

        //Connections
        Room room1 = new Room("A dim room lit by torches on the walls.");
        String[] cutscene = {"The depths of the dungeon seem to welcome you.", "The nausiating smell of blood fills the air",
            "Who knows what terrors lay beyond this door.", "But it is your duty to resolve this,",
            "and to save what you call dear."};
        Door door01 = new CutsceneDoor(prevRoom, room1, "A heavy wooden door.", cutscene);
        //NPCs
        Hint boxingFan = new Hint("s4003322", "\"I LOCKED THE DOORS YOU CAN'T GET OUT UNTIL YOU BEAT THE PACIFIST TO DEATH\"");
        Pacifist niceMan = new Pacifist("Very nice man");
        Slime slime = new Slime("A fat blue slime", 100, 20);
        ArrayList<NPC> npcs = new ArrayList<>(Arrays.asList(boxingFan,slime));
        room1.setNPCs(npcs);
        room1.addMiniBoss(niceMan);

        //Side room 10
        Room sideRoom10 = new Room("A dimly lit office lit only by a computer screen.");
        Door sideDoor1_10 = new LockedDoor(room1, sideRoom10, "A white modern door, it feels out of place.", "Diamond key");
        //NPCs
        Hint advertiser = new Hint("s3927083", "\"Hey, God here, just want to say that Organ Boy over there is going to heal you in combat. You can trust him.\"");
        NPC organBoy = new Healer("Black Market Organ Dealer", 50, 25);
        sideRoom10.addNPC(advertiser);
        sideRoom10.addNPC(organBoy);

        return room1;
    }

    /**
     * Initializes a room from the previous room.
     *
     * @param prevRoom previous room.
     * @return This room.
     */
    private static Room initializeRoom2(Room prevRoom) {
        // -------------------- Room2 --------------------
        //Rooms and doors
        Room room2 = new Room("A dark alleyway, only a few torches light the way, smells of rot.");
        Door door12 = new Door(prevRoom, room2, "A regular oak wood door.");
        //NPCs
        Hint room2hint = new Hint("Rat enthusiast", "Please don't kill it! I love rats! Charm it or bribe it instead!");
        GiantRat rat = new GiantRat("Giant rat", 35, 5);
        room2.addNPC(room2hint);
        room2.addMiniBoss(rat);


        return room2;
    }

    /**
     * Initializes a room from the previous room.
     *
     * @param prevRoom previous room.
     * @return This room.
     */
    private static Room initializeRoom3(Room prevRoom) {
        // -------------------- Room3 --------------------
        //Main room &door
        Room room3 = new Room("An inexplicably well-lit room with a grass floor.");
        Door door23 = new Door(prevRoom, room3, "A clean wooden door.");
        //NPCs
        Hint oopStudent = new Hint("I SWEAR ADDING THAT AS AN ENEMY WAS MY PARTNER'S IDEA NOT MINE", "YOU HEARD WHAT I SAID");
        Enemy myOOPPartnersWaifu = new CatGirl("Catgirl", 40, 2);
        room3.addNPC(oopStudent);
        room3.addMiniBoss(myOOPPartnersWaifu);

        //side room
        Room sideRoom30 = new Room("A shop ran by a totally legit merchant.");
        Door sideDoor3_30 = new Door(room3, sideRoom30, "Archway to a shop.");
        //NPCs
        ArrayList<Item> catalogueAlbert = new ArrayList<>();
        catalogueAlbert.add(new Item(100, 0, 0, false, "Excalibur", "Sword", 5));
        catalogueAlbert.add(new Item(0, 5, 40, false, "Diamond Chestplate", "Chestplate", 5));
        catalogueAlbert.add(new Item(0,0,0,false,"Key with an emerald","Emerald Key",1));
        NPC room30merchant = new Merchant("De Heer Albert Heijn ", catalogueAlbert);
        Scout scoutSama = new Scout("Jacob", 2, 1);
        sideRoom30.addNPC(room30merchant);
        sideRoom30.addNPC(scoutSama);

        return room3;
    }

    /**
     * Initializes a room from the previous room.
     *
     * @param prevRoom previous room.
     * @return This room.
     */
    private static Room initializeRoom4(Room prevRoom) {
        // -------------------- Room4 --------------------
        //main rooms and door
        Room room4 = new Room("A damp room with water creaking through the ceiling.");
        Door door34 = new Door(prevRoom, room4, "A small iron door.");
        //npc
        Robot toaster = new Robot("Toasterbot", 70, 15);
        room4.addMiniBoss(toaster);

        Room sideRoom40 = new Room("A creepy room with nothing but a chandelier and dusty wooden crates.");
        Door door4_40 = new Door(room4, sideRoom40, "A rotting wooden door.");
        //npcs
        Wizard gandalf = new Wizard("Gandalf the Monochrome", 40, 10,
                new Buff(10, 5, 15, false, "Average Buff"));
        sideRoom40.addNPC(gandalf);


        return room4;
    }

    /**
     * Initializes a room from the previous room.
     *
     * @param prevRoom previous room.
     * @return This room.
     */
    private static Room initializeRoom5(Room prevRoom) {
        // -------------------- Room5 --------------------
        //main room and doors
        Room room5 = new Room("A damp room with water trickling down the walls.");
        Door door45 = new Door(prevRoom, room5, "A makeshift reinforced door.");
        //npcs
        SpaceCowboy spiegel = new SpaceCowboy("Nervous Cowboy", 50, 100);
        room5.addMiniBoss(spiegel);

        //side room
        Room sideRoom50 = new Room("Empty prison cells.");
        Door sideDoor5_50 = new Door(room5, sideRoom50, "A jail door.");
        //npcs
        Attacker shankBoy = new Attacker("Shank Assassin", 50, 10);
        sideRoom50.addNPC(shankBoy);


        return room5;
    }

    /**
     * Initializes a room from the previous room.
     *
     * @param prevRoom previous room.
     * @return This room.
     */
    private static Room initializeRoom6(Room prevRoom) {
        // -------------------- Room6 --------------------
        //main room and doors
        Room room6 = new Room("You are knee-high in water. The room is quiet and terrifying.");
        Door door56 = new Door(prevRoom, room6, "Descending staircase.");
        //npcs
        Skeleton skeletor = new Skeleton("Spooky boy", 200, 20);
        room6.addMiniBoss(skeletor);

        //side room, door, merchant
        Room sideRoom60 = new Room("A shop.");
        Door sideDoor6_60 = new LockedDoor(room6, sideRoom60, "A high small wooden door.","Emerald Key");
        ArrayList<Item> catalogueJeff = new ArrayList<>();
        catalogueJeff.add(new Item(100, 0, 0, false, "Chunchunmaru", "Sword", 7));
        catalogueJeff.add(new Item(0, 30, 150, false, "Warmogs", "Chestplate", 7));
        Merchant jeff = new Merchant("Jeff Bezos", catalogueJeff);
        sideRoom60.addNPC(jeff);

        //side room for ending 2
        Room sideRoom61 = new Room("An large but empty room with one person standing inside.");
        Door sideDoor6_61 = new Door(room6, sideRoom61, "Archway to a dark room.");
        NPC guard = new Guard("The bald man", 1, 0);
        sideRoom61.addNPC(guard);
        return room6;
    }

}
