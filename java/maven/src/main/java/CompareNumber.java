/**
 * Created by liu yang on 8/25/14.
 */
public class CompareNumber {

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
