import java.util.ArrayList;
import java.util.Map;

public class Game {

    public enum RACK_TYPE {
        SingleRack,
        DualRack
    }

    Player Player_1;
    Player Player_2;
    Scrabble PlayScrabble;
    ArrayList<String> ValidWords;
    ArrayList<String> UsedWords;
    RACK_TYPE Rack_Type;

    Map<Character, Integer> ScoreMap;

    public Game(String name1, String name2, RACK_TYPE rack) {
        this.Player_1 = new Player(name1);
        this.Player_2 = new Player(name2);
        this.PlayScrabble = new Scrabble();
        this.Rack_Type = rack;

        Map<Character, Integer> distMap = PlayScrabble.getDistributionMap();
        this.ScoreMap = PlayScrabble.getScoreMap();

        switch (this.Rack_Type) {
            case DualRack:
                char[] rack1 = this.PlayScrabble.createDistributedPlayerRack(7, distMap);
                char[] rack2 = this.PlayScrabble.createDistributedPlayerRack(7, distMap);
                this.Player_1.setPlayerRack(rack1);
                this.Player_2.setPlayerRack(rack2);
                ArrayList<String> ValidWords_1 = PlayScrabble.findValidWords(rack1, Scrabble.WordDictionary);
                ArrayList<String> ValidWords_2 = PlayScrabble.findValidWords(rack2, Scrabble.WordDictionary);
                this.Player_1.setValidWords(ValidWords_1);
                this.Player_2.setValidWords(ValidWords_2);

            case SingleRack:
                char[] s_rack = this.PlayScrabble.createDistributedPlayerRack(7, distMap);
                this.Player_1.setPlayerRack(s_rack);
                this.Player_2.setPlayerRack(s_rack);
                this.ValidWords =  PlayScrabble.findValidWords(s_rack, Scrabble.WordDictionary);
                this.Player_1.setValidWords(this.ValidWords);
                this.Player_2.setValidWords(this.ValidWords);

        }

    }

    public void SingleRackMakeGuess(String word, Player player) {
        if (this.UsedWords.contains(word)) {
            player.addToTryCountSoFar();
        } else {
            if (this.ValidWords.contains(word)) {
                this.UsedWords.add(word);
                Integer score = Scrabble.calculateScore(word, this.ScoreMap);
                player.addValidTry(word, score);
            } else {
                player.addToTryCountSoFar();
            }
        }

    }
    public void DualRackMakeGuess(String word, Player player) {
        if (this.UsedWords.contains(word)) {
            player.addToTryCountSoFar();
        } else {
            if (player.getValidWords().contains(word)) {
                this.UsedWords.add(word);
                Integer score = Scrabble.calculateScore(word, this.ScoreMap);
                player.addValidTry(word, score);
            } else {
                player.addToTryCountSoFar();
            }
        }
    }




}
