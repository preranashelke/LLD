package practice_lld.tictactoe.model;

import practice_lld.tictactoe.enums.Symbol;

public class Player {
    String name;
    Symbol symbol;

    public Player(String name, Symbol symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
