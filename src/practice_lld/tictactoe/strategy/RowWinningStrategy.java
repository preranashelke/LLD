package practice_lld.tictactoe.strategy;

import practice_lld.tictactoe.model.Board;
import practice_lld.tictactoe.model.Player;

public class RowWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Player player) {

        for(int row =0;row<board.getSize();row++){
            boolean win = true;
            for(int col =0;col< board.getSize();col++){
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
