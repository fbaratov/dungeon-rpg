package rpg;

/**
 * Class to generate random numbers.
 */
public class Rand {
    /**
     * Gets a random integer between 0-10 inclusive this has an bizzar distribution to make things more interesting.
     * Also informs the player that they have rolled.
     *
     * @return Integer between 0-10 inclusive
     */
    public static int randInt() {
        /*
        Here the numbers are kind of arbitrary, the idea is that the distribution given by the
        Math.random() is completely flat, that is, all numbers are equalily likely, this is no fun,
        So instead by adding two of them together we get more combinations for certain numbers, causing the
        distribution to be more like a bell curve, however this bell curve is a little to extreme, so we
        make the distribution more uniform by changing the weights of each random value (1.5 and 0.5).
        We then want to give this as an integer out of 10 so we add 0.5 to round and multiply by five before casting to
        an integer.
        */
        int rand = (int) (5 * (Math.random() * 1.5 + Math.random() * 0.5) + 0.5);
        System.out.println("You roll " + rand + " out of 10!");
        return rand;
    }

}
