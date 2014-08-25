import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liu yang on 8/25/14.
 */
public class Program {

    public static void main(String[] args) {

        Guess guess = new Guess(new AnswerGenerator());

        boolean done = false;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!done) {

            String line = "";

            try {

                line = reader.readLine();

            } catch (IOException exc) {

            }

            if (line.length() != 4) {

                System.out.println("Invalid input, try again.");

            } else {

                String result = guess.compare(line);

                System.out.println(result);

                if (result.equals("4A0B")) {
                    done = true;
                }

            }

        }
    }
}
