package practice_lld.tictactoe.model;

import practice_lld.tictactoe.enums.Symbol;
import practice_lld.tictactoe.exceptions.InvalidMoveException;

public class Board {
    Player player;
    int size;
    int totalMove;
    Cell[][] grid;

    public Board(int size){
        this.size=size;
        grid = new Cell[size][size];
        totalMove = 0;
        initializeBoard();
    }

    public void initializeBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                grid[i][j] = new Cell();
            }

        }
    }

    public boolean placeSymbol(int x, int y, Symbol symbol){
        if(x>=size||y>=size||x<0||y<0){
            throw  new InvalidMoveException("Invalid position: out of bounds");
        }

        if(grid[x][y].getSymbol()!=Symbol.EMPTY){
            throw new InvalidMoveException("Invalid position: cell is already occupied");
        }

        grid[x][y].setSymbol(symbol);
        totalMove++;
        return true;
    }

    public Cell getCell(int x, int y){
        if(x>=size||y>=size||x<0||y<0){
            return null;
        }
        return grid[x][y];
    }

    public boolean isFull(){
        return size*size == totalMove;
    }

    public void printBoard(){
        System.out.println("\n-------------");
        for(int i=0;i<size;i++){
            System.out.print("| ");
            for(int j=0;j<size;j++){
                System.out.print(grid[i][j].getSymbol().getChar() + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public int getSize() {
        return size;
    }


}
