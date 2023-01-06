package damespiel.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public enum PieceType {
    BLACK_PIECE('O'),
    WHITE_PIECE('X'),
    BLACK_QUEEN('Q'),
    WHITE_QUEEN('W');



    @Getter
    private char pieceColor;

}
