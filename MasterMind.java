import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class MasterMind {
    private final int codeLength = 4; // Length of the code sequence
    private final List<String> colorOptions = Arrays.asList("R", "G", "B", "Y", "O", "P");
    private final List<String> secretCode = new ArrayList<>();
    private final int maxAttempts = 10;

    public MasterMind() {
        generateSecretCode();
    }

    private void generateSecretCode() {
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            secretCode.add(colorOptions.get(random.nextInt(colorOptions.size())));
        }
    }

    protected String getFeedback(List<String> guess) {
        int correctPosition = 0;
        int correctColor = 0;

        // Track which colors have already been matched to avoid double-counting
        boolean[] matchedPositions = new boolean[codeLength];

        // First pass: count correct colors in the correct position
        for (int i = 0; i < codeLength; i++) {
            if (guess.get(i).equals(secretCode.get(i))) {
                correctPosition++;
                matchedPositions[i] = true;
            }
        }

        // Second pass: count correct colors in incorrect positions
        for (int i = 0; i < codeLength; i++) {
            if (!matchedPositions[i] && secretCode.contains(guess.get(i))) {
                int colorIndex = secretCode.indexOf(guess.get(i));
                if (!matchedPositions[colorIndex] && !guess.get(i).equals(secretCode.get(i))) {
                    correctColor++;
                    matchedPositions[colorIndex] = true;
                }
            }
        }

        return "Correct Positions: " + correctPosition + ", Correct Colors (wrong position): " + correctColor;
    }

    protected abstract List<String> getPlayerGuess();

    public void play() {
        System.out.println("Welcome to Mastermind! Try to guess the sequence of colors (R, G, B, Y, O, P).");

        int attempts = 0;
        while (attempts < maxAttempts) {
            List<String> guess = getPlayerGuess();
            String feedback = getFeedback(guess);
            System.out.println(feedback);

            if (feedback.equals("Correct Positions: 4, Correct Colors (wrong position): 0")) {
                System.out.println("Congratulations! You've guessed the correct color sequence.");
                return;
            }
            attempts++;
        }
        System.out.println("Out of attempts! The secret color sequence was: " + secretCode);
    }
}

class MastermindUserGame extends MasterMind {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    protected List<String> getPlayerGuess() {
        System.out.print("Enter your guess (4 colors as letters, e.g., RGRY): ");
        String input = scanner.nextLine().toUpperCase().trim();

        // Validate input format
        while (input.length() != 4 || !input.chars().allMatch(c -> List.of('R', 'G', 'B', 'Y', 'O', 'P').contains((char) c))) {
            System.out.print("Invalid input. Enter 4 colors (R, G, B, Y, O, P): ");
            input = scanner.nextLine().toUpperCase().trim();
        }

        List<String> guess = new ArrayList<>();
        for (char c : input.toCharArray()) {
            guess.add(String.valueOf(c));
        }

        return guess;
    }

    public static void main(String[] args) {
        MastermindUserGame game = new MastermindUserGame();
        game.play();
    }
}
