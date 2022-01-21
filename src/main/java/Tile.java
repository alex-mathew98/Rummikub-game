import java.util.Objects;

//Enum for colors of the class
enum Colors {
    R(0), B(1), G(2), O(3);
    int colorValue;

    Colors(int i) {
        colorValue = i;
    }

    int getColorValue() {
        return colorValue;
    }
}

//This class is used for representing the tiles in the Rummikub game
public class Tile implements Comparable<Tile> {
    private Colors color;
    int value;
    int points;

    //Constructor to initialize the tiles
    public Tile(Colors color, int value) {

        if (value < 1 || value > 14) {
            throw new IllegalArgumentException("Card value out of range");
        }
        this.color = color;
        this.value = value;
        if(this.value >= 10){
            this.points = 10;
        }
        else{
            this.points = this.value;
        }
    }

    public Colors getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(Tile o) {
        if (o.getColor().equals(color)) {
            return value - o.getValue();
        }
        return color.getColorValue() - o.getColor().getColorValue();
    }


    @Override
    public String toString() {
        return color + "" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return value == tile.value && color == tile.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value);
    }

}
