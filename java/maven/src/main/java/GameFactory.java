import java.util.Random;

/**
 * Created by liu yang on 8/28/14.
 */
public class GameFactory {

    public GameInterface createGame(GameType type) {

        switch (type) {
            case SinglePlayerGame:
                return createSinglePlayerGame();
            case MultiPlayerGame:
                return createMultiPlayerGame();
            default:
                return null;
        }

    }

    private Guess createGuess() {
        Random rand = new Random();
        AnswerGenerator answerGenerator = new AnswerGenerator(rand);
        CompareNumber compareNumber = new CompareNumber();
        return new Guess(answerGenerator, compareNumber);
    }

    private GameInterface createSinglePlayerGame() {
        return new SinglePlayerGame(createGuess(), new Player());
    }

    private GameInterface createMultiPlayerGame() {
        // TODO: implement this
        return null;
    }
}
