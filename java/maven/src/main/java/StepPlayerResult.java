/**
 * Created by liu yang on 8/31/14.
 */
public class StepPlayerResult {

    public enum ResultKey {
        Continue,
        Congrats,
        GameOver,
    }

    private ResultKey key;
    private int value;

    public StepPlayerResult(ResultKey key) {
        this(key, 0);
    }

    public StepPlayerResult(ResultKey key, int value) {
        this.key = key;
        this.value = value;
    }

    public ResultKey getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
