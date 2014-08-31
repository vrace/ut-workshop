/**
 * Created by liu yang on 8/29/14.
 */
public class MultiPlayer extends Player {

    private String name;

    public MultiPlayer(final String name) {

        super();
        this.name = name;

    }

    public String getName() {
        return name;
    }

    @Override
    public String askInput() {
        return String.format("Please input a number (%d), %s:", chance, name);
    }

    public String makeWinText(boolean strong) {

        if (strong) {
            return String.format("You got it! Congratulations, %s!", name);
        }

        return String.format("Congratulations, %s.", name);
    }

    public void removeChance(int removeCount) {
        for (int i = 0; i < removeCount; i++) {
            removeChance();
        }
    }
}
