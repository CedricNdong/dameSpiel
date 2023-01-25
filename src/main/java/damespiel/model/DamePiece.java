package damespiel.model;
import lombok.Data;
/**
 * Class for the pieces.
 * <p>
 * Each piece has a position on the board.
 * The piece type is used to determine the color of the piece.
 * <p>
 * "@Data" is a lombok annotation, which generates getters and setters for all fields.
 */
@Data
public class DamePiece {
    private int xPosFrom;
    private int yPosFrom;
    private  PieceType pieceType;

    /**
     * Constructor for the piece.
     * @param xPosFrom The x position of the piece.
     * @param yPosFrom The y position of the piece.
     * @param pieceType The piece type of the piece.
     */
    public DamePiece(int xPosFrom, int yPosFrom, PieceType pieceType) {
        this.xPosFrom = xPosFrom;
        this.yPosFrom = yPosFrom;
        this.pieceType = pieceType;
    }
    /**
     * To string method for the piece.
     * @return The name of the piece.
     *"O" for white piece
     * "X" for black piece
     * etc...
     * This is used to print the board correctly it the console(also in the Jshell)
     */
    public String toString() {
        if (pieceType == PieceType.WHITE_PIECE) {
            return "O";
        } else if (pieceType == PieceType.BLACK_PIECE) {
            return "X";
        } else if (pieceType == PieceType.WHITE_QUEEN) {
            return "W";
        } else if (pieceType == PieceType.BLACK_QUEEN) {
            return "B";
        }else {
            return " ";
        }
    }
}
