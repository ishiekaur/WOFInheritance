public class Hozier implements WheelOfFortunePlayer {
    private char currentGuess = 'a'; // Start guessing from 'a'

    @Override
    public char nextGuess() {
        // If we have reached 'z', reset to 'a'
        if (currentGuess > 'z') {
            currentGuess = 'a';
        }
        
        // Return the current letter and then increment it for the next guess
        return currentGuess++;
    }

    @Override
    public String playerId() {
        return "Hozier";
    }

    @Override
    public void reset() {
        // Reset the guesser back to 'a' when reset is called
        currentGuess = 'a';
    }
}
