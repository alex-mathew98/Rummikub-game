import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class PlayerChoosingToDraw{

    Table t;
    final PrintStream out = System.out;
    ByteArrayOutputStream bout = new ByteArrayOutputStream();

    //Helper function to rig the players hand with the new tiles
    void rigHand(Player player, List<Tile> cheats) {
        for(Tile tile : cheats) {
            player.getHand().remove(0);
            player.getHand().addTile(tile);
        }
    }


    public PlayerChoosingToDraw(){
        t = new Table();
    }

    @Test
    public void drawTest1(){
        System.out.println("Draw test 1");

        t.sc = new Scanner("P1\nP2\nP3\n" + //Input for player names
                "1\n" // Input for player one to draw a card and play his turn
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.G, 2), new Tile(Colors.R, 2), new Tile(Colors.O, 2),
                                      new Tile(Colors.G, 3), new Tile(Colors.R, 3), new Tile(Colors.O, 3),
                                      new Tile(Colors.O, 8), new Tile(Colors.O, 9), new Tile(Colors.O, 10),
                                      new Tile(Colors.R, 8), new Tile(Colors.R, 9), new Tile(Colors.R, 10),new Tile(Colors.G, 12), new Tile(Colors.R, 7)
        ));

        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        //Validation for checking the hands of the players
        assertEquals(15,t.players[0].getHand().sizeOfCollection());

    }


    @Test
    public void drawTest2(){

        System.out.println("Draw test 2");
        t.sc = new Scanner("P1\nP2\nP3\n" + //Input for player names
                "1\n" // Input for player one to draw a card and play his turn
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.G, 2), new Tile(Colors.G, 2), new Tile(Colors.O, 2),
                                      new Tile(Colors.R, 3), new Tile(Colors.B, 3), new Tile(Colors.B, 3),
                                      new Tile(Colors.R, 5), new Tile(Colors.B, 6), new Tile(Colors.O, 7),
                                      new Tile(Colors.R, 9), new Tile(Colors.R, 10), new Tile(Colors.G, 11),new Tile(Colors.B, 12), new Tile(Colors.B, 13)
        ));

        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        //Validation for checking the hands of the players
        assertEquals(15,t.players[0].getHand().sizeOfCollection());

    }
}
