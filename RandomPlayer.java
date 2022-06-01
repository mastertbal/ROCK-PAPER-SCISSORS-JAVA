import java.util.Random;

public class RandomPlayer extends Player {

    Random random;

    public RandomPlayer() {
        this.score = 0;
        this.move = "";
    }

    @Override
    public String makeMove() {
        random = new Random();
        this.move = MainApp.movesList.get(random.nextInt(3));
        return this.move;
    }

    @Override
    public void learnMove(String opponentMove) {
        super.learnMove(opponentMove);
    }

}
