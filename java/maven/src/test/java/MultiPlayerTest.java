import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by liu yang on 8/29/14.
 */
public class MultiPlayerTest {

    @Test
    public void should_have_correct_input_prompt_format() {

        // given
        final String name = "UserA";
        final String expected = "Please input a number (6), UserA:";
        MultiPlayer player = new MultiPlayer(name);

        // when
        final String output = player.askInput();

        // then
        assertThat(output, is(expected));
    }

    @Test
    public void should_have_correct_normal_win_format() {

        // given
        final String name = "UserA";
        final String expected = "Congratulations, UserA.";
        MultiPlayer player = new MultiPlayer(name);

        // when
        final String output = player.makeWinText(false);

        // then
        assertThat(output, is(expected));
    }

    @Test
    public void should_have_correct_strong_win_format() {

        // given
        final String name = "UserB";
        final String expected = "You got it! Congratulations, UserB!";
        MultiPlayer player = new MultiPlayer(name);

        // when
        final String output = player.makeWinText(true);

        // then
        assertThat(output, is(expected));
    }
}
