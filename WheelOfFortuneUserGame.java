import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune {
    private Scanner scanner = new Scanner(System.in);
    private String phrase;
    private StringBuilder hiddenPhrase;
    private List<Character> guessedLetters = new ArrayList<>();
    private int maxIncorrectGuesses = 5;
    private int remainingGuesses;
    private boolean firstGame = true;

    public WheelOfFortuneUserGame() {
        super();
        resetGame();
    }

    private void resetGame() {
        guessedLetters.clear();
        remainingGuesses = maxIncorrectGuesses;
        phrase = randomPhrase();
        if (phrase != null) {
            hiddenPhrase = new StringBuilder(getHiddenPhrase(phrase, guessedLetters));
        }
    }

    @Override
    protected GameRecord play() {
        System.out.println("Welcome to Wheel of Fortune!");
        System.out.println("Try to guess the phrase: " + hiddenPhrase);

        while (remainingGuesses > 0 && hiddenPhrase.toString().contains("*")) {
            char guess = getGuess(previousGuesses());
            guessedLetters.add(guess);

            if (processGuess(guess, phrase, hiddenPhrase)) {
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

        int score = remainingGuesses * 10;
        return new GameRecord(score, "User");
    }

    private String previousGuesses() {
        StringBuilder previous = new StringBuilder();
        for (char guess : guessedLetters) {
            previous.append(guess);
        }
        return previous.toString();
    }

    @Override
    protected boolean playNext() {
        if (firstGame) {
            firstGame = false;
            return true;
        } else {
            System.out.print("Play another game? (y/n): ");
            boolean playAgain = scanner.nextLine().trim().equalsIgnoreCase("y");
            if (playAgain && !phrases.isEmpty()) {
                resetGame();
            }
            return playAgain && !phrases.isEmpty();
        }
    }

    @Override
    protected char getGuess(String previousGuesses) {
        System.out.print("Enter your guess: ");
        String guessInput = scanner.nextLine().trim().toLowerCase();

        while (guessInput.length() != 1 || !Character.isLetter(guessInput.charAt(0)) || previousGuesses.contains(guessInput)) {
            System.out.print("Invalid input or letter already guessed. Try again: ");
            guessInput = scanner.nextLine().trim().toLowerCase();
        }

        return guessInput.charAt(0);
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
