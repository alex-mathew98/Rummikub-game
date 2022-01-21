import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileCollection {
    protected List<Tile> tiles;

    public TileCollection() {
        this.tiles = new ArrayList<>();
    }

    public int totalPoints() {
        int total = 0;
        for(Tile t : tiles) {
            total += t.getPoints();
        }

        return total;
    }

    public int sizeOfCollection(){
        return tiles.size();
    }

    public void addTile(Tile c) {
        tiles.add(c);
    }

    public Tile remove(Tile t) {
        tiles.remove(t);
        return t;
    }

    public Tile remove(int pos) {
        if(tiles.isEmpty()) {
            throw new IllegalStateException("The tile collection is empty");
        }
        if(pos < 0 || pos >= tiles.size()) {
            throw new IllegalStateException("No such card");
        }
        Tile t = tiles.remove(pos);
        return t;
    }

    public void sort() {
        Collections.sort(tiles);
    }


    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for(int i = 0 ; i < tiles.size() ; i++) {
            Tile t = tiles.get(i);
            b.append(t.toString());
            if(i != tiles.size() -1) {
                b.append(" ");
            }
        }

        return b.toString();
    }
}
