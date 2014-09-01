import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by liu yang on 8/25/14.
 */
public class CompareNumberTest {

    CompareNumber compareNumber;

    @Before
    public void setUp() {

        compareNumber = new CompareNumber();

    }

    @Test
    public void should_return_all_match() {

        // given
        final String answer = "1234";
        final String guess = "1234";

        // when
        final String evalResult = compareNumber.compare(answer, guess).toString();

        // then
        assertEquals(evalResult, "4A0B");
    }

    @Test
    public void should_return_all_mismatch() {

        // given
        final String answer = "1234";
        final String guess = "5678";

        // when
        final String evalResult = compareNumber.compare(answer, guess).toString();

        // then
        assertEquals(evalResult, "0A0B");
    }

    @Test
    public void should_return_bad_position_match() {

        // given
        final String answer = "1234";
        final String guess = "4321";

        // when
        final String evalResult = compareNumber.compare(answer, guess).toString();

        // then
        assertEquals(evalResult, "0A4B");
    }

    @Test
    public void should_return_1A1B_when_match_1234_with_2537() {

        // given
        final String answer = "1234";
        final String guess = "2537";

        // when
        final String evalResult = compareNumber.compare(answer, guess).toString();

        // then
        assertEquals(evalResult, "1A1B");
    }

    @Test
    public void should_have_correct_field_marked() {

        // given
        final String answer = "1234";
        final String guess = "1024";

        // when
        CompareNumberResult result = compareNumber.compare(answer, guess);

        // then
        assertThat(result.result[0]).isEqualTo(CompareNumberResult.ResultType.Match);
        assertThat(result.result[1]).isEqualTo(CompareNumberResult.ResultType.NotMatch);
        assertThat(result.result[2]).isEqualTo(CompareNumberResult.ResultType.BadPosition);
        assertThat(result.result[3]).isEqualTo(CompareNumberResult.ResultType.Match);

        assertThat(result.toString()).isEqualTo("2A1B");
    }
}
