void main() {

    Scrabble PlayScrabble = new Scrabble();

    Map<Character, Integer> distMap = PlayScrabble.getDistributionMap();
    Map<Character, Integer> scoreMap = PlayScrabble.getScoreMap();

    char[] distributedPlayerRack = PlayScrabble.createDistributedPlayerRack(7, distMap);

    IO.println(Arrays.toString(distributedPlayerRack));

    ArrayList<String> result = PlayScrabble.findValidWords(distributedPlayerRack, Scrabble.WordDictionary);
    IO.println(result);

    IO.println(PlayScrabble.longestValidWord(result));
    IO.println(PlayScrabble.highestScoringWord(result, scoreMap));
    IO.println(PlayScrabble.highestScoringWordWithTripleLetter(result, scoreMap));
}
