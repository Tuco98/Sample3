package org.example.chess;

public class Board {
    String boardId;
    Square[][] grid = new Square[8][8];

    public Board(String boardId) {
        this.boardId = boardId;
        initialize();
    }

    private void initialize() {
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                grid[i][j] = new Square(i,j);
            }
        }

        for(int i=0;i<8;i++){
            grid[1][i].piece = new Pawn("bp"+i,new Position(1,i),Color.WHITE);
            grid[6][i].piece = new Pawn("wp"+i,new Position(6,i),Color.BLACK);

            // similarly rest of the pieces

        }
    }
    
    Square getSquare(Position pos){
        return grid[pos.row][pos.col];
    }
}
