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

    private static final char[] AlphabetArray = new char[26];
    static {
        for (int i = 0; i < 26; i++) {
            AlphabetArray[i] = (char) ('A' + i);
        }
    }

    public static char[] PlayerRack = new char[7];

    public static char[] createRandomRack(char[] alphabetArray, Integer rackLength) {
        Random random = new Random();
        char[] result = new char[rackLength];
        for (int i = 0; i < rackLength; i++) {
            int randomIndex = random.nextInt(26);
            result[i] = alphabetArray[randomIndex];
        }
        return result;
    }
    static {
        PlayerRack = createRandomRack(AlphabetArray, 7);
    }

    public char[] createDistributedBag() {
        ArrayList<Character> r = new ArrayList<Character>();
        for (Map.Entry<Character, Integer> entry: ScoreMap_Char_to_Dist.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                r.add(entry.getKey());
            }
        }

        char[] result = new char[r.size()];
        for (int i = 0; i < r.size(); i++) {
            result[i] = r.get(i);
        }

        return result;
    }
}
