package practice_lld.tictactoe;

import practice_lld.tictactoe.enums.Symbol;
import practice_lld.tictactoe.model.Player;

public class TicTacToeDemo {

    public static void main(String[] args){
        Player p1 = new Player("guddu", Symbol.O);
        Player p2 = new Player("pinku", Symbol.X);

        TicTacToeSystem system =  new TicTacToeSystem();

        system.createGame(p1, p2);
        system.printBoard();

        system.makeMove(p1, 0,0);
        system.makeMove(p2, 1,0);
        system.makeMove(p1, 0, 1);
        system.makeMove(p2, 1, 1);
        system.makeMove(p1, 0, 2);
        System.out.println("----------------------------------------\n");


        System.out.println("--- GAME 2: p1 (X) vs. p2 (O) ---");
        system.createGame(p1, p2);
        system.printBoard();

        system.makeMove(p1, 0, 0);
        system.makeMove(p2, 1, 0);
        system.makeMove(p1, 0, 1);
        system.makeMove(p2, 1, 1);
        system.makeMove(p1, 2, 2);
        system.makeMove(p2, 1, 2);
        System.out.println("----------------------------------------\n");


        System.out.println("--- GAME 3: p1 (X) vs. p2 (O) - Draw ---");
        system.createGame(p1, p2);
        system.printBoard();

        system.makeMove(p1, 0, 0);
        system.makeMove(p2, 0, 1);
        system.makeMove(p1, 0, 2);
        system.makeMove(p2, 1, 1);
        system.makeMove(p1, 1, 0);
        system.makeMove(p2, 1, 2);
        system.makeMove(p1, 2, 1);
        system.makeMove(p2, 2, 0);
        system.makeMove(p1, 2, 2);
        System.out.println("----------------------------------------\n");


        system.printScoreboard();
    }
}
