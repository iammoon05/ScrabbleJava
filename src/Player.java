import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Player {
    private String name;
    private Integer score;
    private Integer tries;
    private final Map<String, Integer> highestScoringWordMap = new HashMap<>();
    private ArrayList<String> guessedWords= new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public Integer getHighestScoringScore() {
        Optional<Integer> firstValue = this.highestScoringWordMap.values().stream().findFirst();
        return firstValue.orElse(0);
    }
    public String getHighestScoringWord() {
        Optional<String> firstKey = this.highestScoringWordMap.keySet().stream().findFirst();
        return firstKey.orElse("None");
    }
    public Integer getTriesSoFar() {
        return this.tries;
    }
    public Integer getScore() {
        return this.score;
    }
    public void setGuessedWords(String word) {
        this.guessedWords.add(word);
    }
    public ArrayList<String> getGuessedWords() {
        return this.guessedWords;
    }

}
