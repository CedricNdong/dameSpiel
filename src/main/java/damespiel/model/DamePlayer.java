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

    public void addScore() {
        this.score++;
    }

    // should be a type of  Move
    public PieceType onPlayer() {
        System.out.println("Player 1 ist playing");
        return this.playerPieceType;
    }

}
