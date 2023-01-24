package test.model;

import damespiel.model.Dame;

import damespiel.model.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DameTest {

        // test the method makeMove in the class Dame
        @Test
        public void testInvalidMove() {
            Dame dame = new Dame();
            dame.makeMove(5,0, 5, 1);
            assertNotEquals(PieceType.WHITE_PIECE, dame.getBoard()[5][1].getDamePiece().getPieceType());
            dame.makeMove(5, 1, 5, 2);
            assertNotEquals(PieceType.WHITE_PIECE, dame.getBoard()[5][2].getDamePiece().getPieceType());
        }

        // test promotion of a piece
        @Test
        public void testPromotion() {
            Dame dame = new Dame();
            dame.makeMove(3, 2, 3, 3);
            dame.makeMove(3, 3, 2, 4);
            dame.makeMove(2, 4, 2, 5);
            dame.makeMove(2, 5, 1, 6);
            dame.makeMove(1, 6, 1, 7);
            dame.makeMove(1, 7, 0, 8);
            assertEquals(PieceType.WHITE_QUEEN, dame.getBoard()[0][8].getDamePiece().getPieceType());
        }

        // test if a piece can be moved to a field that is already occupied
        @Test
        public void testMoveToOccupiedField() {
            Dame dame = new Dame();
            assertEquals(PieceType.WHITE_QUEEN, dame.getBoard()[0][7].getDamePiece().getPieceType());
        }

        // test if a piece can be moved to a field that is not on the board
        @Test
        public void testMoveToNonExistingField() {
            Dame dame = new Dame();
            dame.makeMove(3, 2, 3, 3);
            dame.makeMove(3, 3, 2, 4);
            dame.makeMove(2, 4, 2, 5);
            dame.makeMove(2, 5, 1, 6);
            dame.makeMove(1, 6, 1, 7);
            dame.makeMove(1, 7, 0, 8);
            dame.makeMove(0, 8, 0, 9);
        }
         // test if the mehtod correctly handles a capturing move
        @Test
        public void testCapture() {
            Dame dame = new Dame();
            dame.makeMove(3, 2, 3, 3);
            dame.makeMove(3, 3, 2, 4);
            dame.makeMove(2, 4, 2, 5);
            dame.makeMove(2, 5, 1, 6);
            dame.makeMove(1, 6, 1, 7);
            dame.makeMove(1, 7, 0, 8);
            dame.makeMove(0, 8, 0, 7);
            dame.makeMove(0, 7, 1, 6);
            assertEquals(PieceType.WHITE_QUEEN, dame.getBoard()[1][6].getDamePiece().getPieceType());
        }

        // test if the method correctly changes the current player after a valid move
        @Test
        public void testChangePlayer() {
            Dame dame = new Dame();
            dame.makeMove(3, 2, 4, 3);
            assertNotEquals(PieceType.BLACK_PIECE, dame.getCurrentPlayer().getPlayerPieceType());

            dame.makeMove(5,4,4,3);
            assertNotEquals(PieceType.WHITE_PIECE, dame.getCurrentPlayer().getPlayerPieceType());

        }

        // test for a invalid move
        @Test
        public void testInvalidMove2() {
            Dame dame = new Dame();
            dame.makeMove(3, 2, 4, 3);
            assertEquals(PieceType.WHITE_PIECE, dame.getBoard()[4][3].getDamePiece().getPieceType());
            dame.makeMove(4, 3, 5, 4);
            assertEquals(PieceType.WHITE_PIECE, dame.getBoard()[5][4].getDamePiece().getPieceType());
        }

        // test for a valid move
        @Test
        public void testValidMove() {
            Dame dame = new Dame();
            dame.makeMove(3, 2, 4, 3);
            assertEquals(PieceType.WHITE_PIECE, dame.getBoard()[4][3].getDamePiece().getPieceType());
            dame.makeMove(4, 3, 3, 4);
            assertEquals(PieceType.WHITE_PIECE, dame.getBoard()[3][4].getDamePiece().getPieceType());
        }


        @Test
        public void testInvalidMove_When_xFrom_yFrom_OutOfBoard() {
            Dame dame = new Dame();
            assertFalse(dame.isValidMove(-1, 0, 0, 0));
            assertFalse(dame.isValidMove(0, -1, 0, 0));
            assertFalse(dame.isValidMove(8, 0, 0, 0));
            assertFalse(dame.isValidMove(0, 8, 0, 0));
        }
        @Test
        public void testInvalidMove_When_xTo_yTo_OutOfBoard() {
            Dame dame = new Dame();
            assertFalse(dame.isValidMove(0, 0, -1, 0));
            assertFalse(dame.isValidMove(0, 0, 0, -1));
            assertFalse(dame.isValidMove(0, 0, 8, 0));
            assertFalse(dame.isValidMove(0, 0, 0, 8));
        }
        @Test
        public void testInvalidMove_When_xFrom_yFrom_Empty() {
            Dame dame = new Dame();
            assertFalse(dame.isValidMove(0, 0, 1, 1));
        }
        @Test
        public void testInvalidMove_When_xTo_yTo_NotEmpty() {
            Dame dame = new Dame();
            dame.makeMove(3, 2, 4, 3);
            assertFalse(dame.isValidMove(4, 3, 3, 4));
        }
        @Test
        public void testInvalidMove_When_CapturingOwnPiece() {
            Dame dame = new Dame();
            dame.makeMove(3, 2, 4, 3);
            assertFalse(dame.isValidMove(4, 3, 5, 4));
        }








    }
