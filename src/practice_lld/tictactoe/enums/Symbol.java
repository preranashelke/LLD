package practice_lld.tictactoe.enums;

public enum Symbol {
    X('X'),
    O('O'),
    EMPTY('_');

    private final char symbol;

    Symbol(char x) {
        this.symbol = x;
    }
    public char getChar(){
        return symbol;
    }
}
