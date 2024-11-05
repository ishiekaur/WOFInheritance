import java.util.ArrayList;
import java.util.List;

public class AllGamesRecord {
    private List<GameRecord> gameRecords;

    public AllGamesRecord() {
        this.gameRecords = new ArrayList<>();
    }

    public void add(GameRecord record) {
        gameRecords.add(record);
    }

    public double average() {
        if (gameRecords.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (GameRecord record : gameRecords) {
            sum += record.getScore();
        }
        return sum / gameRecords.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("All Games Record:\n");
        for (GameRecord record : gameRecords) {
            sb.append(record.toString()).append("\n");
        }
        sb.append("Average Score: ").append(average()).append("\n");
        return sb.toString();
    }
}
