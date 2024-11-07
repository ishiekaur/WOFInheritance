import java.util.ArrayList;
import java.util.List;

public class Bruno implements WheelOfFortunePlayer {
    private List<Character> vowels = new ArrayList<>();
    private List<Character> consonants = new ArrayList<>();
    private int vowelIndex = 0;
    private int consonantIndex = 0;

    public Bruno() {
        // Initialize vowels and consonants
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        // Initialize consonants
        for (char c = 'a'; c <= 'z'; c++) {
            if (!vowels.contains(c)) {
                consonants.add(c);
            }
        }
    }

    @Override
    public char nextGuess() {
        // If there are still vowels left to guess, return the next vowel
        if (vowelIndex < vowels.size()) {
            return vowels.get(vowelIndex++);
        }

        // If all vowels have been guessed, move to consonants
        if (consonantIndex < consonants.size()) {
            return consonants.get(consonantIndex++);
        }

        // If all letters have been guessed (which shouldn't happen in the normal game flow)
        return ' ';
    }

    @Override
    public String playerId() {
        return "Bruno";
    }

    @Override
    public void reset() {
        // Reset indices to start guessing vowels again
        vowelIndex = 0;
        consonantIndex = 0;
    }
}
