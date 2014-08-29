import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by liu yang on 8/29/14.
 */
public class PlayerTest {

    @Test
    public void should_have_chance_at_beginning() {

        // given
        Player player = new Player();

        // when
        boolean hasChance = player.hasMoreChance();

        // then
        assertThat(hasChance, is(true));
    }

    @Test
    public void should_return_true_after_waste_most_chances() {

        // given
        Player player = new Player();

        // when
        for (int i = 0; i < player.InitialChance - 1; i++) {
            player.removeChance();
        }

        boolean hasChance = player.hasMoreChance();

        // then
        assertThat(hasChance, is(true));
    }

    @Test
    public void should_return_false_after_waste_all_chances() {

        // given
        Player player = new Player();

        // when
        for (int i = 0; i < Player.InitialChance; i++) {
            player.removeChance();
        }

        boolean hasChance = player.hasMoreChance();

        // then
        assertThat(hasChance, is(false));
    }

    @Test
    public void should_have_correct_input_prompt_format() {

        // given
        Player player = new Player();

        // when
        final String prompt = player.askInput();
        final String expected = String.format("Please input a number (%d):", Player.InitialChance);

        // then
        assertThat(prompt, is(expected));
    }
}
