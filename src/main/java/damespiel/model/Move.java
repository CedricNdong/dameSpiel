package damespiel.model;

import lombok.Data;

@Data
public class Move {
    private int xPosFrom;
    private int yPosFrom;
    private DamePiece damePiece;
    private DamePiece onPiece;

    public Move(int xPosFrom, int yPosFrom, DamePiece onPiece) {
        this.xPosFrom = xPosFrom;
        this.yPosFrom = yPosFrom;
        this.onPiece = onPiece;
    }
}
