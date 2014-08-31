import java.util.HashSet;

/**
 * Created by liu yang on 8/25/14.
 */
public class CompareNumber {

    public boolean validateInput(final String answer) {
        if (answer != null && answer.length() == 4) {
            HashSet<Character> testSet = new HashSet<Character>();
            for (char c : answer.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    testSet.add(c);
                }
            }

            return testSet.size() == 4;
        }
        return false;
    }

    public CompareNumberResult compare(final String answer, final String guess) {

        CompareNumberResult result = new CompareNumberResult();

        for (int i = 0; i < CompareNumberResult.ResultFieldNum; i++) {

            int index = answer.indexOf(guess.charAt(i));

            if (index == i) {

                result.result[i] = CompareNumberResult.ResultType.Match;

            } else if (index >= 0) {

                result.result[i] = CompareNumberResult.ResultType.BadPosition;

            }

        }

        return result;
    }
}
