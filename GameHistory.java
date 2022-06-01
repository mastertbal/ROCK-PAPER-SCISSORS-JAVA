import java.io.Serializable;
import java.time.LocalDate;

public class GameHistory implements Serializable {
    private int meetingNumber;
    private LocalDate currentDate;
    private int userScore;
    private int computerScore;
    private String whoWon;

    public GameHistory(){}

    public GameHistory(int meetingNumber, LocalDate currentDate, int userScore,
                       int computerScore, String whoWon) {
        this.meetingNumber = meetingNumber;
        this.currentDate = currentDate;
        this.userScore = userScore;
        this.computerScore = computerScore;
        this.whoWon = whoWon;
    }

    public int getMeetingNumber() {
        return meetingNumber;
    }

    // Get methods
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public int getUserScore() {
        return userScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public String getWhoWon() {
        return whoWon;
    }

    public void setMeetingNumber(int meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

    // Set methods
    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public void setWhoWon(String whoWon) {
        this.whoWon = whoWon;
    }
}
