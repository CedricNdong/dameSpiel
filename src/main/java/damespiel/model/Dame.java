package damespiel.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Dame {
    private final int BOARD_COLUMNS=8,BOARD_ROWS = 8;
    private Cell[][] board;
    private DamePlayer[] damePlayers;
    private DamePlayer currentPlayer;
    private Cell cell;
    private boolean isGameOver;

    public Dame() {
        this.board = new Cell[BOARD_ROWS][BOARD_COLUMNS];
        this.damePlayers = new DamePlayer[]{new DamePlayer(PieceType.WHITE_PIECE, PieceType.WHITE_QUEEN), new DamePlayer(PieceType.BLACK_PIECE, PieceType.BLACK_QUEEN)};
        this.currentPlayer = this.damePlayers[0];
        this.gameInitBoard();
        this.isGameOver = false;
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
    // method to check if the game is over
    public boolean isGameOver() {

        if (this.currentPlayer.getNumberOpponentPieces() == 12) {
            System.out.println("  The Winner is   "+this.currentPlayer.getPlayerPieceType());
           return this.isGameOver = true;
        }
        return this.isGameOver;
    }

    // method of possible moves
    // player turn method to  give the player who is playing and disable the other player


    public void setPieceToQueen(int x, int y) {
        if (this.board[x][y].getDamePiece().getPieceType() == PieceType.WHITE_PIECE && x == 0) {
            this.board[x][y].setDamePiece(new DamePiece(x, y, PieceType.WHITE_QUEEN));
        } else if (this.board[x][y].getDamePiece().getPieceType() == PieceType.BLACK_PIECE && x == 7) {
            this.board[x][y].setDamePiece(new DamePiece(x, y, PieceType.BLACK_QUEEN));
        }
    }
    public boolean isQueen(int x, int y) {
        if ((this.board[x][y].getDamePiece().getPieceType() == PieceType.WHITE_QUEEN) || (this.board[x][y].getDamePiece().getPieceType() == PieceType.BLACK_QUEEN)) {
            return true;
        }
        return false;
    }

    // change the current player
    public void changePlayer() {
        this.currentPlayer = (this.currentPlayer == this.damePlayers[0]) ? this.damePlayers[1] : this.damePlayers[0];
    }


    // method to check if the move is valid and return all case of invalid move of a normal piece
    public boolean isValidMove(int xFrom, int yFrom, int xTo, int yTo) {

        // check if the selected piece is not empty
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.EMPTY) {
            System.out.println("You can not move from an empty cell");
            return false;
        }

        // check if the xTo and yTo coordinates are not out of the board
        if (xTo < 0 || xTo >= this.BOARD_ROWS || yTo < 0 || yTo >= this.BOARD_COLUMNS) {
            System.out.println("You can not move out of the board ...");
            return false;
        }

        // check if the selected piece is for the current player
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() != this.currentPlayer.getPlayerPieceType()) {
            System.out.println(this.currentPlayer.getPlayerPieceType()+":  it is not your piece");
            return false;
        }
        // check if the move diagonal
        if (Math.abs(xTo - xFrom) != Math.abs(yTo - yFrom)) {
            System.out.println("You can only move diagonally");
            return false;
        }

        // check if the move to the same pieceType or  opposite pieceType
        if ((this.board[xTo][yTo].getDamePiece().getPieceType() == (this.currentPlayer.getPlayerPieceType()))
                || (this.board[xTo][yTo].getDamePiece().getPieceType() != PieceType.EMPTY)) {
            System.out.println("You can not move to a cell with the same piece type or on opposite piece type");
            return false;
        }

        // check if the move is a capturing move
        if (Math.abs(xFrom - xTo)==2){
            int xMiddle = (xFrom + xTo) / 2;
            int yMiddle = (yFrom + yTo) / 2;
            if (this.board[xMiddle][yMiddle].getDamePiece().getPieceType() == PieceType.EMPTY) {
                System.out.println("You can only capture a piece");
                return false;
            }else if (this.board[xMiddle][yMiddle].getDamePiece().getPieceType() == this.currentPlayer.getPlayerPieceType()) {
                System.out.println("You can only capture a piece");
                return false;
            }
        }

        return true;
    }
/*
    // method to check if the move is valid and return all case of invalid move of a queen
    public boolean isValidMoveQueen(int xFrom, int yFrom, int xTo, int yTo) {

        // check if the xTo and yTo coordinates are not out of the board
        if (xTo < 0 || xTo >= this.BOARD_ROWS || yTo < 0 || yTo >= this.BOARD_COLUMNS) {
            System.out.println("You can not move out of the board ...");
            return false;
        }
        // check if the selected piece is for the current player
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() != this.currentPlayer.getPlayerPieceType()) {
            System.out.println(this.currentPlayer.getPlayerPieceType()+":  it is not your piece");
            return false;
        }
        // check if the move diagonal
        if (Math.abs(xTo - xFrom) != Math.abs(yTo - yFrom)) {
            System.out.println("You can only move diagonally");
            return false;
        }

        // check if the move to the same pieceType or  opposite pieceType
        if ((this.board[xTo][yTo].getDamePiece().getPieceType() == (this.currentPlayer.getPlayerPieceType()))
                || (this.board[xTo][yTo].getDamePiece().getPieceType() != PieceType.EMPTY)) {
            System.out.println("You can not move to a cell with the same piece type or on opposite piece type");
            return false;
        }

        return true;
    }



 */














    // method to the possible moves for a piece
    public List<int[]> possibleMoves(int xFrom, int yFrom) {
        List<int[]> listOfMoves = new ArrayList<>();
        // check if the piece is a queen and add all possible moves in all directions
        if (this.isQueen(xFrom, yFrom)) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    int xTo = xFrom + i;
                    int yTo = yFrom + j;
                    if (isValidMove(xFrom, yFrom, xTo, yTo)) {
                        listOfMoves.add(new int[]{xTo, yTo});
                    }
                }
            }
        } else {
            int moveDirection = this.currentPlayer.getPlayerPieceType() == PieceType.WHITE_PIECE ? -1 : 1;
            for (int i = -1; i <= 1; i += 2) {
                int xTo = xFrom + moveDirection;
                int yTo = yFrom + i;
                if (isValidMove(xFrom, yFrom, xTo, yTo)) {
                    System.out.println("the possible moves from the method possibleMoves are: " + xTo + " " + yTo);
                    listOfMoves.add(new int[]{xTo, yTo});
                }
            }
        }
        return listOfMoves;
    }


  /*  // create a method isQueenMove to check if the move is a queen move
    public boolean isQueenMove(int xFrom, int yFrom, int xTo, int yTo) {
        if (Math.abs(xFrom - xTo) == Math.abs(yFrom - yTo)) {
            int xDirection = Integer.compare(xTo, xFrom);
            int yDirection = Integer.compare(yTo, yFrom);
            int x = xFrom + xDirection;
            int y = yFrom + yDirection;
            while (x != xTo) {
                if (this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY) {
                    return false;
                }
                x += xDirection;
                y += yDirection;
            }
            return true;
        }
        return false;
    }


   */

    public boolean isQueenMove(int xFrom, int yFrom, int xTo, int yTo) {
        if (Math.abs(xFrom - xTo) == Math.abs(yFrom - yTo)) {
            int xDirection = Integer.compare(xTo, xFrom);
            int yDirection = Integer.compare(yTo, yFrom);
            int x = xFrom + xDirection;
            int y = yFrom + yDirection;
            while (x != xTo) {
                if (this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY) {
                    return false;
                }
                x += xDirection;
                y += yDirection;
            }
            // Check if destination cell contains an opponent's piece
            if (this.board[xTo][yTo].getDamePiece().getPieceType() != PieceType.EMPTY &&
                    this.board[xFrom][yFrom].getDamePiece().getPieceType() != this.board[xTo][yTo].getDamePiece().getPieceType()) {
                // Remove opponent's piece from the board

            }
            return true;
        }
        return false;
    }

    // method to move a piece
    public void makeMove(int xFrom, int yFrom, int xTo, int yTo) {
        // check if the xFrom and yFrom coordinates are not out of the board
        if (xFrom < 0 || xFrom >= this.BOARD_ROWS || yFrom < 0 || yFrom >= this.BOARD_COLUMNS) {
            System.out.println("You can not move out of the board / give [0-7] coordinates");
            return;
        }
        // check invalid move
        if (!isValidMove(xFrom, yFrom, xTo, yTo)) {
            printBoard();
            return;
        }
        // check if the selected piece is for the current player
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() != this.currentPlayer.getPlayerPieceType()) {
            System.out.println("It's not your turn. " + this.currentPlayer.getPlayerPieceType() + " should first play");
            return;
        }


        // check if the piece is a queen
        if (this.isQueen(xFrom, yFrom)) {
            // check if the move is a valid queen
            if (isQueenMove(xFrom, yFrom, xTo, yTo)) {


                    this.board[xTo][yTo].setDamePiece(new DamePiece(xFrom, yFrom, this.board[xFrom][yFrom].getDamePiece().getPieceType()));
                    this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));
                    printBoard();

            } else{
                System.out.println("You can not move a queen like that");
                printBoard();

            }
        }

        // check if the move is a capturing move
        if (Math.abs(xFrom - xTo) == 2) {
            int xMiddle = (xFrom + xTo) / 2;
            int yMiddle = (yFrom + yTo) / 2;
            this.board[xTo][yTo].setDamePiece(new DamePiece(xFrom,yFrom, this.board[xFrom][yFrom].getDamePiece().getPieceType()));
            this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom,yFrom, PieceType.EMPTY));
            this.board[xMiddle][yMiddle].setDamePiece(new DamePiece(xMiddle, yMiddle, PieceType.EMPTY));
            this.currentPlayer.setScore(this.currentPlayer.getScore() + 1);
            this.currentPlayer.setNumberOpponentPieces(this.currentPlayer.getNumberOpponentPieces() + 1);
            System.out.println("You captured a piece");
            setPieceToQueen(xTo, yTo);
        }


        if (Math.abs(xFrom - xTo) == 1) {
            if (this.currentPlayer.getPlayerPieceType() == PieceType.WHITE_PIECE) {
                if (xTo > xFrom) {
                    System.out.println("You can only move forward");
                    printBoard();
                    return;
                }
            } else {
                if (xTo < xFrom) {
                    System.out.println("You can only move forward");
                    printBoard();
                    return;
                }

            }
            this.board[xTo][yTo].setDamePiece(new DamePiece(xFrom,yFrom, this.board[xFrom][yFrom].getDamePiece().getPieceType()));
            this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));

            setPieceToQueen(xTo, yTo);
        }
        // check for promotion





        changePlayer();

        printBoard();
    }

}

