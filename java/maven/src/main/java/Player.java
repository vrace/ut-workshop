/**
 * Created by liu yang on 8/29/14.
 */
public class Player {

    public static final int InitialChance = 6;

    private int chance;

    public Player() {
        chance = InitialChance;
    }

    public void removeChance() {
        chance--;
    }

    public String askInput() {
        return String.format("Please input a number (%d):", chance);
    }

    public boolean hasMoreChance() {
        return chance > 0;
    }
}
