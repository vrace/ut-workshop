import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.Buffer;

/**
 * Created by liu yang on 8/29/14.
 */
public class Game {

    private BufferedReader input;
    private PrintStream output;

    public Game(BufferedReader in, PrintStream out) {

        input = in;
        output = out;

    }

    private GameType getGameType() {

        output.println("Please choose game mode (1. Story, 2. Fight):");

        GameType gameType = GameType.InvalidGame;

        while (gameType == GameType.InvalidGame) {

            try {

                String line = input.readLine();

                if (line.equals("1")) {
                    gameType = GameType.SinglePlayerGame;
                } else if (line.equals("2")) {
                    gameType = GameType.MultiPlayerGame;
                } else {
                    output.println("Invalid input. Try again.");
                }

            } catch (IOException exc) {

                output.println("Can't read input. Try again.");

            }
        }

        return gameType;
    }

    public void run() {

        GameFactory factory = new GameFactory();
        GameType gameType = getGameType();
        GameInterface game = factory.createGame(gameType);

        if (game != null) {

            for (game.init(input, output); game.shouldContinue(); game.step());
            output.println(game.resultText());

        } else {

            output.println("Invalid game type.");

        }
    }
}
