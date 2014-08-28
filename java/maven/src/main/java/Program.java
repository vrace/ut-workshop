import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by liu yang on 8/25/14.
 */
public class Program {

    public static void main(String[] args) {

        GameFactory factory = new GameFactory();
        GameInterface game = factory.createGame(GameType.SinglePlayerGame);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        game.init(reader, System.out);
        for (; game.shouldContinue(); game.step());
        System.out.println(game.resultText());
    }
}
