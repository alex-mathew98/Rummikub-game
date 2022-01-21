import java.util.Collections;
import java.util.Stack;
import java.util.Random;

public class Deck {
    Stack<Tile> tiles;
    Random rand;
    public Deck() {
        this(new Random());
    }

    public int getDeckSize(){
        return tiles.size();
    }

    public Deck(Random rand) {
        this.rand = rand;
        tiles = new Stack<>();
        for(int j = 0 ; j < 2 ; j++) {
            for (Colors c : Colors.values()) {
                for (int i = 1; i < 14; i++) {
                    Tile t = new Tile(c, i);
                    tiles.add(t);
                }
            }
        }
        Collections.shuffle(tiles, rand);
    }

    public Tile drawTile() {
        return tiles.pop();
    }

}
