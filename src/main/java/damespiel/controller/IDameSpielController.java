package damespiel.controller;


public interface IDameSpielController {
    public void nextFrame();
    public void userInput();

    void userInput(int xPosFrom, int yPosFrom);

    public void restartGame();

    public void exitGame();
}
