import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

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

    public char[] createDistrbutedPlayerRack(Integer rackLength) {
        char[] distributedBag = createDistributedBag();
        Random random = new Random();
        char[] result = new char[rackLength];
        for (int i = 0; i < rackLength; i++) {
            int randomIndex = random.nextInt(distributedBag.length);
            result[i] = distributedBag[randomIndex];
        }
        return result;
    }

    public static Integer calculateScore(String inputWord) {
        char[] inputCharArray = inputWord.toUpperCase().toCharArray();
        Integer score = 0;
        for (char c : inputCharArray ) {
            score += ScoreMap_Char_to_Score.get(c);
        }
        return score;
    }

    public static ArrayList<String> readDictionaryWords() {
        String filePath = "./src/dictionary.txt";
        ArrayList<String> wordCharList = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                wordCharList.add(scanner.nextLine().toUpperCase());
            }
        } catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }
        return wordCharList;
    }

    public static ArrayList<String> WordDictionary = new ArrayList<String>();
    static {
        WordDictionary = readDictionaryWords();
    }

    public ArrayList<String> findValidWords(char[] playerRack) {
        int[] rackCounts = new int[26];
        //Gets player's rack count of characters
        for (Character c : playerRack) {
            rackCounts[c - 'A']++;
        }

        ArrayList<String> validWords = new ArrayList<String>();

        for(String word : WordDictionary) {
            int[] wordCharCount = new int[26];
            //Gets each words character count
            for (Character c : word.toCharArray()) {
                wordCharCount[c - 'A']++;
            }
            boolean valid = true;
            for (int i = 0; i < 26; i++) {
                if (rackCounts[i] < wordCharCount[i]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                validWords.add(word);
            }
        }
        return validWords;
    }
}
