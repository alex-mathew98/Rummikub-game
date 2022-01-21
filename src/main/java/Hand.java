import java.util.*;

public class Hand extends TileCollection{


    public String toString() {
        return "" + super.toString() + "";
    }

    public Meld makeMeldPlay(String tilesSent) {
        String parts[] = tilesSent.split(" ");
        Meld m = new Meld();
        for (String part : parts) {
            Tile t = null;
            char c = part.charAt(0);
            int value = Integer.parseInt(part.substring(1));
            switch (c) {
                case 'R':
                    t = new Tile(Colors.R, value);
                    break;
                case 'G':
                    t = new Tile(Colors.G, value);
                    break;
                case 'B':
                    t = new Tile(Colors.B, value);
                    break;
                case 'O':
                    t = new Tile(Colors.O, value);
                    break;
            }
            if (t != null) {
                m.addTile(t);

            }
        }

        return m;
    }
}
