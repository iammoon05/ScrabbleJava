import java.util.ArrayList;
import java.util.List;

void main() {
    ScrabbleTestSuite Test = new ScrabbleTestSuite();
    Test.TestCalculcateScore();
}

static class ScrabbleTestSuite {
    Scrabble ScrabbleTest = new Scrabble();

    void expectedActualCheckHelperMethod(ArrayList<Boolean> expected, ArrayList<Boolean> actual) {
        if (expected.size() == actual.size()) {
            for (int i = 0; i < expected.size(); i++) {
                String res = expected.get(i) == actual.get(i) ? "Pass" : "Fail";
                System.out.printf("Test Case %d: %s", i, res);
                System.out.println();
            }
        }
    }

    void TestCalculcateScore() {
        IO.println("Testing calculateScore() method");
        boolean pass = false;
        ArrayList<Boolean> expectedResults = new ArrayList<Boolean>(
                List.of(true, true, true, true, false, false)
        );
        ArrayList<Boolean> actualResults = new ArrayList<Boolean>();
        // Correct Answer would be 6 for both Port and Ball
        // We want to use LinkedHashMap since normal HashMap isn't ordered
        // expectedResults is a ordered list of booleans
        Map<String, Integer> Word_Scores= new LinkedHashMap<>();
        Word_Scores.put("Guardian", 10);
        Word_Scores.put("Bat", 5);
        Word_Scores.put("Tab", 5);
        Word_Scores.put("Abracadabra", 18);
        Word_Scores.put("Port", 2);
        Word_Scores.put("Ball", 3);

        for (Map.Entry<String, Integer> entry:  Word_Scores.entrySet()) {
            Integer score = Scrabble.calculateScore(entry.getKey());
            //equals acts as == even though it's supposed to check for object equality
            //its overridden for types such as Integer, String, Date etc
            actualResults.add(entry.getValue().equals(score));
        }
        expectedActualCheckHelperMethod(expectedResults, actualResults);
    }

}
