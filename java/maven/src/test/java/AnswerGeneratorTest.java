import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

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

        assertEquals(check.size(), 4);
    }
}
