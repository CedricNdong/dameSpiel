package damespiel.model;

import lombok.Data;

@Data
public class Dame {
    private final int BOARD_ROWS = 8;
    private final int BOARD_COLUMNS = 8;
    private Cell[][] board;
    private DamePlayer[] damePlayers;
    private DamePlayer currentPlayer;
    private Cell cell;

    public Dame() {
        this.board = new Cell[BOARD_ROWS][BOARD_COLUMNS];
        this.damePlayers = new DamePlayer[2];
         this.damePlayers[0] = new DamePlayer(PieceType.WHITE_PIECE);
         this.damePlayers[1] = new DamePlayer(PieceType.BLACK_PIECE);
        this.currentPlayer = damePlayers[0];
        this.gameInitBoard();
    }


    // play the game
    public boolean isGameOver = true;

    public void play() {
        while (!isGameOver) {
            //play the game here
        }

        // print the winner
    }

    public void gameInitBoard() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                PieceType pieceType;
                if (i < 3 && (i + j) % 2 == 1) {
                    pieceType = PieceType.BLACK_PIECE;
                    cell = new Cell(i, j);
                    cell.setDamePiece(new DamePiece(i, j, pieceType));
                    board[i][j] = cell;
                } else if (i > 4 && (i + j) % 2 == 1) {
                    pieceType = PieceType.WHITE_PIECE;
                    cell = new Cell(i, j);
                    cell.setDamePiece(new DamePiece(i, j, pieceType));
                    board[i][j] = cell;
                    cell = new Cell(i, j);
                } else {
                    cell = new Cell(i, j);
                    pieceType = PieceType.EMPTY;
                    cell.setDamePiece(new DamePiece(i, j, pieceType));
                    board[i][j] = cell;
                }
            }
        }
        printBoard();
    }




    public void printBoard( ) {

        System.out.print("              0     1     2     3     4     5     6     7\n");
        System.out.print("           +-----+-----+-----+-----+-----+-----+-----+-----+\n");
        for (int i = 0; i < BOARD_ROWS; i++) {
            System.out.print("      " + i + "    | ");
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                    System.out.print(" "+ this.board[i][j]+"  | ");
                }
            System.out.println();
            System.out.print("           +-----+-----+-----+-----+-----+-----+-----+-----+\n");
        }



    }
    // je doit avoir 4 parametre From et To
    public void makeMove(int xFrom, int yFrom, int xTo, int yTo) {

        // check the vertical and horizontal move
        if (isMoveVerticalOrHorizontal(xFrom, yFrom, xTo, yTo)){
            System.out.println("Move in this direction is not allow");
        } else if (this.board[xFrom][yFrom].isCellEmpty()) {// you selected an empty cell
            System.out.println("Can not move from an empty cell");
        } else if (!isMoveObliqueValid(xFrom, yFrom, xTo, yTo)) {
            System.out.println("Move is not valid.must select the next cell in the diagonal");
        } else {
            // move the piece


            // if player 1 is on the beat

            //check if the move is valid,the cell to move is empty
            if (this.board[xTo][yTo].isCellEmpty()) {
                this.board[xTo][yTo].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
                this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));
                setPieceToQueen(xTo, yTo);

            }else { // the cell to move is not empty
                if (this.board[xTo][yTo].getDamePiece().getPieceType() == PieceType.BLACK_PIECE) {//if the piece is enemy
                    // check if you can jump over the piece
                    if (this.board[xTo-1][yTo-1].getDamePiece().getPieceType() == PieceType.EMPTY) { // eat the piece on left side
                        this.board[xTo-1][yTo-1].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
                        // delete the piece that was eaten ,and the piece that was moved at his previous position
                        this.board[xTo][yTo].setDamePiece(new DamePiece(xTo, yTo, PieceType.EMPTY));
                        this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));

                        setPieceToQueen(xTo-1, yTo-1);
                        this.damePlayers[0].addScore();
                    } else if (this.board[xTo-1][yTo+1].getDamePiece().getPieceType() == PieceType.EMPTY) {// eat the piece on right side
                        this.board[xTo-1][yTo+1].setDamePiece(this.board[xFrom][yFrom].getDamePiece());

                        // delete the piece that was eaten ,and the piece that was moved at his previous position
                        this.board[xTo][yTo].setDamePiece(new DamePiece(xTo, yTo, PieceType.EMPTY));


                        this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));
                        this.damePlayers[0].addScore();
                    } else {
                        System.out.println("You can't eat there");
                    }
                } else {// if the piece is not enemy
                    System.out.println("(! Impossible eat your own piece).Choose another piece");
                }
            }





        }

        // if piece is on the last row is true then change the piece type to queen

             printBoard();



    }


    // if move is valid

    public boolean isValidateMove(int xFrom, int yFrom, int xTo, int yTo) {
        if (isMoveObliqueValid(xFrom, yFrom, xTo, yTo)) {

        } else if (isMoveVerticalOrHorizontal(xFrom, yFrom, xTo, yTo)) {
            System.out.println("Move in this direction is not allow");
            return false;
        } else {
            return false;
        }


        //check if the move oblique

    return true;

    }





    // if oblique direction is true
    public boolean isMoveObliqueValid(int xFrom, int yFrom, int xTo, int yTo) {
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.WHITE_PIECE) {

            if (xTo == xFrom-1 && (yTo == yFrom-1 || yTo == yFrom+1)) {
                return true;
            } else {
                return false;
            }
        } else if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.BLACK_PIECE) {
            if (xTo == xFrom+1 && (yTo == yFrom-1 || yTo == yFrom+1)) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    public void setPieceToQueen(int x, int y) {
        if (this.board[x][y].getDamePiece().getPieceType() == PieceType.WHITE_PIECE && x == 0) {
            this.board[x][y].setDamePiece(new DamePiece(x, y, PieceType.WHITE_QUEEN));
        } else if (this.board[x][y].getDamePiece().getPieceType() == PieceType.BLACK_PIECE && x == 7) {
            this.board[x][y].setDamePiece(new DamePiece(x, y, PieceType.BLACK_QUEEN));
        }
    }

    // check if the move is vertical or horizontal
    public boolean isMoveVerticalOrHorizontal(int xFrom, int yFrom, int xTo, int yTo) {
        if (xFrom == xTo || yFrom == yTo) {
            return true;
        }
        return false;
    }



    // change the current player
    public void changePlayer() {
        if (this.currentPlayer == damePlayers[0]) {
            this.currentPlayer = damePlayers[1];
        } else {
            this.currentPlayer = damePlayers[0];
        }
    }

    public void undoMove(int xFrom, int yFrom, int xTo, int yTo) {

    // check if the undo is valid



    // the cell to undo is empty



    // the cell to undo is not empty




    // undo the move
    this.board[xFrom][yFrom].setDamePiece(this.board[xTo][yTo].getDamePiece());
    this.board[xTo][yTo].setDamePiece(new DamePiece(xTo, yTo, PieceType.EMPTY));

    printBoard();
}
    // check if cell is empty
    public boolean isEmpty(int x, int y) {
        return this.board[x][y].getDamePiece().getPieceType() == PieceType.EMPTY;
    }

}

