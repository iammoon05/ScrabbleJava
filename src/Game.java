import java.util.ArrayList;
import java.util.Map;

public class Game {

    public enum RACK_TYPE {
        SingleRack,
        DualRack
    }

    public enum PLAYER_MODE {
        Single,
        Multi
    }

    Player Player_1;
    Player Player_2;
    Scrabble PlayScrabble;
    ArrayList<String> ValidWords = new ArrayList<>();
    ArrayList<String> UsedWords = new ArrayList<>();
    RACK_TYPE Rack_Type;
    PLAYER_MODE Game_Type = PLAYER_MODE.Single;
    Boolean WORDS_LEFT = true;

    Map<Character, Integer> DistMap;
    Map<Character, Integer> ScoreMap;

    char[] first_rack;
    char[] second_rack;

//    public void SetPlayer1() {
//        return;
//    };

    public Game(String name1, String name2, PLAYER_MODE player_mode, RACK_TYPE rack) {
        this.Game_Type = player_mode;

        this.PlayScrabble = new Scrabble();
        this.DistMap = PlayScrabble.getDistributionMap();
        this.ScoreMap = PlayScrabble.getScoreMap();
        this.first_rack = this.PlayScrabble.createDistributedPlayerRack(7, this.DistMap);
        this.second_rack = this.PlayScrabble.createDistributedPlayerRack(7, this.DistMap);

        this.Rack_Type = rack;
        this.Player_1 = new Player(name1);
        SetPlayer1(this.Player_1, 7, this.DistMap);

        if (this.Game_Type == PLAYER_MODE.Multi) {
            this.Player_2 = new Player(name2);
        }

        if (this.Game_Type == PLAYER_MODE.Single) {
            SetPlayer1(this.Player_1, 7, this.DistMap);
        } else {

            SetPlayerWithRack(this.Player_1, this.Player_2, this.first_rack, this.second_rack, this.Rack_Type);
        }

    }



    public boolean ShouldGameGoOn() {
        return this.WORDS_LEFT;
    }

    public void Get_New_Rack() {
        if (this.Game_Type == PLAYER_MODE.Single) {
            this.Player_1.resetPlayer();
            SetPlayer1(this.Player_1, 7, this.DistMap);
        } else {
            this.Player_1.resetPlayer();
            this.Player_2.resetPlayer();
            SetPlayerWithRack(this.Player_1, this.Player_2, this.first_rack, this.second_rack, this.Rack_Type);
        }
    }

    public void SetPlayer1(Player player1, Integer rackLength, Map<Character, Integer> distMap) {
        char[] s_rack = this.PlayScrabble.createDistributedPlayerRack(rackLength, distMap);
        player1.setPlayerRack(s_rack);
        this.ValidWords = PlayScrabble.findValidWords(s_rack, Scrabble.WordDictionary);
        player1.setValidWords(this.ValidWords);
        this.UsedWords = new ArrayList<>();
    }

    public void SetPlayerWithRack(Player player1, Player player2, char[] first_rack, char[] second_rack, RACK_TYPE rack_type) {

        if (rack_type == Game.RACK_TYPE.SingleRack) {
            this.first_rack = this.PlayScrabble.createDistributedPlayerRack(7, this.DistMap);
            player1.setPlayerRack(first_rack);
            player2.setPlayerRack(first_rack);
            this.ValidWords = PlayScrabble.findValidWords(first_rack, Scrabble.WordDictionary);
            player1.setValidWords(this.ValidWords);
            player2.setValidWords(this.ValidWords);
        } else {
            this.first_rack = this.PlayScrabble.createDistributedPlayerRack(7, this.DistMap);
            this.second_rack = this.PlayScrabble.createDistributedPlayerRack(7, this.DistMap);
            player1.setPlayerRack(first_rack);
            player2.setPlayerRack(second_rack);
            ArrayList<String> ValidWords_1 = PlayScrabble.findValidWords(first_rack, Scrabble.WordDictionary);
            ArrayList<String> ValidWords_2 = PlayScrabble.findValidWords(second_rack, Scrabble.WordDictionary);
            player1.setValidWords(ValidWords_1);
            player2.setValidWords(ValidWords_2);
        }
        this.UsedWords = new ArrayList<>();

    }

    public void Play_Game(String word, Player player) {
        if (this.Game_Type == PLAYER_MODE.Single) {
            if (this.ValidWords.size() == this.UsedWords.size()) {
                this.WORDS_LEFT = false;
            }
            if (this.WORDS_LEFT) {
                this.SingleRackMakeGuess(word, player);
            } else {
                return;
            }
        } else {
            if (this.Rack_Type == RACK_TYPE.SingleRack) {
                SingleRackMakeGuess(word, player);
            } else {
                DualRackMakeGuess(word, player);
            }
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
                System.out.printf("%s >> Correct Guess: %s, with score: %d", player.name, word, score);
                System.out.println();
            } else {
                player.addToTryCountSoFar();
                System.out.printf("%s >> Incorrect Guess! Try again...", player.name);
                System.out.println();
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
                System.out.printf("%s >> Correct Guess: %s, with score: %d", player.name, word, score);
                System.out.println();
            } else {
                player.addToTryCountSoFar();
                System.out.printf("%s >> Incorrect Guess! Try again...", player.name);
                System.out.println();
            }
        }
    }




}
