import java.io.*;
import java.time.LocalDate;

public class Game {
    LocalDate now;
    String serializedFileName = "gamehistory";
    File serializedFile;
    ObjectInputStream serialIn;
    ObjectOutputStream serialOut;
    private Player p1, p2;
    private GameHistory storedGameHistory;
    private GameHistory currentGameHistory;

    Game(Player p1, Player p2) throws Exception {
        this.p1 = p1;
        this.p2 = p2;

        storedGameHistory = null;
        currentGameHistory = new GameHistory();
        now = LocalDate.now();

        serialIn = null;
        serialOut = null;
        serializedFile = new File(serializedFileName);

    }

    /* Show the winner after game ends */
    private void displayWinner() {
        System.out.println("*** TOTAL SCORE ***");
        System.out.printf("YOU: %d%nCOMPUTER: %d%n",
                p1.score, p2.score);
        if (p1.score > p2.score) {
            System.out.println("YOU WIN!");
        } else if (p1.score < p2.score) {
            System.out.println("COMPUTER WIN!");
        } else {
            System.out.println("GAME ENDED IN A TIE!");
        }

        // Write game history to file
        writeGameHistory();
    }

    /* Play each round of game */
    private void playRound() {
        // Play your move
        String yourMove = p1.makeMove();
        // Print your move to the console
        System.out.println("You played " + yourMove);
        // Computer to play
        String compMove = p2.makeMove();
        // Computer to learn your move
        p2.learnMove(yourMove);
        // Print computer move
        System.out.println("Computer played " + compMove);

        // Check winner
        checkWinner(yourMove, compMove);
    }

    /* Start game based on number of rounds */
    public void startGame(int numOfRounds) {
        // Print game status
        printStatus("START");
        // Play game based on number of rounds
        for (int i = 0; i < numOfRounds; i++) {
            System.out.println("Round " + (i + 1) + " --:");
            // Call playRound to play each round
            playRound();
        }
        // Print game status
        printStatus("ENDS");
        // Display the winner
        displayWinner();
    }

    /* Print game status */
    private void printStatus(String gameStatus) {
        System.out.println("======================");
        System.out.printf("GAME %s%n", gameStatus);
        System.out.println("========================");
    }

    /* Check and print the winner to the console
     *  at the end of each round
     */
    private void checkWinner(String yourMove, String compMove) {
        if (check(yourMove, compMove)) {
            p1.score++;
            System.out.println("*** YOU WIN ***");
            showCurrentScore();
        } else if (check(compMove, yourMove)) {
            System.out.println("*** COMPUTER WIN ***");
            p2.score++;
            showCurrentScore();
        } else {
            System.out.println("*** TIE ***");
            showCurrentScore();
        }
    }

    // Check if move returns true or false
    private boolean check(String move1, String move2) {
        return move1.equals("rock") && move2.equals("scissors") ||
                move1.equals("scissors") && move2.equals("paper") ||
                move1.equals("paper") && move2.equals("rock");
    }

    /* Show current score */
    private void showCurrentScore() {
        System.out.printf("Your Score: %d, Computer's Score: %d%n",
                p1.score, p2.score);
    }

    // Write game history to memory
    private void writeGameHistory() {
        try (FileWriter fw = new FileWriter("game_history.txt", true);
             PrintWriter pw = new PrintWriter(fw);) {

            // Set current game history, current-date, user-score, and computer-score
            currentGameHistory.setCurrentDate(now);
            currentGameHistory.setUserScore(p1.score);
            currentGameHistory.setComputerScore(p2.score);

            // Check if we don't have a stored game history
            if (serializedFile.length() <= 0) {
                // Create an ObjectOutputStream to serialize current game
                // history object
                serialOut = new ObjectOutputStream(new FileOutputStream(serializedFile));
                // Set the remaining fields of current game history
                currentGameHistory.setMeetingNumber(1);
                if (p1.score > p2.score) {
                    currentGameHistory.setWhoWon("YOU WON");
                } else if (p1.score < p2.score) {
                    currentGameHistory.setWhoWon("COMPUTER WON");
                } else {
                    currentGameHistory.setWhoWon("GAME ENDED IN A DRAW");
                }

                // Serialize current game history object to memory
                serializeGameHistory(currentGameHistory);
                // Write current game history fields to file
                writeGameHistoryToFile(pw, currentGameHistory);
            } else {
                // Read the content of the stored game history object
                serialIn = new ObjectInputStream(new FileInputStream(serializedFile));
                // Initialize the stored game history object
                storedGameHistory = (GameHistory) serialIn.readObject();

                // Set the remaining fields for the current game history object
                int oldMeetingNum = storedGameHistory.getMeetingNumber();
                int newMeetingNum = oldMeetingNum + 1;
                currentGameHistory.setMeetingNumber(newMeetingNum);

                if (p1.score > p2.score) {
                    currentGameHistory.setWhoWon("YOU WON");
                } else if (p1.score < p2.score) {
                    currentGameHistory.setWhoWon("COMPUTER WON");
                } else {
                    currentGameHistory.setWhoWon("GAME ENDED IN A DRAW");
                }

                // Serialize current game history object to memory
                serializeGameHistory(currentGameHistory);
                // Write current game history fields to file
                writeGameHistoryToFile(pw, currentGameHistory);
            }
        } catch (Exception e) {
            System.out.println("Could not get file.\n" + e);
        }
    }

    // Write Game history object to serialized file
    private void serializeGameHistory(GameHistory gameHistory) {
        // Use try-with resources and create an ObjectOutputStream
        // to write the current game history to memory
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(serializedFile))) {
            // Write current game history to memory
            outputStream.writeObject(gameHistory);
        } catch (Exception e) {
            System.out.println("Could not write game history to memory:\n" + e);
        }
    }

    // Write Game History information to file
    private void writeGameHistoryToFile(PrintWriter pw, GameHistory gameHistory) {
        pw.println("GAME HISTORY #" + gameHistory.getMeetingNumber());
        pw.println("DATE: " + gameHistory.getCurrentDate().getYear() + "-" +
                gameHistory.getCurrentDate().getMonth() + "-" +
                gameHistory.getCurrentDate().getDayOfMonth());
        pw.println("USER SCORE: " + gameHistory.getUserScore());
        pw.println("COMPUTER SCORE: " + gameHistory.getComputerScore());
        pw.println(gameHistory.getWhoWon());
        pw.println();
    }
}
