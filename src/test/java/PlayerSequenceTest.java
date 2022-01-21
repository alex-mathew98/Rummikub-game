import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerSequenceTest {

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

    //Helper function to rig the players hand with the new tiles
    void rigHand(Player player, List<Tile> cheats) {
        for(Tile tile : cheats) {
            player.getHand().remove(0);
            player.getHand().addTile(tile);
        }
    }

    public PlayerSequenceTest() {
        t = new Table();
    }

    @Test
    public void testPlayerSequence() {

        t.sc = new Scanner("P1\nP2\nP3\n" + //Input for player names
                "1\n" + // Input for player one to draw a card and play his turn
                "2\nR11 R12 R13\nN\n" + // input for player two who plays R11, R12, 13
                "2\nR13 B13 G13\nY\nR2 B2 O2\nN\n" + // input for player three  who plays two melds R13 G13 B13 B2 R2 O2
                "2\nR12 G12 O12\nN\n" //                "2\nR12 G12 O12\n3\n" // input for player 1 who plays R12 G12 O12
        );

        t.welcome();
        t.dealTiles();

        //Rigging the players hands after dealing the tiles
        rigHand(t.players[1], List.of(new Tile(Colors.R, 12), new Tile(Colors.R, 11), new Tile(Colors.R, 13)));
        rigHand(t.players[2], List.of(new Tile(Colors.R, 13), new Tile(Colors.G, 13), new Tile(Colors.B, 13),new Tile(Colors.B, 2), new Tile(Colors.R, 2), new Tile(Colors.O, 2)));
        rigHand(t.players[0], List.of(new Tile(Colors.R, 12), new Tile(Colors.G, 12), new Tile(Colors.O, 12)));

        try {
            t.playGame();
        } catch (NoSuchElementException nex) {

        }

        String[] lines = bout.toString().split("\n");
        int i = validateTable(lines, "",0); // At the start of the game empty table is show
        i = validateTable(lines, "", i); // After player 1 picks a tile empty board is shown.
        i = validateTable(lines, "{R11 R12 R13}", i);//After player 2 plays his tiles,the first meld needs to be shown on the table
        i = validateTable(lines, "{R11 R12 R13} {R13 G13 B13} {R2 B2 O2}", i);//After player 3 plays his tiles,the three melds need to be shown on the table
        i = validateTable(lines, "{R11 R12 R13} {R13 G13 B13} {R2 B2 O2} {R12 G12 O12}", i);// After player 1 plays his turn,the fours melds need to be shown on the table

        //Validation for checking the hands of the players
        assertEquals(12,t.players[0].getHand().sizeOfCollection());
        assertEquals(11,t.players[1].getHand().sizeOfCollection());
        assertEquals(8,t.players[2].getHand().sizeOfCollection());

        //Validation for checking the melds on the table.
        assertEquals(4,t.meldsOnTable.size());
    }
}