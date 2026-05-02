package org.example.chess;

public class King extends Piece{
    public King(String pieceId, Position position, Color color) {
        super(pieceId, position, color);
    }

    @Override
    boolean isValidMove() {
        return false;
    }
}
