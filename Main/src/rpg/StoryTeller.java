package rpg;

/**
 * Class to print epilogues
 */
public class StoryTeller {
    /**
     * Prints epilogue for Ending 2: Friends
     */
    static void epilogueFriend() {
        String[] lines = new String[]{"You enter the room, you see the Evil Being sitting.",
                "You draw your weapon, and you slowly approach,",
                "*What is it doing* you think to yourself.",
                "*Is it crying?*",
                "And so it turns out the 'Evil' Being",
                "was just lonely and misunderstood.",
                "The man at the entrance set you up.",
                "You befriend the Being",
                "and terrorize the man that betrayed you."};
        printStory(lines);
    }

    /**
     * Prints epilogue for Ending 3: Leave
     */
    static void epilogueLeave() {
        String[] lines = new String[]{
                "You are struck by fear. You start thinking,",
                "what happens if you don't make it out alive?",
                "No, you're not cut out for saving the world.",
                "You turn your back on the dungeon, never to return.",
                "About a week later, you hear rumors that a man named",
                "Nolido has gone into the Dark Dungeon and slain the evils",
                "within. Along his way, he fought a clumsy cowboy, a",
                "demihuman, and joined forces with a giant rat. He",
                "entered the dungeon as a nobody, and left a hero.",
                "You think to yourself, ",
                "\"That could have been me.\""
        };
        printStory(lines);
    }

    /**
     * Prints lines with a delay between all lines
     * @param lines Array of epilogue lines
     */
    static void printStory(String[] lines) {
        System.out.println("\n\n\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            System.out.println(line);
            try {
                Thread.sleep(1500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints with delay (for game over)
     */
    static void printWithDelay(String s, long delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(s);
    }

    /**
     * Prints a list of empty lines.
     * @param noLines Int number of lines to print.
     */
    static void printEmptyLines(int noLines){
        for (int i = 0; i < noLines; i++) {
            System.out.println("\n");
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Prints Epilogue for Ending 1: Slayer
     */
    static void epilogueKill() {
        String[] lines = new String[]{"And so the adventurer defeated the Evil Being.",
                "The dungeon returned to it's original state of peace.",
                "With the commanding force behind all evil done",
                "by the minions of the Evil Being, they rapidly",
                "regained their senses. But what was this Evil Thing?",
                "Why did it seek to harm and destroy? To this day",
                "we do not know."};
        printStory(lines);
    }

    /**
     * Mocks the player for being a dead n00b
     */
    static void gameOver() {
        System.out.println("You succumbed to your injuries!");
        long delay = 500;
        //ASCII text generated using https://www.coolgenerator.com/ascii-text-generator (Bloody font)
        printWithDelay("  ▄████ ▄▄▄      ███▄ ▄███▓█████     ▒█████  ██▒   █▓█████ ██▀███  ", delay);
        printWithDelay(" ██▒ ▀█▒████▄   ▓██▒▀█▀ ██▓█   ▀    ▒██▒  ██▓██░   █▓█   ▀▓██ ▒ ██▒", delay);
        printWithDelay("▒██░▄▄▄▒██  ▀█▄ ▓██    ▓██▒███      ▒██░  ██▒▓██  █▒▒███  ▓██ ░▄█ ▒", delay);
        printWithDelay("░▓█  ██░██▄▄▄▄██▒██    ▒██▒▓█  ▄    ▒██   ██░ ▒██ █░▒▓█  ▄▒██▀▀█▄  ", delay);
        printWithDelay("░▒▓███▀▒▓█   ▓██▒██▒   ░██░▒████▒   ░ ████▓▒░  ▒▀█░ ░▒████░██▓ ▒██▒", delay);
        printWithDelay(" ░▒   ▒ ▒▒   ▓▒█░ ▒░   ░  ░░ ▒░ ░   ░ ▒░▒░▒░   ░ ▐░ ░░ ▒░ ░ ▒▓ ░▒▓░", delay);
        printWithDelay("  ░   ░  ▒    ▒ ░  ░      ░░ ░  ░     ░ ▒  ░   ░ ░░  ░ ░  ░    ░ ▒░", delay);
    }
}
