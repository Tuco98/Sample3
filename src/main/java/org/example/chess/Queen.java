package org.example.chess;

public class Queen extends Piece{
    public Queen(String pieceId, Position position, Color color) {
        super(pieceId, position, color);
    }

    @Override
    boolean isValidMove() {
        return false;
    }
}
