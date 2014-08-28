import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Created by liu yang on 8/28/14.
 */
public interface GameInterface {
    public void init(BufferedReader in, PrintStream out);
    public void step();
    public boolean shouldContinue();
    public String resultText();
}
