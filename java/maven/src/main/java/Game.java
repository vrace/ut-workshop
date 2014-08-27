import java.io.*;

/**
 * Created by liu yang on 8/27/14.
 */
public class Game {

    private Guess guess;
    private int chance;
    private boolean done;

    public Game(Guess guess) {
        this.guess = guess;
        chance = 6;
        done = false;
    }

    public String askInput() {
        return shouldContinue() ? String.format("Please input your number (%d):", chance) : "";
    }

    public String tryInput(String input) {

        chance--;
        String output = guess.compare(input);

        if (output.equals("4A0B")) {
            done = true;
            return "Congratulations!";
        }

        return output;
    }

    public boolean shouldContinue() {
        return !done && chance > 0;
    }

    public String welcome() {
        return "Welcome!";
    }

    public String gameOver() {
        return "Game Over";
    }

    public boolean isGameOver() {
        return !done && chance <= 0;
    }

    private String readLine(BufferedReader in) {
        try {
            return in.readLine();
        } catch (IOException exc) {
            return "";
        }
    }

    public void run(BufferedReader in, PrintStream out) {

        out.println(welcome());
        out.println();

        while (shouldContinue()) {

            out.println(askInput());
            out.println(tryInput(readLine(in)));

            if (isGameOver()) {
                out.println(gameOver());
            }
        }

    }
}
