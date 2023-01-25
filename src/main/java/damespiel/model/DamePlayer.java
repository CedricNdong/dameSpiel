package damespiel.model;
import lombok.Data;
/**
 * Class for the players.
 * <p>
 * Each player has a score, 2 pieces types with different.
 * The first piece type is the normal piece, the second piece type is the queen piece.
 * The number of opponent pieces is used to determine the winner.
 * <p>
 * "@Data" is a lombok annotation, which generates getters and setters for all fields.
 */
@Data

public class DamePlayer {
    private int score;
    private PieceType playerPieceType1;
    private PieceType playerPieceType2;
    private int numberOpponentPieces;

    /**
     * Constructor for the player.
     * @param playerPieceType1 The normal piece type of the player.
     * @param playerPieceType2 The queen piece type of the player.
     */
    public DamePlayer(PieceType playerPieceType1, PieceType playerPieceType2) {
        this.score = 0;
        this.playerPieceType1 = playerPieceType1;
        this.playerPieceType2 = playerPieceType2;
        this.numberOpponentPieces = 0;
    }

    /**
     * To string method for the player.
     * @return The name of the player.
     */

    public String toString() {
        return playerPieceType1 == PieceType.WHITE_PIECE || playerPieceType2 == PieceType.WHITE_QUEEN ? "Player 1" : "Player 2";
    }

}
