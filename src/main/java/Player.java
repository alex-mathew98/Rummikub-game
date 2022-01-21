public class Player implements Comparable<Player> {

    Hand h;
    String name;
    int turnsPlayed;

    int totalPoints;

    public Player() {
        h = new Hand();
        totalPoints = 0;
    }

    public Player(String n) {
        h = new Hand();
        name = n;
    }

    public Hand getHand() {
        return h;
    }

    public void setHand(Hand h) {
        this.h = h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    public void setTurnsPlayed(int turnsPlayed) {
        this.turnsPlayed = turnsPlayed;
    }

    public void updateScore(int points) {
        totalPoints += points;
    }


    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public int compareTo(Player o) {

        if(getHand().sizeOfCollection() == 0) {
            return -1;
        }
        if(o.getHand().sizeOfCollection() == 0) {
            return 1;
        }
        return (totalPoints - h.totalPoints()) -
                (o.totalPoints - o.getHand().totalPoints());


    }
}