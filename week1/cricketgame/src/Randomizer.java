public class Randomizer {

    /**
     * Get Random marks
     * @param min min mark
     * @param max max mark
     * @return random value
     */
    public static int generate(int min,int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
