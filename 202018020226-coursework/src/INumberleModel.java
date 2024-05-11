import java.util.ArrayList;

public interface INumberleModel {
    int MAX_ATTEMPTS = 6;

    String initialize();
    boolean processInput(String input);
    boolean isGameOver();
    boolean isGameWon();

    int getRemainingAttempts();
    void startNewGame();
    ArrayList<ArrayList> getReturnList();
    boolean calcLeft(ArrayList<String> currentGuessList, int equalSymbolIndex);
    boolean calcMiddle(ArrayList<String> currentGuessList, int equalSymbolIndex);
    boolean judgeValid(ArrayList<String> currentGuessList,String input,int intNum, int symbolNum, int equalSymbolFlag,int equalSymbolIndex);
    void result(ArrayList<String> currentGuessList, ArrayList<String> targetList);
    boolean invariant();
}