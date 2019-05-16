package tictactoe;

import tictactoe.difficultyLevel.*;


class Game {
    private Player playerX;
    private Player playerO;

    static int correctInput(String string) {
        String[] strings = string.split(" ");
        if (strings.length < 1) {
            return -1;
        }
        if (strings[0].equals("exit")) {
            return 0;
        }
        if (strings[0].equals("start")) {
            if (strings.length < 3) {
                return -1;
            } else {
                return Player.isLevel(strings[1]) && Player.isLevel(strings[2]) ? 1 : -1;
            }
        }
        return -1;
    }

    Game(String X, String O) {
        switch (X) {
            case "user": playerX = new User('X'); break;
            case "easy": playerX = new Easy('X'); break;
            case "medium": playerX = new Medium('X'); break;
            case "hard": playerX = new Hard('X'); break;
        }

        switch (O) {
            case "user": playerO = new User('O'); break;
            case "easy": playerO = new Easy('O'); break;
            case "medium": playerO = new Medium('O'); break;
            case "hard": playerO = new Hard('O'); break;
        }
    }

    void processGame() throws Player.NoMoveAvailable {
        Status status = new Status();
        char[][] field = status.toMatrix("         ");
        System.out.println(status.drawField(field));
        while (true) {
            if (playerX.makeMove(field, status)) {
                break;
            }
            if (playerO.makeMove(field, status)) {
                break;
            }
        }
        System.out.println(status.status(field));
    }
}
