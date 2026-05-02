package org.example.chess;

public class Move {
    Position from;
    Position to;

    Piece movedPiece;
    Piece capturedPiece;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }
}
