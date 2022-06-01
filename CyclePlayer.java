public class CyclePlayer extends Player {

    public CyclePlayer() {
        this.score = 0;
        this.move = "";
    }

    @Override
    public String makeMove() {
        if (this.move == "")
            return "rock";
        else if (this.move == "rock")
            return "paper";
        else if (this.move == "paper")
            return "scissors";

        else
            return "rock";
    }

    @Override
    public void learnMove(String opponentMove) {
        super.learnMove(opponentMove);
    }

}
