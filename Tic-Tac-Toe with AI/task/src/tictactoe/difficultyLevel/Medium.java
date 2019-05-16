package tictactoe.difficultyLevel;

import tictactoe.Status;

public class Medium extends Player {
    public Medium(char ch) {
        super(ch);
    }


    /*
    True if move was made.
     */
    private boolean thirdInaRow(char[][] field, char chars) {
        for (int i = 0; i < 3; i++) {
            int numOfLetter = 0;
            int posOfSpace = -1;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == chars) {
                    numOfLetter++;
                }
                if (field[i][j] == ' ') {
                    posOfSpace = j;
                }
            }
            if (numOfLetter == 2 && posOfSpace != -1) {
                field[i][posOfSpace] = LETTER;
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            int numOfLetter = 0;
            int posOfSpace = -1;
            for (int j = 0; j < 3; j++) {
                if (field[j][i] == chars) {
                    numOfLetter++;
                }
                if (field[j][i] == ' ') {
                    posOfSpace = j;
                }
            }
            if (numOfLetter == 2 && posOfSpace != -1) {
                field[posOfSpace][i] = LETTER;
                return true;
            }
        }

        {
            int numOfLetter = 0;
            int posOfSpace = -1;
            for (int i = 0; i < 3; i++) {
                if (field[i][i] == chars) {
                    numOfLetter++;
                }
                if (field[i][i] == ' ') {
                    posOfSpace = i;
                }
            }
            if (numOfLetter == 2 && posOfSpace != -1) {
                field[posOfSpace][posOfSpace] = LETTER;
                return true;
            }
        }

        {
            int numOfLetter = 0;
            int posOfSpace = -1;
            for (int i = 0; i < 3; i++) {
                if (field[i][2-i] == chars) {
                    numOfLetter++;
                }
                if (field[i][2-i] == ' ') {
                    posOfSpace = i;
                }
            }
            if (numOfLetter == 2 && posOfSpace != -1) {
                field[posOfSpace][2-posOfSpace] = LETTER;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean makeMove(char[][] field, Status status) throws NoMoveAvailable {
        System.out.print(statusMove());
        if (!thirdInaRow(field, LETTER) && !thirdInaRow(field, LETTER == 'X' ? 'O' : 'X')) {
            return randomMove(field, status);
        }
        System.out.println(status.drawField(field));
        String gameStatus = status.status(field);
        return gameStatus.contains("wins") || gameStatus.contains("Draw");
    }

    @Override
    public String statusMove() {
        return "Making move level \"medium\"\n";
    }
}
