import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liu yang on 8/25/14.
 */
public class Program {

    public static void main(String[] args) {

        Guess guess = new Guess(new AnswerGenerator());
        Game game = new Game(guess);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        game.run(reader, System.out);
    }
}
