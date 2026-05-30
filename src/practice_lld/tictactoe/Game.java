package practice_lld.tictactoe;

import practice_lld.tictactoe.enums.GameStatus;
import practice_lld.tictactoe.enums.Symbol;
import practice_lld.tictactoe.model.Board;
import practice_lld.tictactoe.model.Player;
import practice_lld.tictactoe.observer.GameSubject;
import practice_lld.tictactoe.strategy.ColumnWinningStrategy;
import practice_lld.tictactoe.strategy.DiagonalWinningStrategy;
import practice_lld.tictactoe.strategy.RowWinningStrategy;
import practice_lld.tictactoe.strategy.WinningStrategy;

import java.util.List;

public class Game extends GameSubject {
    Player player1;
    Player player2;
    Player currentPlayer;
    Player winner;
    GameStatus gameStatus;
    List<WinningStrategy> winningStrategyList;
    Board board;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.winningStrategyList = List.of(new RowWinningStrategy(), new ColumnWinningStrategy(), new DiagonalWinningStrategy());
        this.board = new Board(3);
    }

    void switchPlayer(){
        this.currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
    void makeMove(int x, int y, Player player){
        if(this.currentPlayer != player){
            System.out.println("Not your turn !!");
        }
        this.board.placeSymbol(x, y, player.getSymbol());

        if(checkWinner(player)){
            setWinner(player);
            setGameStatus(player.getSymbol() == Symbol.X ? GameStatus.WINNER_X:GameStatus.WINNER_O);
        } else if (getBoard().isFull()){
            setGameStatus(GameStatus.DRAW);
        } else {
            switchPlayer();
        }

    }
    boolean checkWinner(Player player){
        for(WinningStrategy strategy: winningStrategyList){
            if(strategy.checkWinner(board, player)){
                return true;
            }
        }
        return false;
     }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        if(gameStatus != GameStatus.IN_PROGRESS){
            notifyObservers();
        }
    }


}
