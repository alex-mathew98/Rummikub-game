import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayingMeldsAfterInitial30 {

    Table t;
    final PrintStream out = System.out;
    ByteArrayOutputStream bout = new ByteArrayOutputStream();

    public PlayingMeldsAfterInitial30(){
        t = new Table();
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR11 R12 R13\nN\n"+
                "2\nB11 B12 B13\nN\n"+
                "2\nO11 O12 O13\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 11), new Tile(Colors.R, 12), new Tile(Colors.R, 13)));
        rigHand(t.players[1], List.of(new Tile(Colors.B, 11), new Tile(Colors.B, 12), new Tile(Colors.B, 13)));
        rigHand(t.players[2], List.of(new Tile(Colors.O, 11), new Tile(Colors.O, 12), new Tile(Colors.O, 13)));

        try{
            t.playGame();
        } catch (NoSuchElementException nex) {

        }
    }


    //Helper function to rig the players hand with the new tiles
    void rigHand(Player player, List<Tile> cheats) {
        for(Tile tile : cheats) {
            player.getHand().remove(0);
            player.getHand().addTile(tile);
        }
    }


    @Test
    public void playSingleRun(){

        rigHand(t.players[0], List.of(new Tile(Colors.G, 2), new Tile(Colors.G, 3), new Tile(Colors.G, 4)));

        t.sc = new Scanner(
                "2\nG2 G3 G4\nN\n"
        );

        try{
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        //Validation for checking the hands of the players
        assertEquals(8,t.players[0].getHand().sizeOfCollection());

    }

    @Test
    public void playTwoRuns(){

        rigHand(t.players[0], List.of(new Tile(Colors.G, 2), new Tile(Colors.G, 3), new Tile(Colors.G, 4),new Tile(Colors.O, 8), new Tile(Colors.O, 9), new Tile(Colors.O, 10)));

        t.sc = new Scanner(
                "2\nG2 G3 G4\nY\nO8 O9 O10\nN\n"
        );

        try{
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        //Validation for checking the hands of the players
        assertEquals(5,t.players[0].getHand().sizeOfCollection());
    }

    @Test
    public void playSingleSet(){
        rigHand(t.players[0], List.of(new Tile(Colors.G, 2), new Tile(Colors.R, 2), new Tile(Colors.O, 2)));

        t.sc = new Scanner(
                "2\nG2 R2 O2\nN\n"
        );

        try{
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        //Validation for checking the hands of the players
        assertEquals(8,t.players[0].getHand().sizeOfCollection());
    }

    @Test
    public void playTwoSets(){
        rigHand(t.players[0], List.of(new Tile(Colors.G, 2), new Tile(Colors.R, 2), new Tile(Colors.O, 2),new Tile(Colors.O, 8), new Tile(Colors.R, 8), new Tile(Colors.B, 8), new Tile(Colors.G, 8)));

        t.sc = new Scanner(
                "2\nG2 R2 O2\nY\nO8 R8 B8 G8\nN\n"
        );

        try{
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        //Validation for checking the hands of the players
        assertEquals(4,t.players[0].getHand().sizeOfCollection());
    }

    @Test
    public void playSetAndRun(){
        rigHand(t.players[0], List.of(new Tile(Colors.G, 2), new Tile(Colors.R, 2), new Tile(Colors.O, 2),
                                      new Tile(Colors.O, 8), new Tile(Colors.O, 9), new Tile(Colors.O, 10)));

        t.sc = new Scanner(
                "2\nG2 R2 O2\nY\nO8 O9 O10\nN\n"
        );

        try{
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        //Validation for checking the hands of the players
        assertEquals(5,t.players[0].getHand().sizeOfCollection());
    }

    @Test
    public void playTwoSetsAndRun(){
        rigHand(t.players[0], List.of(new Tile(Colors.G, 2), new Tile(Colors.R, 2), new Tile(Colors.O, 2),
                                      new Tile(Colors.G,3 ), new Tile(Colors.R, 3), new Tile(Colors.O, 3),
                                      new Tile(Colors.G, 8), new Tile(Colors.G, 9), new Tile(Colors.G, 10),new Tile(Colors.G, 11), new Tile(Colors.G, 12)
                ));

        t.sc = new Scanner(
                "2\nG2 R2 O2\nY\nG3 R3 O3\nY\nG8 G9 G10 G11 G12\nN\n"
        );

        try{
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        //Validation for checking the hands of the players
        assertEquals(0,t.players[0].getHand().sizeOfCollection());
    }
}
