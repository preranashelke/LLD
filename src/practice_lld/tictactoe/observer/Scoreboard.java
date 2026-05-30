package practice_lld.tictactoe.observer;

import practice_lld.tictactoe.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Scoreboard implements GameObserver{
    private final Map<String, Integer> scores;

    public Scoreboard(){
        scores = new ConcurrentHashMap<>();
    }
    @Override
    public void update(Game game) {
        if(game.getWinner()!=null){
            String winnerName = game.getWinner().getName();
            scores.put(winnerName, scores.getOrDefault(winnerName, 0 )+1);
            System.out.printf("Scoreboard %s wins! their new scores is %d.%n", winnerName, scores.get(winnerName));
        }
    }

    public void printScores(){
        System.out.println("----- Overall Scoreboard -----");
        if(scores.isEmpty()){
            System.out.println("No games with a winner have been played yet");
            return;
        }
        scores.forEach((playerName, score)-> System.out.printf("Player: %-10s | wins: %d%n", playerName, score));
        System.out.println("------------------------------\n");
    }
}
