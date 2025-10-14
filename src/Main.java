import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public static void main(String[] args) throws IOException {
    System.out.println("To exit game type: /exit/");
    System.out.println("Tell me if you want to play Single or MultiPlayer: ");
    System.out.println("Enter 1 for single and 2 for multiplayer...");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String userInput = null;

    boolean Valid_Num_of_Player = false;
    boolean EXIT_GAME = false;
    int Num_Of_Player = 0;

    // Forces user to choose between single or multi player
    do {
        try {
            userInput = reader.readLine();
            if (Objects.equals(userInput, "/exit/")) {
                EXIT_GAME = true;
                break;
            }
            Num_Of_Player = Integer.parseInt(userInput);
            Valid_Num_of_Player = true;
        } catch (NumberFormatException e) {
            System.out.print("Please enter 1 for single and 2 for multiplayer");
        }
    } while (!Valid_Num_of_Player && !EXIT_GAME);

    Game.PLAYER_MODE Game_Type = Num_Of_Player == 1 ? Game.PLAYER_MODE.Single : Game.PLAYER_MODE.Single;

    Game NewGame = new Game("Abdullah", "None", Game_Type, Game.RACK_TYPE.SingleRack);

    if (EXIT_GAME) return;

    System.out.print("Player Rack is: ");
    System.out.println(Arrays.toString(NewGame.Player_1.getPlayerRack()));

    do {
        try {
            userInput = reader.readLine();
            if (Objects.equals(userInput, "/exit/")) {
                EXIT_GAME = true;
            }
            NewGame.Play_Game(userInput.toUpperCase(), NewGame.Player_1);

        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    } while (!EXIT_GAME && NewGame.ShouldGameGoOn()) ;

    System.out.println("Words Guess: ");
    System.out.println(Arrays.toString(NewGame.UsedWords.toArray()));
    System.out.println();
    System.out.printf("Total tries made: %d", NewGame.Player_1.getTryCountSoFar());
    System.out.println();
    System.out.printf("Total Score: %d", NewGame.Player_1.getScore());

    reader.close(); // Close the reader when done
}
