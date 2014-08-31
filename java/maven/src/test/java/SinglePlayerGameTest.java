import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.ByteBuffer;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by liu yang on 8/31/14.
 */

public class SinglePlayerGameTest {

    private SinglePlayerGame game;
    private AnswerGenerator answerGenerator;

    @Before
    public void setup() {

        answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.makeAnswer()).thenReturn("1234");

        Player player = new Player();
        Guess guess = new Guess(answerGenerator, new CompareNumber());
        game = new SinglePlayerGame(guess, player);
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
    public void should_show_congrats_when_success() throws Exception {

        // given
        BufferedReader in = mock(BufferedReader.class);
        when(in.readLine()).thenReturn("1234");

        PrintStream out = mock(PrintStream.class);

        InOrder order = inOrder(out);

        // when
        game.init(in, out);
        game.step();

        // that
        order.verify(out).println("Welcome!");
        order.verify(out).println("Please input a number (6):");
        assertThat(game.resultText()).isEqualTo("Congratulations!");
    }
}
