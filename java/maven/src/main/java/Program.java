import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by liu yang on 8/25/14.
 */
public class Program {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Game game = new Game(reader, System.out);
        game.run();

    }
}
