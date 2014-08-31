/**
 * Created by liu yang on 8/25/14.
 */
public class Guess {

    public static final String TotalMatch = "4A0B";

    private CompareNumber compareNumber;
    private String answer;
    private boolean[] correctHistory;

    public Guess(AnswerGenerator answerGenerator, CompareNumber compareNumber) {
        this.compareNumber = compareNumber;
        answer = answerGenerator.makeAnswer();

        correctHistory = new boolean[CompareNumberResult.ResultFieldNum];
        for (int i = 0; i < CompareNumberResult.ResultFieldNum; i++) {
            correctHistory[i] = false;
        }
    }

    public GuessResult compare(final String guess) {

        int newHit = 0;
        CompareNumberResult compareResult = compareNumber.compare(answer, guess);

        for (int i = 0; i < CompareNumberResult.ResultFieldNum; i++) {
            if (!correctHistory[i] && compareResult.result[i] == CompareNumberResult.ResultType.Match) {
                correctHistory[i] = true;
                newHit++;
            }
        }

        return new GuessResult(compareResult, newHit);
    }

    public boolean validateInput(final String guess) {
        return compareNumber.validateInput(guess);
    }
}
