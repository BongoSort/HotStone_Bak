package hotstone.standard;

import hotstone.framework.Game;
import hotstone.variants.AlphaStone.AlphaStoneConcreteFactory;

public class TingelTest {

    public static void main(String[] args) {
        Game game  = new StandardHotStoneGame(new AlphaStoneConcreteFactory());
        game.endTurn();
        StandardHotStoneGameTranscriber trans = new StandardHotStoneGameTranscriber(game);
        trans.endTurn();
        trans.endTurn();
        trans.getList().forEach(System.out::println);
        game = trans;
        System.out.println(trans.getList().size());


    }
}
