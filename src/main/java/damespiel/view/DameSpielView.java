package damespiel.view;
import damespiel.controller.DameSpielController;
import damespiel.controller.IDameSpielController;
import damespiel.controller.IDameSpielView;
import processing.core.PApplet;
import processing.core.PImage;


public class DameSpielView extends PApplet implements IDameSpielView {

    PImage startScreen;
    IDameSpielController dameSpielController;
    PImage WHITE_PIECE,BLACK_PIECE,WHITE_QUEEN,BLACK_QUEEN;
    PImage[][]  gameBoard;

    private boolean onClick = true;
    private boolean move = true;
    private int xPos,yPos, xPosTo, yPosTo; // mouse position



    public DameSpielView() {
        setSize( 1000, 1000);
    }
    public void setup() {
        this.dameSpielController = new DameSpielController(this);
        startScreen = loadImage("src/main/java/damespiel/view/images/StartScreen.png");
        BLACK_PIECE = loadImage("src/main/java/damespiel/view/images/BlackPiece.png");
        WHITE_PIECE = loadImage("src/main/java/damespiel/view/images/WhitePiece.png");
        BLACK_QUEEN = loadImage("src/main/java/damespiel/view/images/BlackQueen.png");
        WHITE_QUEEN = loadImage("src/main/java/damespiel/view/images/WhiteQueen.png");
    }
    public void draw() {
        dameSpielController.nextFrame();
    }
    @Override
    public void onStartBoard() {
        onClick = false;
        background(0);
        //intitial position of the pieces
        gameBoard = new PImage[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i < 3 && (i + j) % 2 == 1) {
                    gameBoard[i][j] = BLACK_PIECE;
                } else if (i > 4 && (i + j) % 2 == 1) {
                    gameBoard[i][j] = WHITE_PIECE;

                }
                else {
                    gameBoard[i][j] = null;
                }
            }
        }
        // Draw the cell
        for (int i = 0; i < 8;i++) {
            for (int j = 0; j < 8; j++) {
                int color = ((i + j) % 2 == 0) ? 255 : 209;
                fill(color, (i + j) % 2 == 0 ? 206 : 139, (i + j) % 2 == 0 ? 158 : 71);
                rect(i * 100 + 100, j * 100 + 40, 100, 100);
            }
        }
        // Draw the pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[j][i] != null) {
                    image(gameBoard[j][i], i * 100 + 105, j * 100 +45);
                }
            }
        }
// Display Features wie Score
        fill(255,206,158);
        rect(65,860,875,120);

        fill(0,0,0);
        textSize(50);
        text("Score !", 100, 920);

    }
    public void keyPressed() {
        if (key == 's') {
            dameSpielController.userInput();
        }
        if (key == 'r') {
            dameSpielController.restartGame();
        }
        if (key == 'q') {
            dameSpielController.exitGame();
        }

    }
    public void mousePressed() {
        move = true;

       if (mouseX > 100 && mouseX < 900 && mouseY > 40 && mouseY < 840) {
           if (onClick) {
               xPosTo = round( mouseY / 100  ) -1;
               yPosTo = round( mouseX / 100  ) -1;
               // the piece should move to the next position



               onClick = false;

           }

           else {
               this.xPos = round( mouseY / 100  ) -1;
               this.yPos = round( mouseX / 100 )  -1;
               fill(255, 0, 0, 100);
               rect(this.yPos*100+100,this.xPos*100 +40 , 100, 100);
               System.out.println("xPos: " + this.xPos + " yPos: " + this.yPos);
               dameSpielController.mousePos(this.xPos,this.yPos);

               onClick = true;
              }

       }
    }



    @Override
    public void drawGame() {
         onStartBoard();
        // here must event from Modell like movePiece() be called etc... So the game logic
    // select a piece and move it
        if (onClick) {


        }



    }



    /**
     *
     */
    @Override
    public void drawTitleScreen() {
        background(255);
        image(startScreen, 0, 0, 1000, 1000);


    }

    /**
     *
     */
    @Override
    public void drawGameOver() {
        background(0);
        textSize(70);
        text("Game Over !", 350, 300);
        text("Player 1 won", 350, 450);

        textSize(45);
        text(" Press r to restart or q to exit", 280, 650);


    }

    /**
     * @param xto
     * @param yto
     */
    @Override
    public void drawCanMoveTo(int xto, int yto) {
        if (move){
            drawGame();
            move = false;
        }
        fill(0, 255,0, 100);
        rect(yto*100+100,xto*100 +40 , 100, 100);
        System.out.println("xPossss: " + xto + " yPos: " + yto);
    }








    public static void main(String[] args){
            PApplet.main(DameSpielView.class);
        }
}
