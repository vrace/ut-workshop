import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by liu yang on 8/25/14.
 */
public class GuessTest {

    private Guess guess;

    @Mock
    private AnswerGenerator answerGenerator;

    @Before
    public void setUp() {
        answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.makeAnswer()).thenReturn("1234");
        guess = new Guess(answerGenerator, new CompareNumber());
    }

    @Test
    public void should_return_4A0B_when_all_match() {
        // given

        // when
        final String result = guess.compare("1234").toString();

        // then
        assertEquals(result, "4A0B");
    }

    @Test
    public void should_return_0A4B_when_all_bad_position() {
        // given

        // when
        final String result = guess.compare("4321").toString();

        // then
        assertEquals(result, "0A4B");
    }

    @Test
    public void should_return_0A0B_when_all_mismatch() {
        // given

        // when
        final String result = guess.compare("5678").toString();

        // then
        assertEquals(result, "0A0B");
    }

    @Test
    public void should_return_1A1B_when_match_1234_with_7253() {
        // given

        // when
        final String result = guess.compare("7253").toString();

        // then
        assertEquals(result, "1A1B");
    }

    @Test
    public void should_return_correct_hit_count_on_first_correct_guess() {

        // given
        final GuessResult result = guess.compare("7253");

        // then
        assertThat(result.getNewHit()).isEqualTo(1);
        assertThat(result.toString()).isEqualTo("1A1B");
    }

    @Test
    public void should_return_zero_hit_count_on_second_correct_guess() {

        // given
        guess.compare("7253");
        final GuessResult result = guess.compare("7253");

        // then
        assertThat(result.getNewHit()).isEqualTo(0);
        assertThat(result.toString()).isEqualTo("1A1B");
    }
}
