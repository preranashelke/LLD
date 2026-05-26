package practice_lld.tictactoe;

import practice_lld.tictactoe.enums.Symbol;
import practice_lld.tictactoe.model.Board;
import practice_lld.tictactoe.model.Player;

public class TicTacToeDemo {

    public static void main(String[] args){
        Player p1 = new Player("guddu", Symbol.O);
        Player p2 = new Player("pinku", Symbol.X);

        Board b1= new Board(3);
        b1.printBoard();

        //playing game
        boolean win = false;

        while(win==false){

        }
    }
}
