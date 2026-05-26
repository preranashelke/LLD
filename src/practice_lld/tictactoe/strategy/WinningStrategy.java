package practice_lld.tictactoe.strategy;

import practice_lld.tictactoe.model.Board;
import practice_lld.tictactoe.model.Player;

public interface WinningStrategy {

    boolean checkWinner(Board board, Player player);
}
