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

        BLACK_PIECE.resize(100, 100);
        WHITE_PIECE.resize(100, 100);
        BLACK_QUEEN.resize(100, 100);
        WHITE_QUEEN.resize(100, 100);
    }
    public void draw() {
        dameSpielController.nextFrame();
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
        dameSpielController.userInput(mouseX, mouseY);

    }







    @Override
    public void onStartBoard() {
        background(0);

        // Draw the cell
        for (int i = 0; i < 8;i++) {
            for (int j = 0; j < 8; j++) {
                int color = ((i + j) % 2 == 0) ? 255 : 209;
                fill(color, (i + j) % 2 == 0 ? 206 : 139, (i + j) % 2 == 0 ? 158 : 71);
                rect(i * 100 + 100, j * 100 + 40, 100, 100);
            }
        }


// Display Features wie Score
        fill(255,206,158);
        rect(65,850,875,120);

        fill(0,0,0);
        textSize(50);
        text("Score !", 100, 920);
    }


    /**
     *
     */
    @Override
    public void drawGame() {
         onStartBoard();


        // here must event from Modell like movePiece() be called etc... So the game logik

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
    public void drawBlackPiece(int xPos, int yPos) {
        image(BLACK_PIECE, xPos, yPos);
    }
    public void drawWhitePiece(int xPos, int yPos) {
        image(WHITE_PIECE, xPos, yPos);

    }

    /**
     * @param i
     * @param j
     */
    @Override
    public void drawBlackQueen(int i, int j) {
        image(BLACK_QUEEN, i, j);

    }

    /**
     * @param i
     * @param j
     */
    @Override
    public void drawWhiteQueen(int i, int j) {
        image(WHITE_QUEEN, i, j);


    }

    /**
     * @param i
     * @param j
     */
    @Override
    public void drawEmptyCell(int i, int j) {

    }

    public static void main(String[] args) {
        PApplet.main(DameSpielView.class);
    }
}
