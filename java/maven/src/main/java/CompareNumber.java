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

    public String compare(final String answer, final String guess) {

        int match = 0;
        int wrongPosition = 0;

        for (int i = 0; i < 4; i++) {

            int index = answer.indexOf(guess.charAt(i));

            if (index == i) {

                match++;

            } else if (index >= 0) {

                wrongPosition++;

            }

        }

        return String.format("%dA%dB", match, wrongPosition);
    }
}
