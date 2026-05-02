package org.example.chess;

public class Pawn extends Piece{

    public Pawn(String pieceId, Position position, Color color) {
        super(pieceId, position, color);
    }

    @Override
    boolean isValidMove() {
        return false;
    }
}
