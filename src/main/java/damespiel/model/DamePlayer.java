package damespiel.model;

import lombok.Data;


@Data

public class DamePlayer {
    private int score;
    private PieceType playerPieceType;

    public DamePlayer(PieceType playerPieceType) {
        this.score = 0;
        this.playerPieceType = playerPieceType;
    }
}
