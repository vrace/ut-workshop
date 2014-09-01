import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by liu yang on 9/1/14.
 */
public class MultiPlayerGameTest {

    private MultiPlayerGame game;
    private AnswerGenerator answerGenerator;

    @Before
    public void setup() {

        answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.makeAnswer()).thenReturn("1234");

        MultiPlayer userA = new MultiPlayer("UserA");
        MultiPlayer userB = new MultiPlayer("UserB");

        Guess guess = new Guess(answerGenerator, new CompareNumber());

        game = new MultiPlayerGame(guess, userA, userB);
    }

    @Test
    public void should_show_welcome_at_init() throws Exception {

        // given
        BufferedReader in = mock(BufferedReader.class);
        PrintStream out = mock(PrintStream.class);

        // when
        game.init(in, out);

        // that
        verify(out).println("Welcome!");
    }

    @Test
    public void should_reduce_opponent_chance_when_get_correct_digit() throws Exception {

        // given
        BufferedReader in = mock(BufferedReader.class);
        when(in.readLine()).thenReturn("1024");

        PrintStream out = mock(PrintStream.class);
        InOrder order = inOrder(out);

        // when
        game.init(in, out);
        game.step();

        // that
        order.verify(out).println("Welcome!");
        order.verify(out).println("Please input a number (6), UserA:");
        order.verify(out).println("2A1B");
        order.verify(out).println("2 chance removed from UserB.");
        order.verify(out).println("Please input a number (4), UserB:");
    }

    @Test
    public void should_show_congrats_when_success() throws Exception {

        // given
        BufferedReader in = mock(BufferedReader.class);
        when(in.readLine()).thenReturn("4321").thenReturn("1234");

        PrintStream out = mock(PrintStream.class);

        InOrder order = inOrder(out);

        // when
        game.init(in, out);
        game.step();

        // that
        order.verify(out).println("Welcome!");
        order.verify(out).println("Please input a number (6), UserA:");
        order.verify(out).println("0A4B");
        order.verify(out).println("Please input a number (6), UserB:");
        assertThat(game.resultText()).isEqualTo("You got it! Congratulations, UserB!");

    }

    @Test
    public void should_show_win_when_opponent_has_no_more_chance() throws Exception {

        // given
        BufferedReader in = mock(BufferedReader.class);
        when(in.readLine())
                // A6 B6
                .thenReturn("5678")
                .thenReturn("5678")
                // A5 B5
                .thenReturn("5678")
                .thenReturn("5678")
                // A4 B4
                .thenReturn("5678")
                .thenReturn("5678")
                // A3 B2
                .thenReturn("1567")
                .thenReturn("5678")
                // A2 B0
                .thenReturn("1267")
                .thenReturn("5678");

        PrintStream out = mock(PrintStream.class);

        InOrder order = inOrder(out);

        // when
        for (game.init(in, out); game.shouldContinue(); game.step());

        // that
        order.verify(out).println("Welcome!");
        order.verify(out).println("Please input a number (6), UserA:");
        order.verify(out).println("Please input a number (6), UserB:");
        order.verify(out).println("Please input a number (5), UserA:");
        order.verify(out).println("Please input a number (5), UserB:");
        order.verify(out).println("Please input a number (4), UserA:");
        order.verify(out).println("Please input a number (4), UserB:");
        order.verify(out).println("Please input a number (3), UserA:");
        order.verify(out).println("1 chance removed from UserB.");
        order.verify(out).println("Please input a number (2), UserB:");
        order.verify(out).println("Please input a number (2), UserA:");
        order.verify(out).println("1 chance removed from UserB.");

        assertThat(game.resultText()).isEqualTo("Congratulations, UserA.");
    }
}
