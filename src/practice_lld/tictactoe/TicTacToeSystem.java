package practice_lld.tictactoe;

import practice_lld.tictactoe.exceptions.InvalidMoveException;
import practice_lld.tictactoe.model.Player;
import practice_lld.tictactoe.observer.Scoreboard;

public class TicTacToeSystem {
    private static volatile TicTacToeSystem instance;
    private Game game;
    private final Scoreboard scoreboard;

    TicTacToeSystem(){
        this.scoreboard =  new Scoreboard();
    };

    public TicTacToeSystem getInstance(){
        if(instance==null){
            instance =  new TicTacToeSystem();
        }
        return instance;
    }

    public void createGame(Player p1, Player p2){
        this.game = new Game(p1, p2);
        this.game.addObserver(this.scoreboard);

        System.out.printf("Game started between %s (X) and %s (Y).%n", p1.getName(), p2.getName());
    }

    public void makeMove(Player player, int x, int y){
        if(game == null){
            System.out.println("No game in progress, first create game first");
            return;
        }
        try{
            System.out.printf("%s plays at (%d, %d)%n", player.getName(), x, y);
            game.makeMove(x,y,player);
            printBoard();
            System.out.println("Game status: " + game.getGameStatus());
            if(game.getWinner()!=null){
                System.out.println("Winner: " + game.getWinner().getName());
            }

        } catch (InvalidMoveException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void printBoard(){
        game.getBoard().printBoard();
    }

    public void printScoreboard(){
        scoreboard.printScores();
    }
}
