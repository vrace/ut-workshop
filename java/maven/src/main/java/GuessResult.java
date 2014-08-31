/**
 * Created by liu yang on 8/31/14.
 */
public class GuessResult {

    private int newHit;
    private CompareNumberResult compareResult;

    public GuessResult(CompareNumberResult compareResult, int newHit) {
        this.compareResult = compareResult;
        this.newHit = newHit;
    }

    public int getNewHit() {
        return newHit;
    }

    public String toString() {
        return compareResult.toString();
    }
}
