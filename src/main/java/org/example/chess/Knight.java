package org.example.chess;

public class Knight extends Piece{
    public Knight(String pieceId, Position position, Color color) {
        super(pieceId, position, color);
    }

    @Override
    boolean isValidMove() {
        return false;
    }
}
