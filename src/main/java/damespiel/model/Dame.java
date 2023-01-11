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
        // this.damePlayers[0] = new DamePlayer(PieceType.WHITE.getPieceType().getPieceColor());
        // this.damePlayers[1] = new DamePlayer(PieceType.BLACK);
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


    public void undoMove(Move move) {

    }

    public void printBoard( ) {

        System.out.print("           +---+---+---+---+---+---+---+---+\n");
        for (int i = 0; i < BOARD_ROWS; i++) {
            System.out.print("  " + i + "        | ");
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                    System.out.print(""+ this.board[i][j]+" | ");
                }
            System.out.println();
            System.out.print("           +---+---+---+---+---+---+---+---+\n");
        }



    }
    // je doit avoir 4 parametre From et To
    public void makeMove(int xFrom, int yFrom, int xTo, int yTo) {

        // move the piece


        // check if the move is valid

        //the move is valid
        if (this.board[xTo][yTo].isCellEmpty()) {
            // update the board
            this.board[xTo][yTo].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
            this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));

        }else { // the move is not valid
            if (this.board[xTo][yTo].getDamePiece().getPieceType() == PieceType.BLACK_PIECE) {//if the piece is enemy
                // check if you can jump over the piece
                if (this.board[xTo-1][yTo-1].getDamePiece().getPieceType() == PieceType.EMPTY
                    || this.board[xTo-1][yTo+1].getDamePiece().getPieceType() == PieceType.EMPTY) {
                    // eat the piece
                    this.board[xTo-1][yTo-1].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
                    this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));
                    this.damePlayers[0].addScore();
                }else {
                    System.out.println("You can't move there");
                }


            } else {// if the piece is not enemy
                System.out.println("choose another piece");
            }
        }




      //  this.board[xTo][yTo].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
      //  this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));
        printBoard();



    }

    // check if cell is empty
    public boolean isEmpty(int x, int y) {
        return this.board[x][y].getDamePiece().getPieceType() == PieceType.EMPTY;
    }

}

