package tictactoe.difficultyLevel;

import tictactoe.Status;

import java.util.Scanner;

public class User extends Player {
    public User(char ch) {
        super(ch);
    }

    @Override
    public boolean makeMove(char[][] field, Status status) {
        System.out.print(statusMove());
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Status.Answer answer = status.parseInput(input, field, LETTER);
        while (!answer.getaBoolean()) {
            System.out.println(answer.getString());
            input = scanner.nextLine();
            answer = status.parseInput(input, field, LETTER);
        }
        System.out.println(answer.getString());
        String gameStatus = status.status(field);
        return gameStatus.contains("wins") || gameStatus.contains("Draw");
    }

    @Override
    public String statusMove() {
        return "Enter the coordinates: ";
    }
}
