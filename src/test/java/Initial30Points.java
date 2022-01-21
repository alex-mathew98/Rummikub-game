import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class Initial30Points {

    Table t;
    final PrintStream out = System.out;
    ByteArrayOutputStream bout = new ByteArrayOutputStream();

    //Helper function to validate the melds on the table
    private int validateTable(String[] lines, String match, int offset) {
        int i;
        for(i = offset; i < lines.length ; i++) {
            String line = lines[i];
            if(line.equals("\nThe current table is as follows: ")) {

                assertEquals(match, lines[i + 1]);
                return i+ 1;
            }
        }
        return i;
    }

    public Initial30Points(){
        t = new Table();
    }
    //Helper function to rig the players hand with the new tiles
    void rigHand(Player player, List<Tile> cheats) {
        for(Tile tile : cheats) {
            player.getHand().remove(0);
            player.getHand().addTile(tile);
        }
    }

    @BeforeEach
    void setUp() {
        t = new Table();
    }

    @Test
    public void threeCardRun(){
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR11 R12 R13\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 11), new Tile(Colors.R, 12), new Tile(Colors.R, 13)));


        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0);
        i = validateTable(lines, "{R11 R12 R13}", i);

        //Validation for checking the hands of the players
        assertEquals(11,t.players[0].getHand().sizeOfCollection());

        //Validation for checking the melds on the table
        assertEquals(1,t.meldsOnTable.size());


    }

    @Test
    public void threeCardSet(){
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR12 G12 B12\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 12), new Tile(Colors.G, 12), new Tile(Colors.B, 12)));


        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0);
        i = validateTable(lines, "{R12 G12 B12}", i);

        //Validation for checking the hands of the players
        assertEquals(11,t.players[0].getHand().sizeOfCollection());

        //Validation for checking the melds on the table.
        assertEquals(1,t.meldsOnTable.size());
    }

    @Test
    public void fiveCardRun(){
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR9 R10 R11 R12 R13\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 9), new Tile(Colors.R, 10), new Tile(Colors.R, 11), new Tile(Colors.R, 12), new Tile(Colors.R, 13)));

        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0);
        i = validateTable(lines, "{R9 R10 R11 R12 R13}", i);

        //Validation for checking the hands of the players
        assertEquals(9,t.players[0].getHand().sizeOfCollection());

        //Validation for checking the melds on the table.
        assertEquals(1,t.meldsOnTable.size());
    }

    @Test
    public void fourCardSet(){
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR13 G13 B13 O13\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 13), new Tile(Colors.G, 13), new Tile(Colors.B, 13), new Tile(Colors.O, 13)));

        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0);
        i = validateTable(lines, "{R13 G13 B13 O13}", i);

        //Validation for checking the hands of the players
        assertEquals(10,t.players[0].getHand().sizeOfCollection());

        //Validation for checking the melds on the table.
        assertEquals(1,t.meldsOnTable.size());
    }

    @Test
    public void twoRuns(){
        t.sc = new Scanner("P1\nP2\nP3\n" + //Input for player names
                "2\nR2 R3 R4\nY\nB7 B8 B9\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 2), new Tile(Colors.R, 3), new Tile(Colors.R, 4), new Tile(Colors.B, 7), new Tile(Colors.B, 8), new Tile(Colors.B, 9)));

        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0); // At the start of the game empty table is show
        i = validateTable(lines, "{R2 R3 R4} {B7 B8 B9}", i);//After player 2 plays his tiles,the first meld needs to be shown on the table

        //Validation for checking the hands of the players
        assertEquals(8,t.players[0].getHand().sizeOfCollection());

        //Validation for checking the melds on the table.
        assertEquals(2,t.meldsOnTable.size());
    }

    @Test
    public void threeSets(){
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR2 B2 O2\nY\nG4 O4 B4 R4\nY\nO5 B5 R5\nN"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 2), new Tile(Colors.B, 2), new Tile(Colors.O, 2),
                new Tile(Colors.G, 4), new Tile(Colors.O, 4), new Tile(Colors.B, 4), new Tile(Colors.R, 4),
                new Tile(Colors.O, 5), new Tile(Colors.B, 5), new Tile(Colors.R, 5)));

        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0);
        i = validateTable(lines, "{R2 B2 O2} {R4 B4 G4 O4} {R5 B5 O5}", i);

        //Validation for checking the hands of the players
        assertEquals(4,t.players[0].getHand().sizeOfCollection());

        //Validation for checking the melds on the table.
        assertEquals(3,t.meldsOnTable.size());
    }

    @Test
    public void setAndRun(){
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR8 G8 O8\nY\nR2 R3 R4\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 8), new Tile(Colors.G, 8), new Tile(Colors.O, 8),
                new Tile(Colors.R, 2), new Tile(Colors.R, 3), new Tile(Colors.R, 4)));
        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0);
        i = validateTable(lines, "{R8 G8 O8} {R2 R3 R4}", i);

        //Validation for checking the hands of the players
        assertEquals(8,t.players[0].getHand().sizeOfCollection());

        //Validation for checking the melds on the table.
        assertEquals(2,t.meldsOnTable.size());
    }


    @Test
    public void twoSetsAndtwoRuns(){
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR2 O2 B2\nY\nG2 G3 G4\nY\nR3 B3 O3\nY\nB5 B6 B7\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 2), new Tile(Colors.O, 2), new Tile(Colors.B, 2),
                new Tile(Colors.G, 2), new Tile(Colors.G, 3), new Tile(Colors.G, 4),
                new Tile(Colors.R, 3), new Tile(Colors.B, 3), new Tile(Colors.O, 3),
                new Tile(Colors.B, 5), new Tile(Colors.B, 6), new Tile(Colors.B, 7)));
        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0);
        i = validateTable(lines, "{R2 B2 O2} {G2 G3 G4} {R3 B3 O3} {B5 B6 B7}", i);

        //Validation for checking the hands of the players
        assertEquals(2,t.players[0].getHand().sizeOfCollection());

        //Validation for checking the melds on the table.
        assertEquals(4,t.meldsOnTable.size());
    }

    @Test
    public void winningHand(){
        t.sc = new Scanner("P1\nP2\nP3\n" +
                "2\nR2 B2 G2 O2\nY\nG3 G4 G5 G6 G7\nY\nO4 O5 O6 O7 O8\nN\n"
        );

        t.welcome();
        t.dealTiles();

        rigHand(t.players[0], List.of(new Tile(Colors.R, 2), new Tile(Colors.B, 2), new Tile(Colors.G, 2), new Tile(Colors.O, 2),
                new Tile(Colors.G, 3), new Tile(Colors.G, 4), new Tile(Colors.G, 5),new Tile(Colors.G, 6), new Tile(Colors.G, 7),
                new Tile(Colors.O, 4), new Tile(Colors.O, 5), new Tile(Colors.O, 6), new Tile(Colors.O, 7), new Tile(Colors.O, 8)));
        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0);
        i = validateTable(lines, "{R2 B2 G2 O2} {G3 G4 G5 G6 G7} {O4 O5 O6 O7 O8}", i);

        //Validation for checking the hands of the players
        assertEquals(0,t.players[0].getHand().sizeOfCollection());
        //Validation for checking the winner of the game
        assertEquals("P1",t.getWinner().getName());
        //Validation for checking the melds on the table.
        assertEquals(3,t.meldsOnTable.size());
    }
}
