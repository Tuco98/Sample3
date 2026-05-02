package org.example.chess;

public class Square {
    Position pos;
    Piece piece;

    public Square(int row, int col) {
        this.pos = new Position(row,col);
    }

    boolean isWhite(){
        return (pos.row+pos.col)%2 == 0;
    }

    boolean ifOccupied(){
        return piece !=null;
    }
}
