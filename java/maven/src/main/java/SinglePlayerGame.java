import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by liu yang on 8/28/14.
 */
public class SinglePlayerGame implements GameInterface {

    private Guess guess;
    private int chance;
    private String result;
    private BufferedReader input;
    private PrintStream output;

    public SinglePlayerGame(Guess guess) {
        this.guess = guess;
        chance = 6;
        result = "";

        input = new BufferedReader(new InputStreamReader(System.in));
        output = System.out;
    }

    @Override
    public void init(BufferedReader in, PrintStream out) {
        input = in;
        output = out;

        out.println("Welcome");
        out.println();
    }

    @Override
    public void step() {
        if (chance > 0 && shouldContinue()) {
            output.println(String.format("Please input your number (%d):", chance));

            String line = "";
            try {
                line = input.readLine();
            } catch (IOException exc) {
            }

            if (guess.validateInput(line)) {

                chance--;

                String value = guess.compare(line);
                if (value.equals(Guess.TotalMatch)) {
                    result = "Congratulations!";
                } else {
                    output.println(value);
                    if (chance <= 0) {
                        result = "Game Over";
                    }
                }

            } else {
                output.println("Invalid input. Try again.");
            }

            output.println();
        }
    }

    @Override
    public boolean shouldContinue() {
        return result.isEmpty();
    }

    @Override
    public String resultText() {
        return result;
    }
}
