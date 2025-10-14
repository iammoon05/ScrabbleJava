//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
//    IO.println(String.format("Hello and welcome!"));
//
//    for (int i = 1; i <= 5; i++) {
//        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//        IO.println("i = " + i);
//    }


    Scrabble PlayScrabble = new Scrabble();

//    Integer score = PlayScrabble.calculateScore("GUARDIAN");
//    IO.println(score);
//    IO.println(PlayScrabbble.getPlayerRack());
//    IO.println(PlayScrabble.getDistrbutedPlayerRack());

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
