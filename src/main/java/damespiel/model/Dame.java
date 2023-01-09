package damespiel.model;

import lombok.Data;

@Data
public class Dame {
    private final int BOARD_ROWS = 8;
    private final int BOARD_COLUMNS = 8;
    private Cell[][] board;
    private DamePlayer[] damePlayers;
    private DamePlayer currentPlayer;
    private Cell damePiece;

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

    public  void gameInitBoard() {

        System.out.print("           +---+---+---+---+---+---+---+---+\n");
        for (int i = 0; i < BOARD_ROWS; i++) {
            System.out.print("  "+i+ "        | ");
            for (int j = 0; j < BOARD_COLUMNS; j++) {

                if (i < 3 && (i + j) % 2 == 1) {
                    damePiece = new Cell(i, j);
                    board[i][j] = damePiece;

                    System.out.print("X | ");
                } else if (i > 4 && (i + j) % 2 == 1) {
                    /* damePiece = new DamePiece(i, j, PieceType.BLACK_PIECE);
                    board[i][j] = damePiece.getPieceType().getPieceColor();
                    System.out.print(""+ board[i][j]+" | ");
                     */
                    damePiece = new Cell(i, j);
                    board[i][j] = damePiece;
                    System.out.print("O | ");
                } else {
                    damePiece = new Cell(i, j);
                    damePiece.isCellEmpty();
                    System.out.print("  | ");

                }
            }
            System.out.println();
            System.out.print("           +---+---+---+---+---+---+---+---+\n");
        }
    }

    public void makeMove(Move move) {

    }
    public void undoMove(Move move) {

    }





}

