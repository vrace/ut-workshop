import java.util.Random;

/**
 * Created by liu yang on 8/25/14.
 */
public class AnswerGenerator {

    private Random rand;

    public AnswerGenerator(Random rand) {
        this.rand = rand;
    }

    public String makeAnswer() {

        int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        for (int i = 0; i < 10; i++) {

            int pos = rand.nextInt(10);

            int t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }

        return String.format("%d%d%d%d", arr[0], arr[1], arr[2], arr[3]);
    }

}
