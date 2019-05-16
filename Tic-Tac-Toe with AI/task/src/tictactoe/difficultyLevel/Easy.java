package tictactoe.difficultyLevel;

import tictactoe.Status;

public class Easy extends Player {
    public Easy(char ch) {
        super(ch);
    }

    @Override
    public boolean makeMove(char[][] field, Status status) throws NoMoveAvailable {
        System.out.print(statusMove());
        return randomMove(field, status);
    }

    @Override
    public String statusMove() {
        return "Making move level \"easy\"\n";
    }
}
