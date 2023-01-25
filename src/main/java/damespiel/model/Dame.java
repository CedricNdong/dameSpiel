package damespiel.model;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for the dame game.
 * <p>
 * The dame game is played on a 8x8 board.
 * The dame game has 2 players.
 * <p>
 * Each player has 2 different piece types.
 * One piece type is the normal piece, the other piece type is the queen piece.
 * Each player begins with 12 pieces.
 * This is the main class of the logic of the game with the methods to move the pieces, to check if the game is over, etc...
 * <p>
 * "@Data" is a lombok annotation, which generates getters and setters for all fields.
 */
@Data
public class Dame {
    private final int BOARD_COLUMNS=8,BOARD_ROWS = 8;
    private Cell[][] board;
    private DamePlayer player1;
    private DamePlayer player2;
    private DamePlayer currentPlayer;
    private Cell cell;
    private boolean isGameOver;

    /**
     * Constructor for the dame game.
     * <p>
     * The dame game is initialized with 2 players.
     * The dame game is initialized with a board.
     * The dame game is initialized with the current player as player 1.
     * The dame game is initialized with the pieces on the board.
     */
    public Dame() {
        this.board = new Cell[BOARD_ROWS][BOARD_COLUMNS];
        this.player1 = new DamePlayer(PieceType.WHITE_PIECE, PieceType.WHITE_QUEEN);
        this.player2 = new DamePlayer(PieceType.BLACK_PIECE, PieceType.BLACK_QUEEN);
        this.currentPlayer = this.player1;
        this.gameInitBoard();
        this.isGameOver = false;
    }
    /**
     * Method to initialize the board.
     * <p>
     * The board is initialized with 12 pieces for each player.
     * The pieces are placed on the board.
     */
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

    /**
     * Method to correctly display the board on the console(also Jshell).
     * <p>
     * The board is displayed with the coordinates of the pieces.
     */
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

    /**
     * Method to check if the game is over.
     * <p>
     * The game is over if one of the players has no piece left.
     */
    public boolean isGameOver() {

        if (this.currentPlayer.getNumberOpponentPieces() == 12) {
            System.out.println("  The Winner is   "+this.currentPlayer);
           return this.isGameOver = true;
        }
        return this.isGameOver;
    }

    /**
     * Method to check if the piece which is moved is on the promotion row.
     * <p>
     * The promotion row is the first row for the white pieces and the last row for the black pieces.
     * If the piece is on the promotion row, the piece is promoted to a queen piece.
     * When a piece is promoted, the score of the player is increased by 2.
     * @param x the x coordinate of the piece.
     * @param y the y coordinate of the piece.
     */
    public void setPieceToQueen(int x, int y) {
        if (this.board[x][y].getDamePiece().getPieceType() == PieceType.WHITE_PIECE && x == 0) {
            this.board[x][y].setDamePiece(new DamePiece(x, y, PieceType.WHITE_QUEEN));
            this.currentPlayer.setScore(this.currentPlayer.getScore() + 2);
        } else if (this.board[x][y].getDamePiece().getPieceType() == PieceType.BLACK_PIECE && x == 7) {
            this.board[x][y].setDamePiece(new DamePiece(x, y, PieceType.BLACK_QUEEN));
            this.currentPlayer.setScore(this.currentPlayer.getScore() + 2);
        }
    }

    /**
     * Method to check if the piece is a queen piece.
     * <p>
     * @param x the x coordinate of the piece.
     * @param y the y coordinate of the piece.
     * @return true if the piece is a queen piece, false otherwise.
     */
    public boolean isQueen(int x, int y) {
        if ((this.board[x][y].getDamePiece().getPieceType() == PieceType.WHITE_QUEEN)
                || (this.board[x][y].getDamePiece().getPieceType() == PieceType.BLACK_QUEEN)) {
            return true;
        }
        return false;
    }

    /**
     * Method to change the current player.
     * <p>
     * The current player is changed after each move.
     */
    public void changePlayer() {
        this.currentPlayer = (this.currentPlayer == this.player1) ? this.player2 : this.player1;
    }

    /**
     * Method to check if the move is valid.
     * This is for a normal piece.
     * <p>
     * The Player should first select a piece and then select the destination cell.
     * It is not allowed to move a piece to an empty cell, to a cell which is out of the board or to a cell which is not empty.
     * It is allowed to move a piece over the opponent pieces if the destination cell is empty.
     * It can only move in one or two directions diagonally.
     * @param xFrom the x coordinate of the selected piece.
     * @param yFrom the y coordinate of the selected piece.
     * @param xTo the x coordinate of the destination cell.
     * @param yTo the y coordinate of the destination cell.
     * @return true if the move is valid, false otherwise.
     */
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
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() != (this.currentPlayer.getPlayerPieceType1())
                && this.board[xFrom][yFrom].getDamePiece().getPieceType() != (this.currentPlayer.getPlayerPieceType2())) {
            System.out.println(this.currentPlayer+":  You can not move a piece that is not yours, please select a piece of your color");
            return false;
        }
        // check if the move diagonal
        if (Math.abs(xTo - xFrom) != Math.abs(yTo - yFrom)) {
            System.out.println("You can only move diagonally");
            return false;
        }

        // check if the move to the same pieceType or  opposite pieceType
        if ((this.board[xTo][yTo].getDamePiece().getPieceType() == this.currentPlayer.getPlayerPieceType1())
                || (this.board[xTo][yTo].getDamePiece().getPieceType() == this.currentPlayer.getPlayerPieceType2())
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
            }else if ((this.board[xMiddle][yMiddle].getDamePiece().getPieceType() == this.currentPlayer.getPlayerPieceType1())
                    || (this.board[xMiddle][yMiddle].getDamePiece().getPieceType() == this.currentPlayer.getPlayerPieceType2())) {
                System.out.println("You can only capture a piece");
                return false;
            }
        }
        return true;
    }

    /**
     * Method which returns a list of all possible moves for a piece.
     * @param xFrom the x coordinate of the selected piece.
     * @param yFrom the y coordinate of the selected piece.
     * @return a list of all possible moves for a piece.
     * A move has the format [xTo, yTo].
     * The list is empty if there are no possible moves.
     */
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
            int moveDirection = ((this.currentPlayer.getPlayerPieceType1() == PieceType.WHITE_PIECE)
                    || this.currentPlayer.getPlayerPieceType2() == PieceType.WHITE_QUEEN) ? -1 : 1;
            for (int i = -1; i <= 1; i += 2) {
                int xTo = xFrom + moveDirection;
                int yTo = yFrom + i;
                if (isValidMove(xFrom, yFrom, xTo, yTo)) {
                    listOfMoves.add(new int[]{xTo, yTo});
                }
            }
        }
        return listOfMoves;
    }

    /**
     * Method which checks if the move is for a queen.
     * If the piece is a queen, it can move in all directions.
     * It can also capture pieces in all directions.
     * @param xFrom the x coordinate of the selected queen.
     * @param yFrom the y coordinate of the selected queen.
     * @param xTo the x coordinate of the destination cell.
     * @param yTo the y coordinate of the destination cell.
     * @return
     */
    public boolean isQueenMove(int xFrom, int yFrom, int xTo, int yTo) {
        if (Math.abs(xFrom - xTo) == Math.abs(yFrom - yTo)) {

            int xDirection = Integer.compare(xTo, xFrom);
            int yDirection = Integer.compare(yTo, yFrom);
            int x = xFrom + xDirection;
            int y = yFrom + yDirection;
            int xMiddle = x;
            int yMiddle = y;
            while (x != xTo) {
                if ((this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY && this.board[x][y].getDamePiece().getPieceType() == this.currentPlayer.getPlayerPieceType2())
                        || (this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY && this.board[x][y].getDamePiece().getPieceType() == this.currentPlayer.getPlayerPieceType1())){

                    return false;
                } else if ((this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY && this.board[x][y].getDamePiece().getPieceType() != this.currentPlayer.getPlayerPieceType2())
                        || (this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY && this.board[x][y].getDamePiece().getPieceType() != this.currentPlayer.getPlayerPieceType1())){
                     xMiddle = x ;
                     yMiddle = y ;
                }
                x += xDirection;
                y += yDirection;
            }
            if ((this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY && this.board[xFrom][yFrom].getDamePiece().getPieceType() == this.board[x][y].getDamePiece().getPieceType())
                    || (this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY && this.board[x][y].getDamePiece().getPieceType() == this.currentPlayer.getPlayerPieceType1())
                    || (this.board[x][y].getDamePiece().getPieceType() != PieceType.EMPTY && this.board[x][y].getDamePiece().getPieceType() != this.currentPlayer.getPlayerPieceType1())) {
                return false;
            }
            this.board[xMiddle][yMiddle].setDamePiece(new DamePiece(xMiddle,yMiddle,PieceType.EMPTY));
            this.currentPlayer.setScore(this.currentPlayer.getScore() + 2);
            this.currentPlayer.setNumberOpponentPieces(this.currentPlayer.getNumberOpponentPieces() + 1);
            if (this.board[xTo][yTo].getDamePiece().getPieceType() != PieceType.EMPTY
                    && this.board[xFrom][yFrom].getDamePiece().getPieceType() == this.board[xTo][yTo].getDamePiece().getPieceType()) {

            return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Method which move the piece from the selected cell to the destination cell.
     * It uses the isValidMove and the isQueenMove method to check if the move is valid before making the move.
     * It also checks if the coordinates are not out of the board.
     * If a piece  is captured by a queen, the score is increased by 2.
     * If a piece is captured by a normal piece, the score is increased by 1.
     * @param xFrom the x coordinate of the selected piece.
     * @param yFrom the y coordinate of the selected piece.
     * @param xTo the x coordinate of the destination cell.
     * @param yTo the y coordinate of the destination cell.
     */
    public void makeMove(int xFrom, int yFrom, int xTo, int yTo) {

        // check if the xFrom and yFrom coordinates are not out of the board
        if (xFrom < 0 || xFrom >= this.BOARD_ROWS || yFrom < 0 || yFrom >= this.BOARD_COLUMNS) {
            System.out.println("You can not move out of the board / give [0-7] coordinates");
            return;
        }
        // check if the selected piece is for the current player
        if (this.board[xFrom][yFrom].getDamePiece().getPieceType() != (this.currentPlayer.getPlayerPieceType1())
                && this.board[xFrom][yFrom].getDamePiece().getPieceType() != (this.currentPlayer.getPlayerPieceType2())) {
            System.out.println("It's not your turn. " + this.currentPlayer + " should first play");
            return;
        }
        // check if the piece is a queen
        if (this.isQueen(xFrom, yFrom)) {
            // check if the move is a valid queen
            if (isQueenMove(xFrom, yFrom, xTo, yTo)) {
                this.board[xTo][yTo].setDamePiece(new DamePiece(xFrom, yFrom, this.board[xFrom][yFrom].getDamePiece().getPieceType()));
                this.board[xFrom][yFrom].setDamePiece(new DamePiece(xFrom, yFrom, PieceType.EMPTY));
                changePlayer();
                printBoard();


            } else{
                System.out.println("You can not move a queen like that");
                printBoard();
            }
            return;
        }
        // check invalid move
        if (!isValidMove(xFrom, yFrom, xTo, yTo)) {
            printBoard();
            return;
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
            setPieceToQueen(xTo, yTo);
        }
        if (Math.abs(xFrom - xTo) == 1) {
            if ((this.currentPlayer.getPlayerPieceType1() == PieceType.WHITE_PIECE) || (this.currentPlayer.getPlayerPieceType2() == PieceType.WHITE_QUEEN)) {
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
        // check if the game is over
        if (this.isGameOver()) {
            return;
        }
        changePlayer();
        printBoard();
    }

}

