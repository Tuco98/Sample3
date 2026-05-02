package org.example.chess;

public class Game {
    Board board;
    Player whitePlayer, blackPlayer;
    Player currPlayer;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = new Board("someId");
        currPlayer = whitePlayer;
    }

    boolean makeMove(Move move){

        Square to = board.getSquare(move.to);
        Square from = board.getSquare(move.from);

        if(!from.ifOccupied()) return  false;

        Piece fromPiece = to.piece;

        if(fromPiece.color != currPlayer.color) return false;

        if(!fromPiece.isValidMove()) return  false;

        move.capturedPiece = to.piece;

        to.piece = fromPiece;
        from.piece = null;

        fromPiece.position = move.to;

        switchTurn();

        return true;


    }

    void  switchTurn(){
        currPlayer =  currPlayer.equals(whitePlayer)?blackPlayer:whitePlayer;
    }
}
