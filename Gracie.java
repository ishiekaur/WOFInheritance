import java.util.Random;

public class Gracie implements WheelOfFortunePlayer {
    private Random random = new Random();

    @Override
    public char nextGuess() {
        // Generate a random letter from 'a' to 'z'
        return (char) ('a' + random.nextInt(26));
    }

    @Override
    public String playerId() {
        return "RandomGuesser";
    }

    @Override
    public void reset() {
        // No reset state needed for RandomGuesser
    }
}
