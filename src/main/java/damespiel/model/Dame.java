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

        System.out.print("           +---+---+---+---+---+---+---+---+\n");
        for (int i = 0; i < BOARD_ROWS; i++) {
            System.out.print("  " + i + "        | ");
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                PieceType pieceType;
                if (i < 3 && (i + j) % 2 == 1) {
                    pieceType = PieceType.BLACK_PIECE;
                    cell = new Cell(i, j);
                    cell.setDamePiece(new DamePiece(i, j, pieceType));
                    board[i][j] = cell;
                    System.out.print(""+ board[i][j]+" | ");

                } else if (i > 4 && (i + j) % 2 == 1) {
                    pieceType = PieceType.WHITE_PIECE;
                    cell = new Cell(i, j);
                    cell.setDamePiece(new DamePiece(i, j, pieceType));
                    board[i][j] = cell;
                    cell = new Cell(i, j);
                  System.out.print(""+ board[i][j]+" | ");
                } else {
                    cell = new Cell(i, j);
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

    // je doit avoir 4 parametre From et To
    public Cell[][] updateBoard() {
        board[4][1]= this.board[5][0];

        System.out.println("board[4][1] = " + board[4][1]);
        return board;
    }





}

