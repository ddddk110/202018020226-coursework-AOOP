import java.util.ArrayList;


public class NumberleController {
    private INumberleModel model;
    private NumberleView view;
//    private CLIApp cliApp;

    public NumberleController(INumberleModel model) {
        this.model = model;
    }

    public void setView(NumberleView view) {
        this.view = view;
    }

//    public void setCliApp(CLIApp cliApp){
//        this.cliApp = cliApp;
//    }
    public boolean processInput(String input) {
        return model.processInput(input);
    }

    public boolean isGameOver() {
        return model.isGameOver();
    }

    public boolean isGameWon() {
        return model.isGameWon();
    }

    public int getRemainingAttempts() {
        return model.getRemainingAttempts();
    }

    public void startNewGame() {
        model.startNewGame();
    }
    public ArrayList<ArrayList> getReturnList(){return model.getReturnList();}
}