import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune {
    private Scanner scanner = new Scanner(System.in);

    @Override
    protected GameRecord play() {
        // Implement user play logic
        System.out.println("Playing Wheel of Fortune as user...");
        int score = 0;  // Placeholder for actual scoring logic
        return new GameRecord(score, "User");
    }

    @Override
    protected boolean playNext() {
        System.out.println("Play another game? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    @Override
    protected char getGuess(String previousGuesses) {
        System.out.println("Enter your guess: ");
        return scanner.nextLine().trim().charAt(0);
    }

    public static void main(String[] args) {
        WheelOfFortuneUserGame game = new WheelOfFortuneUserGame();
        AllGamesRecord record = game.playAll();
        System.out.println(record);
    }
}
