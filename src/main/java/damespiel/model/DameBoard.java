package damespiel.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DameBoard {

    private  final int BOARD_COLUMNS = 8;
    private  final int BOARD_ROWS = 8;

    private  final int PLAYER_PIECES = 12;



    private char[][]  board;
    // private DamePiece[][]  board;

    public DameBoard() {
        //board = new DamePiece[BOARD_ROWS][BOARD_COLUMNS];

        board = new char[BOARD_ROWS][BOARD_COLUMNS];
    }

    public  void gameInitBoard() {

        System.out.print("           +---+---+---+---+---+---+---+---+\n");
        for (int i = 0; i < BOARD_ROWS; i++) {
            System.out.print("  "+i+ "        | ");
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if (i < 3 && (i + j) % 2 == 1) {
                    DamePiece piece = new DamePiece(i, j, PieceType.WHITE_PIECE);
                    board[i][j] = piece.getPieceType().getPieceColor();
                    System.out.print(""+ board[i][j]+" | ");
                } else if (i > 4 && (i + j) % 2 == 1) {
                    DamePiece piece = new DamePiece(i, j, PieceType.BLACK_PIECE);
                    board[i][j] = piece.getPieceType().getPieceColor();
                   // board[i][j] = new DamePiece(i, j, PieceType.BLACK_PIECE);;
                    System.out.print(""+ board[i][j]+" | ");
                } else {
                    System.out.print("  | ");

                    //System.out.print(""+ board[i][j]+" | ");
                }
            }
            System.out.println();
            System.out.print("           +---+---+---+---+---+---+---+---+\n");
        }



















       /* System.out.print("           +---+---+---+---+---+---+---+---+\n");
        for (int i = 0; i < BOARD_ROWS; i++) {
            System.out.print("  "+i+ "        | ");
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if (i < 3 && (i + j) % 2 == 1) {
                    board[i][j] = new DamePiece(i, j, PieceType.WHITE_PIECE);
                    System.out.print(""+ board[i][j]+" | ");
                } else if (i > 4 && (i + j) % 2 == 1) {
                    board[i][j] = new DamePiece(i, j, PieceType.BLACK_PIECE);;
                    System.out.print(""+ board[i][j]+" | ");
                } else {
                    System.out.println("  | ");

                    //System.out.print(""+ board[i][j]+" | ");
                }
            }
            System.out.println();
            System.out.print("           +---+---+---+---+---+---+---+---+\n");
        }

        */

    }

}


