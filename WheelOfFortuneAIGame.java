import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WheelOfFortuneAIGame extends WheelOfFortune {
    private List<WheelOfFortunePlayer> players;
    private List<String> phrases;

    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> players) {
        this.players = players;
        this.phrases = loadPhrases("WOFPhrases.txt");  // Load phrases from file
    }

    // Load phrases from file into a list
    private List<String> loadPhrases(String fileName) {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                phrases.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    @Override
    protected GameRecord play() {
        return null;  // Placeholder: not directly called
    }

    protected GameRecord play(WheelOfFortunePlayer player, String phrase) {
        System.out.println("Playing Wheel of Fortune with AI player: " + player.playerId() + " on phrase: " + phrase);
        int score = (int) (Math.random() * 100);  // Placeholder for AI scoring logic
        return new GameRecord(score, player.playerId());
    }

    @Override
    protected boolean playNext() {
        return false;  // Placeholder logic
    }

    @Override
    protected char getGuess(String previousGuesses) {
        return 'e';  // Example guess
    }

    // Play games for all players across all phrases
    public AllGamesRecord playAll() {
        AllGamesRecord allRecords = new AllGamesRecord();
        for (WheelOfFortunePlayer player : players) {
            for (String phrase : phrases) {
                GameRecord record = play(player, phrase);  // Play each phrase for each player
                allRecords.add(record);
            }
        }
        return allRecords;
    }

    public static void main(String[] args) {
        List<WheelOfFortunePlayer> aiPlayers = List.of(new Gracie(), new Hozier(), new Bruno());
        WheelOfFortuneAIGame game = new WheelOfFortuneAIGame(aiPlayers);
        AllGamesRecord record = game.playAll();
        System.out.println(record);
    }
}
