package tictactoe;

import tictactoe.difficultyLevel.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Player.NoMoveAvailable {
        System.out.print("Input command: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (Game.correctInput(input) < 0) {
            System.out.println("Bad parameters!");
            System.out.print("Input command: ");
            input = scanner.nextLine();
        }
        if (Game.correctInput(input) == 0) {
            return;
        }
        String[] params = input.split(" ");
        Game game = new Game(params[1], params[2]);
        game.processGame();
    }
}
