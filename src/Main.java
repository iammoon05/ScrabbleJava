import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

String EXIT_Command = ":exit";
String NEW_RACK_Command = ":nr";
String SHOW_SCORE_Command = ":score";
String HELP_Command = ":h";
boolean EXIT_GAME = false;
boolean Player1_Turn = true;
int Num_Of_Player = 0;

void main() throws IOException {
    System.out.println("To exit game type: :exit");
    System.out.println("For a new rack: :nr");
    System.out.println("Tell me if you want to play Single or MultiPlayer: ");
    System.out.println("Enter 1 for single and 2 for multiplayer...");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String userInput;


    boolean Valid_Num_of_Player = false;

    // Forces user to choose between single or multi-player
    do {
        try {
            userInput = reader.readLine();
            if (Objects.equals(userInput, EXIT_Command)) {
                EXIT_GAME = true;
            } else if (userInput != null) {
                Num_Of_Player = Integer.parseInt(userInput);
                Valid_Num_of_Player = true;
            }
        } catch (NumberFormatException e) {
            System.out.print("Please enter 1 for single and 2 for multiplayer");
        }
    } while (!Valid_Num_of_Player && !EXIT_GAME);

    Game.PLAYER_MODE Game_Type = Num_Of_Player == 1 ? Game.PLAYER_MODE.Single : Game.PLAYER_MODE.Multi;

    Game NewGame = new Game("Player 1", "Player 2", Game_Type, Game.RACK_TYPE.SingleRack);

    if (EXIT_GAME) return;

    if (Game_Type == Game.PLAYER_MODE.Single) {
        printPlayerRack(NewGame, 1);
    } else {
        printPlayerRack(NewGame, 2);
    }

    do {
        try {
            userInput = reader.readLine();
            if (Num_Of_Player == 1 && userInput != null) {
                SinglePlayerPath(userInput, NewGame);
            } else if (Num_Of_Player == 2 && userInput != null) {
                MultiPlayerPath(userInput, NewGame);
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    } while (!EXIT_GAME && NewGame.ShouldGameGoOn()) ;

    // prints after exit game
    exitCalled(NewGame);

    reader.close(); // Close the reader when done
}

public void SinglePlayerPath(String input, Game game) {
    if (Objects.equals(input, EXIT_Command)) {
        EXIT_GAME = true;
        return;
    } else if (Objects.equals(input, NEW_RACK_Command)) {
        game.Get_New_Rack();
        printPlayerRack(game, 1);
        return;
    } else if (Objects.equals(input, SHOW_SCORE_Command)) {
        printPlayerScore(game, 1);
        return;
    } else if (Objects.equals(input, HELP_Command)) {
        printHelp();
        return;
    } else if (game.UsedWords.size() == game.ValidWords.size()) {
        EXIT_GAME = true;
        return;
    } else {
        game.Play_Game(input.toUpperCase(), game.Player_1);
    }
    return;
}

public void MultiPlayerPath(String input, Game game) {
    if (Objects.equals(input, EXIT_Command)) {
        EXIT_GAME = true;
        return;
    } else if (Objects.equals(input, NEW_RACK_Command)) {
        game.Get_New_Rack();
        printPlayerRack(game, 2);
        return;
    } else if (Objects.equals(input, SHOW_SCORE_Command)) {
        printPlayerScore(game, 2);
        return;
    } else if (Objects.equals(input, HELP_Command)) {
        printHelp();
        return;
    } else {
        if (game.Rack_Type == Game.RACK_TYPE.SingleRack) {
            if (game.UsedWords.size() == game.ValidWords.size()) {
                EXIT_GAME = true;
                return;
            } else {
                if (Player1_Turn) {
                    game.Play_Game(input.toUpperCase(), game.Player_1);
                } else {
                    game.Play_Game(input.toUpperCase(), game.Player_2);
                }
                Player1_Turn = !Player1_Turn;
                return;
            }
        }
    }
    return;
}

public void exitCalled(Game game) {
    System.out.println("Words Guess: ");
    System.out.println(Arrays.toString(game.UsedWords.toArray()));
    System.out.println();
    printSummary(game, Num_Of_Player);
}


public void printSummary(Game game, Integer playerNum) {
    if (playerNum == 1) {
        System.out.println("Words Guess: ");
        System.out.println(Arrays.toString(game.UsedWords.toArray()));
        System.out.println();
        System.out.printf("%s Total tries made: %d", game.Player_1.name, game.Player_1.getTryCountSoFar());
        System.out.println();
        System.out.printf("%s >> Total Score: %d", game.Player_1.name, game.Player_1.getScore());
        System.out.println();
    } else {
        System.out.println("Words Guess: ");
        System.out.println(Arrays.toString(game.UsedWords.toArray()));
        System.out.println();
        System.out.printf("%s Total tries made: %d", game.Player_1.name, game.Player_1.getTryCountSoFar());
        System.out.println();
        System.out.printf("%s >> Total Score: %d", game.Player_1.name, game.Player_1.getScore());
        System.out.println();
        System.out.printf("%s Total tries made: %d", game.Player_2.name, game.Player_2.getTryCountSoFar());
        System.out.println();
        System.out.printf("%s >> Total Score: %d", game.Player_2.name, game.Player_2.getScore());
        System.out.println();
    }
}

public void printPlayerScore(Game game, Integer playerNum) {
    if (playerNum == 1) {
        System.out.printf("%s >> Score so far: %d", game.Player_1.name, game.Player_1.getScore());
        System.out.println();
    } else {
        System.out.printf("%s >> Score so far: %d", game.Player_2.name, game.Player_1.getScore());
        System.out.println();
        System.out.printf("%s >> Score so far: %d", game.Player_2.name, game.Player_2.getScore());
        System.out.println();
    }
}
public void printPlayerRack(Game game, Integer playerNum) {
    if (playerNum == 1) {
        System.out.printf("%s Rack is: ", game.Player_1.name);
        System.out.println(Arrays.toString(game.Player_1.getPlayerRack()));
    } else {
        System.out.printf("%s Rack is: ", game.Player_1.name);
        System.out.println(Arrays.toString(game.Player_1.getPlayerRack()));
        System.out.printf("%s Rack is: ", game.Player_2.name);
        System.out.println(Arrays.toString(game.Player_2.getPlayerRack()));
    }
}
public void printHelp() {

    System.out.println("Nothing to print so far");

}
