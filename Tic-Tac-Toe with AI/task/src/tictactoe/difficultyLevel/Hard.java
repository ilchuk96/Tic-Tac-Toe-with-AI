package tictactoe.difficultyLevel;

import tictactoe.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Hard extends Player {
    public Hard(char ch) {
        super(ch);
    }

    private class FieldScore {
        int I;
        int J;
        int score;

        FieldScore(int i, int j, int s) {
            I = i;
            J = j;
            score = s;
        }
    }

    private FieldScore getScore(char[][] field, Status status, boolean thisPlayerMove) {
        String gameStatus = status.status(field);
        if (gameStatus.contains("Draw")) {
            return new FieldScore(-1, -1, 0);
        }
        if (gameStatus.contains("wins")) {
            return gameStatus
                    .contains(String.valueOf(LETTER)) ? new FieldScore(-1, -1, 1)
                    : new FieldScore(-1, -1, -1);
        }

        char otherPlayer = LETTER == 'X' ? 'O' : 'X';
        List<FieldScore> fieldScores = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    char[][] nextStep = cloneField(field);
                    nextStep[i][j] = thisPlayerMove ? LETTER : otherPlayer;
                    FieldScore tmp = getScore(nextStep, status, !thisPlayerMove);
                    tmp.I = i;
                    tmp.J = j;
                    fieldScores.add(tmp);
                }
            }
        }
        if (thisPlayerMove) {
            return Collections.max(fieldScores, Comparator.comparing((FieldScore fs) -> fs.score));
        }
        return Collections.min(fieldScores, Comparator.comparing((FieldScore fs) -> fs.score));
    }

    private char[][] cloneField(char[][] field) {
        char[][] ans = new char[3][3];
        for (int i = 0; i < 3; i++) {
            ans[i] = field[i].clone();
        }
        return ans;
    }

    @Override
    public boolean makeMove(char[][] field, Status status) throws NoMoveAvailable {
        FieldScore fieldScore = getScore(field, status, true);
        field[fieldScore.I][fieldScore.J] = LETTER;
        System.out.println(status.drawField(field));
        String gameStatus = status.status(field);
        return gameStatus.contains("wins") || gameStatus.contains("Draw");
    }

    @Override
    String statusMove() {
        return "Making move level \"hard\"\n";
    }
}
