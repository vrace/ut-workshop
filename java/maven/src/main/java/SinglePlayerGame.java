import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by liu yang on 8/28/14.
 */
public class SinglePlayerGame implements GameInterface {

    private Guess guess;
    private Player player;
    private String result;
    private BufferedReader input;
    private PrintStream output;

    protected enum StepPlayerResult {
        StepResultContinue,
        StepResultCongrats,
        StepResultGameOver,
    }

    public SinglePlayerGame(Guess guess, Player player) {
        this.guess = guess;
        this.player = player;
        result = "";

        input = new BufferedReader(new InputStreamReader(System.in));
        output = System.out;
    }

    @Override
    public void init(BufferedReader in, PrintStream out) {
        input = in;
        output = out;

        out.println("Welcome!");
        out.println();
    }

    protected String readGuess() {

        String line = "";

        while (true) {

            try {
                line = input.readLine();
            } catch (IOException exc) {
                line = "";
                output.println("Can't read input. Try again.");
            }

            if (!line.isEmpty()) {
                if (!guess.validateInput(line)) {
                    output.println("Bad input format. Try again.");
                } else {
                    break;
                }
            }
        }

        return line;
    }

    protected StepPlayerResult stepPlayer(Player player) {

        output.println(player.askInput());

        final String line = readGuess();
        final String value = guess.compare(line);

        StepPlayerResult result = StepPlayerResult.StepResultContinue;
        if (value.equals(Guess.TotalMatch)) {
            result = StepPlayerResult.StepResultCongrats;
        } else {
            output.println(value);
            player.removeChance();
            if (!player.hasMoreChance()) {
                result = StepPlayerResult.StepResultGameOver;
            }
        }

        output.println();

        return result;
    }

    @Override
    public void step() {
        if (player.hasMoreChance() && shouldContinue()) {

            StepPlayerResult res = stepPlayer(player);

            switch (res) {

                case StepResultCongrats:
                    result = "Congratulations!";
                    break;

                case StepResultGameOver:
                    result = "Game Over";
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public boolean shouldContinue() {
        return result.isEmpty();
    }

    protected  void setResultText(final String text) {
        result = text;
    }

    @Override
    public String resultText() {
        return result;
    }
}
