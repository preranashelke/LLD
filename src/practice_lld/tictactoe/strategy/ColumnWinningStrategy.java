package practice_lld.tictactoe.strategy;

import practice_lld.tictactoe.model.Board;
import practice_lld.tictactoe.model.Player;

public class ColumnWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Player player) {

        for(int col =0;col<board.getSize();col++){
            boolean win = true;
            for(int row =0;row< board.getSize();row++){
                if(board.getCell(row,col).getSymbol()!=player.getSymbol()){
                    win = false;
                    break;
                }
            }
            if(win){
                return true;
            }
        }
        return false;
    }
}
