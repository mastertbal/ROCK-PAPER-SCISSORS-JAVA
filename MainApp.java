import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainApp {

    // List of moves
    public static ArrayList<String> movesList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // add elements to movesList
        movesList.add("rock");
        movesList.add("paper");
        movesList.add("scissors");

        // Create an array list of all computer players and add all
        // computer player objects
        ArrayList<Player> computerPlayersList = new ArrayList<>();
        computerPlayersList.add(new Player());
        computerPlayersList.add(new RandomPlayer());
        computerPlayersList.add(new ReflectPlayer());
        computerPlayersList.add(new CyclePlayer());

        // Create a Scanner object to ask user for the number of rounds to play
        Scanner sc = new Scanner(System.in);

        // Initialize numRounds to 0
        char numOfRounds = '0';

        // Keep asking for a valid number from the user
        // until a valid number is entered
        do {
            System.out.println("Please make sure you enter a valid number!");
            System.out.print("How many rounds do you want to play? ");
            numOfRounds = sc.next().charAt(0);
        } while (!Character.isDigit(numOfRounds));

        // Initialize the Game object, while randomly choosing a computer player to play with
        Game game = new Game(new HumanPlayer(), computerPlayersList.get(new Random().nextInt(4)));
        // Start the game
        game.startGame(Integer.parseInt("" + numOfRounds));
    }
}
