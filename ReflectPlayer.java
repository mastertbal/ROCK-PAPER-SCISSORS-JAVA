import java.util.Random;

public class ReflectPlayer extends Player {
    private String reflectMove;

    public ReflectPlayer() {
        this.score = 0;
        this.move = "";
        this.reflectMove = "";
    }

    @Override
    public String makeMove() {
        if (this.reflectMove.equals("")) {
            Random random = new Random();
            this.move = MainApp.movesList.get(random.nextInt(3));
            return this.move;
        } else {
            this.move = this.reflectMove;
            return this.move;
        }
    }

    @Override
    public void learnMove(String opponentMove) {
        this.reflectMove = opponentMove;
    }

}
