/**
 * Created by liu yang on 8/25/14.
 */
public class Guess {

    public static final String TotalMatch = "4A0B";

    private CompareNumber compareNumber;
    private String answer;

    public Guess(AnswerGenerator answerGenerator, CompareNumber compareNumber) {
        this.compareNumber = compareNumber;
        answer = answerGenerator.makeAnswer();
    }

    public String compare(final String guess) {
        return compareNumber.compare(answer, guess);
    }

    public boolean validateInput(final String guess) {
        return compareNumber.validateInput(guess);
    }
}
