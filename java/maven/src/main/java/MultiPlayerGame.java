import com.sun.org.apache.xpath.internal.operations.Mult;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by liu yang on 8/29/14.
 */

public class MultiPlayerGame extends SinglePlayerGame {

    private Guess guess;
    private MultiPlayer[] players;
    private MultiPlayer winner;
    private boolean strongWin;

    public MultiPlayerGame(Guess guess, MultiPlayer playerA, MultiPlayer playerB) {

        super(guess, null);

        this.guess = guess;

        players = new MultiPlayer[2];
        players[0] = playerA;
        players[1] = playerB;

        this.winner = null;

        input = new BufferedReader(new InputStreamReader(System.in));
        output = System.out;

        strongWin = false;
    }

    @Override
    public void init(BufferedReader in, PrintStream out) {

        this.input = in;
        this.output = out;

        output.println("Welcome!");
        output.println();

    }

    private MultiPlayer nextPlayer(int current) {
        return players[(current + 1) % players.length];
    }

    @Override
    public void step() {

        for (int i = 0; i < players.length && shouldContinue(); i++) {

            MultiPlayer current = players[i];
            StepPlayerResult res = stepPlayer(current);

            switch (res.getKey()) {

                case Congrats:
                    winner = current;
                    setResultText(winner.makeWinText(true));
                    break;

                case GameOver:
                    winner = nextPlayer(i);
                    setResultText(winner.makeWinText(false));
                    break;

                case Continue: {
                    MultiPlayer next = nextPlayer(i);
                    if (res.getValue() > 0) {
                        next.removeChance(res.getValue());
                        output.println(String.format("%d chance removed from %s.", res.getValue(), next.getName()));
                    }
                    if (!next.hasMoreChance()) {
                        winner = current;
                        setResultText(winner.makeWinText(false));
                    }
                    break;
                }

                default:
                    break;

            }

            output.println();

        }

    }

    @Override
    public boolean shouldContinue() {
        return winner == null;
    }
}
