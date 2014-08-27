import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by liu yang on 8/25/14.
 */
public class AnswerGeneratorTest {

    private AnswerGenerator generator;

    @Before
    public void setUp() {

        generator = new AnswerGenerator();

    }

    @Test
    public void should_has_no_repeat_digit_in_answer() {

        // given
        HashSet<Character> check = new HashSet<Character>();

        // when
        final String answer = generator.makeAnswer();
        for (Character c : answer.toCharArray()) {
            check.add(c);
        }

        // that
        assertEquals(check.size(), 4);
    }

    @Test
    public void should_have_only_digits() {

        // given
        String digits = "0123456789";

        // when
        String answer = generator.makeAnswer();
        int converted = Integer.parseInt(answer);
        String convertedAnswer = String.format("%04d", converted);

        // that
        assertThat(answer, is(convertedAnswer));
    }
}
