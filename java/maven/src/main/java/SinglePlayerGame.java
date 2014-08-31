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
    protected BufferedReader input;
    protected PrintStream output;

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
        GuessResult guessResult = guess.compare(line);

        final String value = guessResult.toString();

        StepPlayerResult stepPlayerResult = null;
        if (value.equals(Guess.TotalMatch)) {
            stepPlayerResult = new StepPlayerResult(StepPlayerResult.ResultKey.Congrats);
        } else {
            output.println(value);
            player.removeChance();
            if (!player.hasMoreChance()) {
                stepPlayerResult = new StepPlayerResult(StepPlayerResult.ResultKey.GameOver);
            } else {
                stepPlayerResult = new StepPlayerResult(StepPlayerResult.ResultKey.Continue, guessResult.getNewHit());
            }
        }

        return stepPlayerResult;
    }

    @Override
    public void step() {
        if (player.hasMoreChance() && shouldContinue()) {

            StepPlayerResult res = stepPlayer(player);

            switch (res.getKey()) {

                case Congrats:
                    result = "Congratulations!";
                    break;

                case GameOver:
                    result = "Game Over";
                    break;

                default:
                    break;
            }

            output.println();
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
