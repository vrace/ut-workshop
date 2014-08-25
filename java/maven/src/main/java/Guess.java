/**
 * Created by liu yang on 8/25/14.
 */
public class Guess {

    private CompareNumber compareNumber;
    private String answer;

    public Guess(AnswerGenerator answerGenerator) {
        compareNumber = new CompareNumber();
        answer = answerGenerator.makeAnswer();
    }

    public String compare(final String guess) {
        return compareNumber.compare(answer, guess);
    }
}
