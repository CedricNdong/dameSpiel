package damespiel.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Dame {
    private final int BOARD_COLUMNS=8,BOARD_ROWS = 8;
    private Cell[][] board;
    private DamePlayer[] damePlayers;
    private DamePlayer currentPlayer;
    private Cell cell;
    private   ArrayList<Integer>  to1,to2; // the arraylist to store the possible moves
    private boolean isGameOver;

    public Dame() {
        this.board = new Cell[BOARD_ROWS][BOARD_COLUMNS];
        this.damePlayers = new DamePlayer[]{new DamePlayer(PieceType.WHITE_PIECE),new DamePlayer(PieceType.BLACK_PIECE)};
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
        if (this.currentPlayer.getScore() == 12) {
            System.out.println("  The Winner is   "+this.currentPlayer.getPlayerPieceType());
           return this.isGameOver = true;
        }
        return this.isGameOver;
    }


    public void makeMove(int xFrom, int yFrom, int xTo, int yTo) {


        isGameOver();
        possibleMove(xFrom, yFrom);
        if (playerTurn(xFrom,yFrom)) {
            // check the vertical and horizontal move
            if (isMoveVerticalOrHorizontal(xFrom, yFrom, xTo, yTo)) {
                System.out.println("Move in this direction is not allow");
            } else if (this.board[xFrom][yFrom].isCellEmpty()) {// you selected an empty cell
                System.out.println("Can not move to an empty cell");
            } else if (!isMoveObliqueValid(xFrom, yFrom, xTo, yTo)) {
                System.out.println("Move is not valid.must select the next cell in the diagonal");
            } else {// move the piece





                //check if the move is valid,the cell to move is empty
                if (this.board[xTo][yTo].isCellEmpty()) {
                    this.board[xTo][yTo].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
                    this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));
                    // change the current player to the next player
                    changePlayer();
                    setPieceToQueen(xTo, yTo);

                } else { // the cell to move is not empty,

                    // check the current player
                    if (this.currentPlayer == this.damePlayers[0]){
                      //Player 1 is on the beat
                        if (this.board[xTo][yTo].getDamePiece().getPieceType() == PieceType.BLACK_PIECE) {//if the piece is enemy
                            // check if you can jump over the piece
                            if (this.board[xTo - 1][yTo - 1].getDamePiece().getPieceType() == PieceType.EMPTY) { // eat the piece on left side
                                this.board[xTo - 1][yTo - 1].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
                                // delete the piece that was eaten ,and the piece that was moved at his previous position
                                this.board[xTo][yTo].setDamePiece(new DamePiece(xTo, yTo, PieceType.EMPTY));
                                this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));

                                this.currentPlayer.setScore(this.currentPlayer.getScore() + 12);
                                System.out.println("Player  score is " + this.currentPlayer.getScore());
                                changePlayer();
                                setPieceToQueen(xTo - 1, yTo - 1);
                            } else if (this.board[xTo - 1][yTo + 1].getDamePiece().getPieceType() == PieceType.EMPTY) {// eat the piece on right side
                                this.board[xTo - 1][yTo + 1].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
                                this.board[xTo][yTo].setDamePiece(new DamePiece(xTo, yTo, PieceType.EMPTY));
                                this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));

                                this.currentPlayer.setScore(this.currentPlayer.getScore() + 12);
                                System.out.println("Player 1 score is " + this.currentPlayer.getScore());
                                changePlayer();
                                setPieceToQueen(xTo - 1, yTo + 1);

                            } else {
                                System.out.println("You can't eat there");
                            }
                        } else {// if the piece is not enemy
                            System.out.println("(! Impossible eat your own piece).Choose another piece");
                        }
                    }
                    else { //Player 2 is on the beat

                        if (this.board[xTo][yTo].getDamePiece().getPieceType() == PieceType.WHITE_PIECE) {//if the piece is enemy
                            // check if you can jump over the piece
                            if (this.board[xTo + 1][yTo - 1].getDamePiece().getPieceType() == PieceType.EMPTY) { // eat the piece on left side
                                this.board[xTo + 1][yTo - 1].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
                                // delete the piece that was eaten ,and the piece that was moved at his previous position
                                this.board[xTo][yTo].setDamePiece(new DamePiece(xTo, yTo, PieceType.EMPTY));
                                this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));

                                this.currentPlayer.setScore(this.currentPlayer.getScore() + 1);
                                System.out.println("Player 1 score is " + this.currentPlayer.getScore());
                                changePlayer();
                                setPieceToQueen(xTo + 1, yTo - 1);
                            } else if (this.board[xTo + 1][yTo + 1].getDamePiece().getPieceType() == PieceType.EMPTY) {// eat the piece on right side
                                this.board[xTo + 1][yTo + 1].setDamePiece(this.board[xFrom][yFrom].getDamePiece());
                                this.board[xTo][yTo].setDamePiece(new DamePiece(xTo, yTo, PieceType.EMPTY));
                                this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));

                                this.currentPlayer.setScore(this.currentPlayer.getScore() + 1);
                                System.out.println("Player 1 score is " + this.currentPlayer.getScore());
                                changePlayer();
                                setPieceToQueen(xTo + 1, yTo + 1);

                            } else {
                                System.out.println("You can't eat there");
                            }
                        } else {// if the piece is not enemy
                            System.out.println("(! Impossible eat your own piece).Choose another piece");
                        }
                    }

                }


            }
        }else {
            System.out.println("It's not your turn");
        }
        printBoard();
        isGameOver();
    }

    // method of possible moves



    // player turn method to  give the player who is playing and disable the other player

    public boolean playerTurn(int xFrom, int yFrom) {
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.WHITE_PIECE && this.currentPlayer == this.damePlayers[0]) {
            System.out.println(this.currentPlayer.getPlayerPieceType()+"  is playing...");
            return true;
        }else if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.BLACK_PIECE && this.currentPlayer == this.damePlayers[1]) {
            System.out.println(this.currentPlayer.getPlayerPieceType()+"  is playing...");
            return true;
        } else if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.EMPTY && this.currentPlayer == this.damePlayers[1]) {
            System.out.println(this.currentPlayer.getPlayerPieceType()+"  can not move from an empty cell");
            return false;
        } else if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.EMPTY && this.currentPlayer == this.damePlayers[0]) {
            System.out.println(this.currentPlayer.getPlayerPieceType()+"  can not move from an empty cell");
            return false;
        }
        else {
            System.out.println("It's not your turn. "+this.currentPlayer.getPlayerPieceType()+" should first pay");
            return false;
        }
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

    public void possibleMove(int xFrom, int yFrom) {


        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.WHITE_PIECE) {

            if (xFrom - 1 >= 0 && yFrom - 1 >= 0) {
                if (this.board[xFrom - 1][yFrom - 1].getDamePiece().getPieceType() == PieceType.EMPTY) {
                    to1 = new ArrayList();
                    to1.add(xFrom - 1);
                    to1.add(yFrom - 1);
                    System.out.println("can move to" + to1);
                }
            }
            if (xFrom - 1 >= 0 && yFrom + 1 >= 0) {
                if (this.board[xFrom - 1][yFrom + 1].getDamePiece().getPieceType() == PieceType.EMPTY) {
                    to2 = new ArrayList();
                    to2.add(xFrom - 1);
                    to2.add(yFrom + 1);
                    System.out.println("can move to" + to2);
                }
            }
        }
        else if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.BLACK_PIECE) {

            if (xFrom + 1 >= 0 && yFrom - 1 >= 0) {

                if (this.board[xFrom + 1][yFrom - 1].getDamePiece().getPieceType() == PieceType.EMPTY) {
                    to1 = new ArrayList();
                    to1.add(xFrom + 1);
                    to1.add(yFrom - 1);
                    System.out.println("can move to" + to1);
                }
            }

            if (xFrom + 1 >= 0 && yFrom + 1 >= 0) {

                if (this.board[xFrom + 1][yFrom + 1].getDamePiece().getPieceType() == PieceType.EMPTY) {
                    to2 = new ArrayList();
                    to2.add(xFrom + 1);
                    to2.add(yFrom + 1);
                    System.out.println("can move to" + to2);
                }
            }
        }
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
        return xFrom == xTo || yFrom == yTo;
    }
    // change the current player
    public void changePlayer() {
        if (this.currentPlayer == this.damePlayers[0]) {
            this.currentPlayer = this.damePlayers[1];
        } else {
            this.currentPlayer = this.damePlayers[0];
        }
    }


    // method to check if the move is valid and return all case of invalid move
    public boolean isValidMove(int xFrom, int yFrom, int xTo, int yTo) {
        // check if the selected piece is not empty
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.EMPTY) {
            System.out.println("You can not move from an empty cell");
            return false;
        }
        // check if the selected piece is for the current player
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.WHITE_PIECE && this.currentPlayer == this.damePlayers[0]) {
            System.out.println(this.currentPlayer.getPlayerPieceType()+"  is playing...");
        } else if (this.board[xFrom][yFrom].getDamePiece().getPieceType() == PieceType.BLACK_PIECE && this.currentPlayer == this.damePlayers[1]) {
            System.out.println(this.currentPlayer.getPlayerPieceType()+"  is playing...");
        } else {
            System.out.println("It's not your turn. "+this.currentPlayer.getPlayerPieceType()+" should first pay");
            return false;
        }

        // check if the xTo and yTo Cell is empty
        if (this.board[xTo][yTo].getDamePiece().getPieceType() == PieceType.EMPTY) {
            System.out.println("You can not move to a empty cell");
            return false;
        }

        // check if the xTo and yTo coordinates are not out of the board
        if (xTo < 0 || xTo >= BOARD_ROWS || yTo < 0 || yTo >= BOARD_COLUMNS) {
            System.out.println("You can not move out of the board");
            return false;
        }

        // check if the move is a diagonal move
        if (Math.abs(xFrom - xTo) != Math.abs(yFrom - yTo)) {
            System.out.println("You can only move diagonally");
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









    public void undoMove(int xFrom, int yFrom, int xTo, int yTo) {

    // check if the undo is valid



    // the cell to undo is empty



    // the cell to undo is not empty




    // undo the move
    this.board[xFrom][yFrom].setDamePiece(this.board[xTo][yTo].getDamePiece());
    this.board[xTo][yTo].setDamePiece(new DamePiece(xTo, yTo, PieceType.EMPTY));

    printBoard();
}


}

