package practice_lld.tictactoe.strategy;

import practice_lld.tictactoe.model.Board;
import practice_lld.tictactoe.model.Player;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Player player) {

        boolean mainDia = true;
        for (int row = 0; row < board.getSize(); row++) {
            if (board.getCell(row, row).getSymbol() != player.getSymbol()) {
                mainDia = false;
                break;
            }
        }
        if (mainDia) {
            return true;
        }
        boolean antiDia = true;
        for (int row = 0; row < board.getSize(); row++) {
            if (board.getCell(row, board.getSize() -1 - row).getSymbol() != player.getSymbol()) {
                antiDia = false;
                break;
            }
        }
        if (antiDia) {
            return true;
        }

        return false;
    }
}
