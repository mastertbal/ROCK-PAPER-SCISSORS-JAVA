/* A basic player class that has a score and a move */

public class Player {
    public int score;
    public String move;

    /* Constructor */
    public Player() {
        this.score = 0;
        this.move = "";
    }

    public Player(int score, String move) {
        this();
    }

    /* Make move method */
    public String makeMove() {
        return "rock";
    }

    /* Learn the move of an opponent */
    public void learnMove(String opponentMove) {
        // do nothing
    }

}
