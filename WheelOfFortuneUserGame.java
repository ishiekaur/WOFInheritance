import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune {
    private Scanner scanner = new Scanner(System.in);
    private String phrase;
    private StringBuilder hiddenPhrase;
    private String previousGuesses = "";
    private int maxIncorrectGuesses = 5;
    private int remainingGuesses;
    private boolean firstGame = true;

    // Constructor to initialize game and load phrases from file
    public WheelOfFortuneUserGame() {
        super();
        resetGame(); // Initialize game settings
    }

    // Method to reset the game for a new round
    private void resetGame() {
        previousGuesses = ""; // Clear previous guesses
        remainingGuesses = maxIncorrectGuesses; // Reset guesses

        try {
            List<String> phrases = Files.readAllLines(Paths.get("WOFPhrases.txt"));
            this.phrase = getRandomPhrase(phrases).toLowerCase(); // Get a new random phrase
            this.hiddenPhrase = generateHiddenPhrase(phrase);      // Generate hidden version
        } catch (IOException e) {
            System.err.println("Error reading phrases file: " + e.getMessage());
        }
    }

    // Randomly select a phrase from the list
    private String getRandomPhrase(List<String> phrases) {
        Random random = new Random();
        return phrases.get(random.nextInt(phrases.size()));
    }

    // Generate the hidden phrase (replacing letters with *)
    private StringBuilder generateHiddenPhrase(String phrase) {
        StringBuilder hidden = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            hidden.append(Character.isLetter(c) ? '*' : c);
        }
        return hidden;
    }

    @Override
    protected GameRecord play() {
        System.out.println("Welcome to Wheel of Fortune!");
        System.out.println("Try to guess the phrase: " + hiddenPhrase);

        while (remainingGuesses > 0 && hiddenPhrase.toString().contains("*")) {
            char guess = getGuess(previousGuesses);
            previousGuesses += guess;

            if (processGuess(guess)) {
                System.out.println("Good guess! The phrase now: " + hiddenPhrase);
            } else {
                remainingGuesses--;
                System.out.println("Incorrect guess! Remaining guesses: " + remainingGuesses);
            }
        }

        if (remainingGuesses <= 0) {
            System.out.println("You lose! The phrase was: " + phrase);
        } else {
            System.out.println("Congratulations! You guessed the phrase: " + phrase);
        }

        int score = remainingGuesses * 10; // Example scoring logic based on remaining guesses
        return new GameRecord(score, "User");
    }

    @Override
    protected boolean playNext() {
        if (firstGame) {
            firstGame = false; // Skip prompt for the first game
            return true;
        } else {
            System.out.println("Play another game? (y/n): ");
            boolean playAgain = scanner.nextLine().trim().equalsIgnoreCase("y");
            if (playAgain) {
                resetGame(); // Reset game state for a new round
            }
            return playAgain;
        }
    }

    @Override
    protected char getGuess(String previousGuesses) {
        System.out.println("Enter your guess: ");
        String guessInput = scanner.nextLine().trim().toLowerCase();

        // Validate the input to ensure it’s a single letter
        while (guessInput.length() != 1 || !Character.isLetter(guessInput.charAt(0)) || previousGuesses.contains(guessInput)) {
            System.out.println("Invalid input or letter already guessed. Try again:");
            guessInput = scanner.nextLine().trim().toLowerCase();
        }

        return guessInput.charAt(0);
    }

    // Process the guess by updating the hidden phrase
        protected boolean processGuess(char guess) {
            boolean found = false;
            for (int i = 0; i < phrase.length(); i++) {
                if (phrase.charAt(i) == guess) {
                    hiddenPhrase.setCharAt(i, guess);
                    found = true;
                }
            }
            return found;
        }

    public static void main(String[] args) {
        boolean playAgain;
        do {
            WheelOfFortuneUserGame game = new WheelOfFortuneUserGame();
            AllGamesRecord record = game.playAll();
            System.out.println(record);
            playAgain = game.playNext();
        } while (playAgain);
    }
}
