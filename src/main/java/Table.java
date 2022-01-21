import java.io.*;
import java.util.*;

public class Table {
    static final String TEXT_CURRENT_TABLE = "\nThe current table is as follows: ";
    Scanner sc;
    Player[] players;
    Deck deck;
    ArrayList<Meld> meldsOnTable;
    Player currentPlayer;
    boolean gameOver = false;

    public Table() {
        deck = new Deck();
        players = new Player[3];
        for (int i = 0; i < 3; i++) {
            players[i] = new Player();
        }
        sc = new Scanner(System.in);

        meldsOnTable = new ArrayList<>();
    }

    // Dealing the tiles to the players
    public void dealTiles() {
        for (int i = 0; i < 14; i++) {
            for (Player p : players) {
                p.getHand().addTile(deck.drawTile());
            }
        }

        for (Player p : players) {
            p.getHand().sort();
        }
    }

    public void drawTile() {
        currentPlayer.getHand().addTile(deck.drawTile());
    }


    public boolean addMeld(List<Meld> listOfMelds) {
        Player p = currentPlayer;
        int sumOfTiles = 0;
        boolean valid;
        boolean isPresentInHand = false;
        for (Meld m : listOfMelds) {
            if (p.getHand().tiles.containsAll(m.tiles)) {
                isPresentInHand = true;
                if (m.validate()) {
                    sumOfTiles += m.totalPoints();
                }
            } else {
                isPresentInHand = false;
            }
        }

        if (isPresentInHand == true) {
            if (p.getTurnsPlayed() == 0) {
                if (sumOfTiles >= 30) {
                    addMultipleMeldsToTable(listOfMelds);
                    valid = true;
                } else {
                    System.out.println("The tiles dont add to 30");
                    valid = false;
                }
            } else {
                addMultipleMeldsToTable(listOfMelds);
                valid = true;
            }
        } else {
            System.out.println("The tiles you want to add are not present in your hand");
            valid = false;
        }
        return valid;
    }

    private void addMultipleMeldsToTable(List<Meld> listOfMelds) {
        for (Meld m: listOfMelds){
            meldsOnTable.add(m);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < meldsOnTable.size(); i++) {
            Meld meld = meldsOnTable.get(i);
            sb.append(meld.toString());
            if (i != meldsOnTable.size() - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public Player getWinner() {
        for (Player p : players) {
            if (p.getHand().sizeOfCollection() == 0) {
                gameOver = true;
                Arrays.sort(players);
                return p;
            }
        }
        return null;
    }


    public void welcome() {
        System.out.println("Welcome to the Rummykub Game");
        for (int i = 0; i < 3; i++) {
            System.out.printf("Player %d : Please enter your name \n", i + 1);
            String name = sc.nextLine().strip();
            players[i].setName(name);
        }
    }

    public void makePlay() {
        Player p = currentPlayer;
        ArrayList<Meld> tempList = new ArrayList<Meld>();
        String choice;
        boolean endPlay = false;
        do {
            System.out.println("What's your play");
            String play = sc.nextLine();
            String play_1 = play.strip();
            Meld tempMeld = p.getHand().makeMeldPlay(play_1);
            tempList.add(tempMeld);
            System.out.println(tempList);
            System.out.println("Do you want to make another play? (Y/N)");
            choice = sc.nextLine();

            if (choice.equals("Y")) {
                endPlay = false;
            } else {
                endPlay = true;
            }
        } while (!endPlay);

        if (addMeld(tempList)) {
            for (Meld m : tempList) {
                for (Tile t : m.tiles) {
                    p.getHand().remove(t);
                }
            }
            p.setTurnsPlayed(p.getTurnsPlayed() + 1);
        } else {
            System.out.println("Couldn't make move");
            drawTile();
        }
    }

    public void playGame() {
        while (!gameOver && getWinner() == null) {
            for (Player p : players) {
                currentPlayer = p;
                boolean endRound = false;
                System.out.println(TEXT_CURRENT_TABLE);
                System.out.println(this);
                System.out.printf("%s's turn - Current players hand\n", currentPlayer.getName());
                currentPlayer.getHand().sort();
                System.out.println(currentPlayer.getHand());

                do {
                    System.out.println("\nWhat would you like to do?");
                    System.out.println("Press 1 to draw a tile");
                    System.out.println("Press 2 to make a play");
                    String option = sc.nextLine();
                    if (option.equals("1")) {
                        drawTile();
                        System.out.println("\nThe player has drawn a tile");
                        p.getHand().sort();
                        System.out.println("The updated hand:"+p.getHand().toString());
                        endRound = true;
                    } else if (option.equals("2")) {
                        makePlay();
                        endRound = true;
                    }

                    if(getWinner()!=null){
                        gameOver = true;
                    }

                } while ((!endRound));

                if(getWinner()!=null){
                    System.out.println("The winner of the game is: "+getWinner().getName());
                    int points = getWinner().totalPoints;
                    for(Player p2 : players) {
                        if(p2 != getWinner()) {
                            int penalty = p2.getHand().totalPoints();
                            p2.updateScore(-penalty);
                            points += penalty;
                        }
                    }
                    System.out.println("Total score of winner: " + getWinner().getTotalPoints());

                    Arrays.sort(players);
                    for(Player p2 : players) {
                        if(p2 != getWinner()) {
                            System.out.println(p2.getName() + " as scored " + p2.getTotalPoints());
                        }
                    }

                    break;
                }
                else if(deck.getDeckSize() == 0){
                    System.out.println("The deck is over");
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        Table table = new Table();
        table.dealTiles();
        table.welcome();
        table.playGame();
    }
}