import java.util.List;
import java.util.Random;

public abstract class WheelOfFortune extends Game {
    protected List<String> phrases;
    private Random random = new Random();

    protected void readPhrases() {
        // Read phrases from a file or other source
    }

    protected String randomPhrase() {
        return phrases.get(random.nextInt(phrases.size()));
    }

    protected String getHiddenPhrase(String phrase, List<Character> guesses) {
        // Return the phrase with guessed letters revealed and others hidden
        return "";  // Placeholder
    }

    protected boolean processGuess(char guess) {
        // Process the guessed character
        return false;  // Placeholder
    }

    protected abstract char getGuess(String previousGuesses);
}
