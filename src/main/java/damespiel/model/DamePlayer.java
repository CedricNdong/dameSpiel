package damespiel.model;

import lombok.Data;


@Data

public class DamePlayer {
    private int score;
    private PieceType playerPieceType;
    // number of opponent pieces
    private int numberOpponentPieces;

    public DamePlayer(PieceType playerPieceType) {
        this.score = 0;
        this.playerPieceType = playerPieceType;
        this.numberOpponentPieces = 0;
    }
}
