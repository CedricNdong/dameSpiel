package damespiel.model;
import lombok.Data;

/**
 * Class for the cells.
 * <p>
 * Each cell has a position on the board.
 * Cells are use to build the board.
 * The cell can contain a piece or be empty.
 * <p>
 * "@Data" is a lombok annotation, which generates getters and setters for all fields.
 */
@Data
public class Cell {
    private int xCellPos;
    private int yCellPos;
    private DamePiece damePiece;

    /**
     * Constructor for the cell.
     * @param xCellPos The x position of the cell.
     * @param ySpacePos The y position of the cell.
     */
    public Cell(int xCellPos, int ySpacePos) {
        this.xCellPos = xCellPos;
        this.yCellPos = ySpacePos;
    }
    /**
     * To string method for the cell.
     * @return The content of the cell.
     *It can be a piece or an empty space
     * This is used to print the board correctly it the console(also in the Jshell)
     */
    public String toString() {
        return damePiece != null ? damePiece.toString() : " ";
    }

}