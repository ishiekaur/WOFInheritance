import java.util.List;

// Superclass for Guessing Games
public abstract class GuessingGame {
    protected int maxAttempts;
    protected int attempts;
    protected boolean gameWon;

    public GuessingGame(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.gameWon = false;
    }

    protected abstract boolean isCorrectGuess(List<String> guess);
    protected abstract String getFeedback(List<String> guess);
    protected abstract List<String> getPlayerGuess();

    // General play loop
    public void play() {
        System.out.println("Welcome to the Game! Try to guess the sequence.");

        while (attempts < maxAttempts && !gameWon) {
            List<String> guess = getPlayerGuess();
            gameWon = isCorrectGuess(guess);

            System.out.println(getFeedback(guess));
            attempts++;
        }
        
        if (gameWon) {
            System.out.println("Congratulations! You've won the game.");
        } else {
            System.out.println("Out of attempts! Better luck next time.");
        }
    }
}