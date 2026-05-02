package org.example.chess;

public abstract class Piece {
    String pieceId;
    Position position;
    Color color;

    public Piece(String pieceId, Position position, Color color) {
        this.pieceId = pieceId;
        this.position = position;
        this.color = color;
    }

    abstract boolean  isValidMove();
}
