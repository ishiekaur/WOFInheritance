import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WheelOfFortune extends Game {
    protected List<String> phrases;
    private Random random = new Random();

    public WheelOfFortune() {
        phrases = loadPhrases("WOFPhrases.txt");
    }

    private List<String> loadPhrases(String fileName) {
        List<String> phrases = new ArrayList<>();
        try {
            phrases = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Error reading phrases file: " + e.getMessage());
        }
        return phrases;
    }

    protected String randomPhrase() {
        if (phrases.isEmpty()) {
            System.out.println("No more phrases available.");
            return null;
        }
        return phrases.remove(random.nextInt(phrases.size())).toLowerCase();
    }

    protected String getHiddenPhrase(String phrase, List<Character> guesses) {
        StringBuilder hidden = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            // Only hide alphabetic characters not yet guessed
            if (Character.isLetter(c) && !guesses.contains(c)) {
                hidden.append('*');
            } else {
                hidden.append(c); // Leave spaces and punctuation as they are
            }
        }
        return hidden.toString();
    }

    protected boolean processGuess(char guess, String phrase, StringBuilder hiddenPhrase) {
        boolean found = false;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == guess) {
                hiddenPhrase.setCharAt(i, guess);
                found = true;
            }
        }
        return found;
    }

    protected abstract char getGuess(String previousGuesses);
}
