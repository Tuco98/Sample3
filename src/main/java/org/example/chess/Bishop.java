package org.example.chess;

public class Bishop extends Piece{
    public Bishop(String pieceId, Position position, Color color) {
        super(pieceId, position, color);
    }

    @Override
    boolean isValidMove() {
        return false;
    }
}
