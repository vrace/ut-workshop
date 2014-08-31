/**
 * Created by liu yang on 8/31/14.
 */
public class CompareNumberResult {

    public enum ResultType {
        NotMatch,
        Match,
        BadPosition,
    }

    public static final int ResultFieldNum = 4;
    public ResultType[] result;

    public CompareNumberResult() {

        result = new ResultType[ResultFieldNum];

        for (int i = 0; i < ResultFieldNum; i++) {
            result[i] = ResultType.NotMatch;
        }
    }

    @Override
    public String toString() {

        int matchCount = 0;
        int badPositionCount = 0;

        for (ResultType t : result) {

            switch (t) {

                case Match:
                    matchCount++;
                    break;

                case BadPosition:
                    badPositionCount++;
                    break;

                default:
                    break;
            }

        }

        return String.format("%dA%dB", matchCount, badPositionCount);
    }
}
