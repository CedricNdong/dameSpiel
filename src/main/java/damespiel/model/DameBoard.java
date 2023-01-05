package damespiel.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DameBoard {

    private  final int BOARD_COLUMNS = 8;
    private  final int BOARD_ROWS = 8;

    private  final int PLAYER_PIECES = 12;


     private char[][]  board = new char[BOARD_ROWS][BOARD_COLUMNS];

    public DameBoard() {



    }
    public  void gameInitBoard() {
        //create a board with 8x8 fields
      //  createBoard();
        // fill the board with pieces
       // setPlayersPieces();

        System.out.print("           +---+---+---+---+---+---+---+---+\n");
        for (int i = 0; i < BOARD_ROWS; i++) {
            System.out.print("  "+i+ "        | ");
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if (i < 3 && (i + j) % 2 == 1) {
                    board[i][j] = 'X';
                    System.out.print(""+ board[i][j]+" | ");
                } else if (i > 4 && (i + j) % 2 == 1) {
                    board[i][j] = 'O';
                    System.out.print(""+ board[i][j]+" | ");
                } else {
                    board[i][j] = ' ';
                    System.out.print(""+ board[i][j]+" | ");
                }
            }
            System.out.println();
            System.out.print("           +---+---+---+---+---+---+---+---+\n");
        }

    }

    /*
    public void createBoard() {

        System.out.print("           +---+---+---+---+---+---+---+---+\n");
        for (int i = 0; i < BOARD_ROWS; i++) {
            System.out.print("  "+i+ "        | ");
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                board[i][j] = ' ';
                System.out.print(""+ board[i][j]+" | ");
            }
            System.out.println();
            System.out.print("           +---+---+---+---+---+---+---+---+\n");
        }


    }

     */
    public void setPlayersPieces() {

        //set player 1 pieces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                 if ((i + j) % 2 == 1) {
                     board[i][j] = 'x';
                     System.out.print(""+ board[i][j]+" | ");

                 }
            }
        }

        //set player 2 pieces
        for (int i = 5; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if ((i + j) % 2 == 1) {
                    board[i][j] = 'o';
                    System.out.print(""+ board[i][j]+" | ");
                }
            }
        }
    }








}





       /*// fill the board with pieces
       for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if (i < 3 && (i + j) % 2 == 1) {
                    board[i][j] = 'X';
                    System.out.print("" + board[i][j] + " | ");
                } else if (i > 4 && (i + j) % 2 == 1) {
                    board[i][j] = 'O';
                    System.out.print("" + board[i][j] + " | ");
                } else {
                    board[i][j] = ' ';
                    System.out.print("" + board[i][j] + " | ");
                }
            }


        }

        */

