package damespiel.model;

import lombok.Data;


@Data

public class DamePlayer {
    private int score;
    private PieceType playerPieceType1;
    private PieceType playerPieceType2;

    // number of opponent pieces
    private int numberOpponentPieces;

    public DamePlayer(PieceType playerPieceType1, PieceType playerPieceType2) {
        this.score = 0;
        this.playerPieceType1 = playerPieceType1;
        this.playerPieceType2 = playerPieceType2;
        this.numberOpponentPieces = 0;
    }

    // toString method
    public String toString() {
        if (playerPieceType1 == PieceType.WHITE_PIECE || playerPieceType2 == PieceType.WHITE_QUEEN) {
            return "Player 1";
        } else if (playerPieceType1 == PieceType.BLACK_PIECE || playerPieceType2 == PieceType.BLACK_QUEEN) {
            return "Player 2";
        } else {
            return " ";
        }
    }

}
