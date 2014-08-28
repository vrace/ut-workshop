import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by liu yang on 8/27/14.
 */
public class GameTest {

    private AnswerGenerator answerGenerator;
    private Guess guess;

    @Before
    public void setup() {
        answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.makeAnswer()).thenReturn("1234");
        guess = new Guess(answerGenerator);
    }

    @Test
    public void should_continue_when_not_running_out_of_chance() {

        // given
        Game game = new Game(guess);
        
        // when
        boolean shouldContinue = game.shouldContinue();

        // then
        assertThat(shouldContinue, is(true));
    }

    @Test
    public void should_return_not_match_and_continue_when_not_match_and_still_have_chance() {

        // given
        Game game = new Game(guess);

        // when
        String output = game.tryInput("4321");
        boolean shouldContinue = game.shouldContinue();

        // then
        assertThat(output, is("0A4B"));
        assertThat(shouldContinue, is(true));
    }

    @Test
    public void should_return_congrats_and_not_continue() {

        // given
        Game game = new Game(guess);

        // when
        String output = game.tryInput("1234");
        boolean shouldContinue = game.shouldContinue();

        // then
        assertThat(output, is("Congratulations!"));
        assertThat(shouldContinue, is(false));
    }

    @Test
    public void should_ask_input_with_digit_decreasing_when_play() {

        // given
        Game game = new Game(guess);

        // when
        String ask1 = game.askInput();
        game.tryInput("5555");
        String ask2 = game.askInput();

        // then
        assertThat(ask1, is("Please input your number (6):"));
        assertThat(ask2, is("Please input your number (5):"));
    }

    @Test
    public void should_return_welcome_at_start() {

        // given
        Game game = new Game(guess);

        // when
        String welcome = game.welcome();

        // then
        assertThat(welcome, is("Welcome!"));
    }

    @Test
    public void should_return_game_over_when_game_over() {

        // given
        Game game = new Game(guess);

        // when
        String gameover = game.gameOver();

        // then
        assertThat(gameover, is("Game Over"));
    }

    @Test
    public void should_not_return_game_over_when_not_dead() {

        // given
        Game game = new Game(guess);

        // when
        boolean gameover = game.isGameOver();

        // then
        assertThat(gameover, is(false));
    }

    @Test
    public void should_not_return_game_over_when_success() {

        // given
        Game game = new Game(guess);

        // when
        game.tryInput("1234");
        boolean gameover = game.isGameOver();

        // then
        assertThat(gameover, is(false));
    }

    @Test
    public void should_return_game_over_when_die() {
        // given
        Game game = new Game(guess);

        // when
        game.tryInput("5555");
        game.tryInput("5555");
        game.tryInput("5555");
        game.tryInput("5555");
        game.tryInput("5555");
        game.tryInput("5555");

        boolean gameover = game.isGameOver();

        // then
        assertThat(gameover, is(true));
    }

    @Test
    public void should_show_welcome_at_beginning_and_success() {

        // given
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = new PrintStream(bytes);
        BufferedReader reader = mock(BufferedReader.class);

        try {
            when(reader.readLine()).thenReturn("1234");
        } catch (IOException exc) {

        }

        Game game = new Game(guess);

        // when
        game.run(reader, console);

        // then
        assertThat(bytes.toString(), is("Welcome!\n\nPlease input your number (6):\nCongratulations!\n"));
    }

    @Test
    public void should_show_bad_guess_and_game_over_when_input_bad_numbers() {
        // given
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = new PrintStream(bytes);
        BufferedReader reader = mock(BufferedReader.class);

        try {
            when(reader.readLine()).thenReturn("4321");
        } catch (IOException exc) {

        }

        Game game = new Game(guess);

        String expected = "Welcome!\n\n";
        for (int i = 6; i > 0; i--) {
            expected += String.format("Please input your number (%d):\n", i);
            expected += "0A4B\n";
        }
        expected += "Game Over\n";

        // when
        game.run(reader, console);

        // then
        assertThat(bytes.toString(), is(expected));
    }
}
