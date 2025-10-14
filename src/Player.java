import java.util.*;

public class Player {
    public String name;
    private Integer score = 0;
    private Integer tryCount = 0;
    private final LinkedHashMap<String, Integer> highestScoringWordMap = new LinkedHashMap<>();
    private ArrayList<String> validWords= new ArrayList<>();
    private ArrayList<String> guessedWords= new ArrayList<>();
    private char[] playerRack;

    public Player(String name) {
        this.name = name;
    }

    public Integer getHighestScoringScore() {
        Optional<Integer> firstValue = this.highestScoringWordMap.values().stream().findFirst();
        return firstValue.orElse(0);
    }
    public String getHighestScoringWord() {
        Optional<String> firstKey = this.highestScoringWordMap.keySet().stream().findFirst();
        return firstKey.orElse("None");
    }
    public Integer getTryCountSoFar() {
        return this.tryCount;
    }
    public void addToTryCountSoFar() {
        this.tryCount++;
    }
    public Integer getScore() {
        return this.score;
    }
    public void setScore(Integer score) {
        this.score += score;
    }
    public void setGuessedWords(String word) {
        this.guessedWords.add(word);
    }
    public ArrayList<String> getGuessedWords() {
        return this.guessedWords;
    }
    public void setValidWords(ArrayList<String> words) {
        this.validWords = words;
    }
    public ArrayList<String> getValidWords() {
        return this.validWords;
    }
    public void setPlayerRack(char[] rackCharArray) {
        this.playerRack = rackCharArray;
    }
    public char[] getPlayerRack() {
        return this.playerRack;
    }
    public void addValidTry(String word, Integer score) {
        this.addToTryCountSoFar();
        if (this.highestScoringWordMap.isEmpty()) {
            this.highestScoringWordMap.put(word, score);
        } else {
            this.highestScoringWordMap.putFirst(word, score);
            this.setGuessedWords(word);
        }
        this.setScore(score);
    }

}
