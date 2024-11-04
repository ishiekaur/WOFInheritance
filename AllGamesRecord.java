import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllGamesRecord {
    private List<GameRecord> records;

    public AllGamesRecord() {
        this.records = new ArrayList<>();
    }

    public void add(GameRecord record) {
        records.add(record);
    }

    public double average() {
        return records.stream().mapToInt(GameRecord::getScore).average().orElse(0.0);
    }

    public double average(String playerId) {
        return records.stream()
                      .filter(record -> record.getPlayerId().equals(playerId))
                      .mapToInt(GameRecord::getScore)
                      .average()
                      .orElse(0.0);
    }

    public List<GameRecord> highGameList(int n) {
        List<GameRecord> sortedRecords = new ArrayList<>(records);
        sortedRecords.sort(Collections.reverseOrder());
        return sortedRecords.subList(0, Math.min(n, sortedRecords.size()));
    }

    public List<GameRecord> highGameList(String playerId, int n) {
        List<GameRecord> playerRecords = new ArrayList<>();
        for (GameRecord record : records) {
            if (record.getPlayerId().equals(playerId)) {
                playerRecords.add(record);
            }
        }
        playerRecords.sort(Collections.reverseOrder());
        return playerRecords.subList(0, Math.min(n, playerRecords.size()));
    }
}
