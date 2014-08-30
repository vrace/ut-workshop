import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by liu yang on 8/29/14.
 */
public class GameFactoryTest {

    private GameFactory factory;

    @Before
    public void setup() {
        factory = new GameFactory();
    }

    @Test
    public void should_return_null_when_invalid_game_type_supplied() {

        // given
        GameType type = GameType.InvalidGame;

        // when
        GameInterface game = factory.createGame(type);
        boolean nullGame = (game == null);

        // then
        assertThat(nullGame, is(true));
    }

    @Test
    public void should_return_single_player_game_when_story_mode_supplied() {

        // given
        GameType type = GameType.SinglePlayerGame;

        // when
        GameInterface game = factory.createGame(type);
        boolean isSingleMode = game instanceof SinglePlayerGame;

        // then
        assertThat(isSingleMode, is(true));
    }

    @Test
    public void should_return_multi_player_game_when_fight_mode_supplied() {

        // given
        GameType type = GameType.MultiPlayerGame;

        // when
        GameInterface game = factory.createGame(type);
        boolean isMultiMode = game instanceof MultiPlayerGame;

        // then
        assertThat(isMultiMode, is(true));
    }
}
