import java.util.Scanner;

public class HumanPlayer extends Player {

    // Create a scanner object to read a move
    Scanner scanner;

    public HumanPlayer() {
        this.score = 0;
        this.move = "";
    }

    @Override
    public String makeMove() {
        scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Enter rock, paper, or scissors: ");
            input = scanner.nextLine().trim().toLowerCase();
        } while (!MainApp.movesList.contains(input));

        return input;
    }

    @Override
    public void learnMove(String opponentMove) {
        super.learnMove(opponentMove);
    }

}
