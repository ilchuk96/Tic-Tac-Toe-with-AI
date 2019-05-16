package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Status {
    public String drawField(char[][] str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------\n");
        for (int i = 0; i < 3; i++) {
            stringBuilder.append("| ");
            for (int j = 0; j < 3; j++) {
                stringBuilder.append(str[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append("|\n");
        }
        stringBuilder.append("---------");
        return stringBuilder.toString();
    }

    char[][] toMatrix(String str) {
        char[][] ans = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char ch = str.charAt(3*i+j);
                ans[i][j] = ch;
            }
        }
        return ans;
    }

    public String status(char[][] matrix) {
        Map<Character, Integer> num = new HashMap<>();
        /*
        Check if drawField has a lot more X's that O's or vice versa.
         */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int count = num.getOrDefault(matrix[i][j], 0);
                num.put(matrix[i][j], count + 1);
            }
        }
        if (Math.abs(num.getOrDefault('X', 0) - num.getOrDefault('O', 0)) > 1) {
            return "Impossible";
        }

        Map<Character, Boolean> winner = new HashMap<>();
        winner.put('X', false);
        winner.put('O', false);
        /*
        Check who has 3 i a row.
         */
        for (int i = 0; i < 3; i++) {
            char first = matrix[i][0];
            if (matrix[i][1] == first && matrix[i][2] == first) {
                winner.put(first, true);
            }
        }
        for (int j = 0; j < 3; j++) {
            char first = matrix[0][j];
            if (matrix[1][j] == first && matrix[2][j] == first) {
                winner.put(first, true);
            }
        }
        {
            char first = matrix[0][0];
            if (matrix[1][1] == first && matrix[2][2] == first) {
                winner.put(first, true);
            }
        }
        {
            char first = matrix[0][2];
            if (matrix[1][1] == first && matrix[2][0] == first) {
                winner.put(first, true);
            }
        }

        if (winner.get('X') && winner.get('O')) {
            return "Impossible";
        }
        if (winner.get('X')) {
            return "X wins";
        }
        if (winner.get('O')) {
            return "O wins";
        }
        if (num.getOrDefault(' ', 0).equals(0)) {
            return "Draw";
        }
        return "Game not finished";
    }

    public class Answer {
        private Boolean aBoolean;
        private String string;

        Answer(boolean b, String s) {
            aBoolean = b;
            string = s;
        }

        public Boolean getaBoolean() {
            return aBoolean;
        }

        public String getString() {
            return string;
        }
    }

    public Answer parseInput(String input, char[][] field, char ch) {
        StringTokenizer stringTokenizer = new StringTokenizer(input);
        int x;
        int y;
        try {
            x = Integer.parseInt(stringTokenizer.nextToken());
            y = Integer.parseInt(stringTokenizer.nextToken());
        } catch (NumberFormatException | NoSuchElementException e) {
            return new Answer(false, "You should enter numbers!");
        }

        if (x < 1 || x > 3 || y < 1 || y > 3) {
            return new Answer(false, "Coordinates should be from 1 to 3!");
        }

        if (field[3-y][x-1] != ' ') {
            return new Answer(false, "This cell is occupied! Choose another one!");
        }

        field[3-y][x-1] = ch;
        return new Answer(true, drawField(field));
    }
}
