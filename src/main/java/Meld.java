import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Meld extends TileCollection {

    @Override
    public String toString() {
        return "{" + super.toString() + "}";
    }

    public boolean isRun() {
        if(tiles.size() <3) {
            return false;
        }
        Collections.sort(tiles);
        boolean valid = true;
        Tile last = null;
        for(Tile t : tiles) {
            if(last != null) {
                if(! last.getColor().equals(t.getColor())) {
                    valid = false;
                    break;
                }
                if(last.getValue() + 1 != t.getValue()) {
                    valid = false;
                    break;
                }
            }
            last = t;
        }
        return valid;
    }

    public boolean isSet() {
        if(tiles.size() < 3) {
            return false;
        }
        Set<Tile> seen = new TreeSet<>();

        boolean valid = true;
        Tile last = null;
        for(Tile t : tiles) {
            if(last != null) {
                if(last.getColor().equals(t.getColor())) {
                    valid = false;
                    break;
                }
                if(last.getValue() != t.getValue()) {
                    valid = false;
                    break;
                }
            }
            last = t;
            seen.add(t);
        }
        if(seen.size() == tiles.size()) {
            return valid;
        }
        return false;
    }

    public boolean validate(){
        if (this.isSet() || this.isRun()){
            return true;
        }
        return false;
    }

}
