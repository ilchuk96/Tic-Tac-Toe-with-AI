package tictactoe.difficultyLevel;

import tictactoe.Status;

import java.util.Random;

public abstract class Player {
    protected final char LETTER;

    Player(char letter) {
        LETTER = letter;
    }

    public class NoMoveAvailable extends Exception {}

    public static boolean isLevel(String string) {
        return string.equals("user") || string.equals("easy") || string.equals("medium") || string.equals("hard");
    }

    boolean randomMove(char[][] field, Status status) throws NoMoveAvailable {
        int numberOfMoves = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    numberOfMoves++;
                }
            }
        }
        if (numberOfMoves == 0) {
            throw new NoMoveAvailable();
        }

        Random random = new Random();
        int move = random.nextInt(numberOfMoves);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    if (move == 0) {
                        field[i][j] = LETTER;
                        System.out.println(status.drawField(field));
                        String gameStatus = status.status(field);
                        return gameStatus.contains("wins") || gameStatus.contains("Draw");
                    }
                    move--;
                }
            }
        }
        throw new NoMoveAvailable();
    }

    /*
    True if game is finished.
     */
    public abstract boolean makeMove(char[][] field, Status status) throws NoMoveAvailable;

    abstract String statusMove();
}
