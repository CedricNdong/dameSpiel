import damespiel.model.Dame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int x,y, x2, y2,isUndo;

        System.out.println("Hello world! and welcome to the dame game");
        System.out.println("WHITE_PIECE = O  , BLACK_PIECE = X BLACK_QUEEN = Q and WHITE_QUEEN = W");
        System.out.println("");
        Scanner scanner = new Scanner(System.in);

        Dame dame = new Dame();

        System.out.println("\n");

        while(true) {
            System.out.println("##>>>  "+dame.getCurrentPlayer().getPlayerPieceType()+ " is playing");
            System.out.println("\nx please");
            x  = scanner.nextInt();
            System.out.println("y please");
            y  = scanner.nextInt();

            System.out.println("x2 please");
            x2 = scanner.nextInt();
            System.out.println("y2 please");
            y2 = scanner.nextInt();


                //dame.makeMove(x, y, x2, y2);


            //dame.possibleMoves(x,y);
            //give the possible position of the
           // System.out.println("possible move to "+dame.getTo1().get(0)+" "+dame.getTo1().get(1));
            //System.out.println("possible move to "+dame.getTo2().get(0)+" "+dame.getTo2().get(1));
          //  dame.possibleMoves(x,y);
            //List<int[]>moves = dame.possibleMoves(x,y);
            //for (int[] move : moves) {
             //   System.out.println("possible move to "+move[0]+" "+move[1]);
            //}
            dame.makeMove(x,y,x2,y2);
            System.out.println("undo? 10 for yes 0 for no");
            isUndo = scanner.nextInt();
            //undo
            if (isUndo == 10) {
                dame.undoMove(x,y,x2,y2);
            }








        }
    }
}
