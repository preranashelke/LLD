package practice_lld.tictactoe.model;

import practice_lld.tictactoe.enums.Symbol;

public class Cell {
    Symbol symbol;
     public Cell(){
         this.symbol = Symbol.EMPTY;
     }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
