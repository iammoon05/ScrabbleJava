import java.util.*;

public class Scrabble {

    private static final Map<Integer, char[]> ScoreMap = Map.of(
            1, new char[]{'E', 'A', 'I', 'O', 'N', 'R', 'T', 'L', 'S', 'U'},
            2, new char[]{'D', 'G'},
            3, new char[]{'B', 'C', 'M', 'P'},
            4, new char[]{'F', 'H', 'V', 'W', 'Y'},
            5, new char[]{'K'},
            8, new char[]{'J', 'X'},
            10, new char[]{'Q', 'Z'}
    );

    private static final Map<Character, Integer> ScoreMap_Char_to_Score;

    static {
        Map<Character, Integer> tempScoreMap = new HashMap<>();
        for (Map.Entry<Integer, char[]> entry: ScoreMap.entrySet()) {
            for (Character e : entry.getValue()) {
                tempScoreMap.put(e, entry.getKey());
            }
        }
        ScoreMap_Char_to_Score = Collections.unmodifiableMap(tempScoreMap);
    }

    private static final Map<Integer, char[]> DistributionMap= Map.of(
            12, new char[]{'E'},
            9, new char[]{'A', 'I'},
            8, new char[]{'O'},
            6, new char[]{'N', 'R', 'T'},
            4, new char[]{'L', 'S', 'U', 'D'},
            3, new char[]{'G'},
            2, new char[]{'B', 'C', 'M', 'P', 'F', 'H', 'V', 'W', 'Y'},
            1, new char[]{'K', 'J', 'X', 'Q', 'Z'}
    );

    private static final Map<Character, Integer> ScoreMap_Char_to_Dist;

    static {
        Map<Character, Integer> tempDistributionMap = new HashMap<>();
        for (Map.Entry<Integer, char[]> entry: DistributionMap.entrySet()) {
            for (Character e : entry.getValue()) {
                tempDistributionMap.put(e, entry.getKey());
            }
        }
        ScoreMap_Char_to_Dist = Collections.unmodifiableMap(tempDistributionMap);
    }
}
