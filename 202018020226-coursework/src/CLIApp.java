import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class CLIApp {
    private String[][] userInputList = new String[7][7];
    private final INumberleModel model;

    public CLIApp(INumberleModel model) {
        this.model = model;
        this.model.startNewGame();
        roundProcess();
//        this.model.setCliApp(this);

    }

    public static void main(String[] args) {
        initRun();
    }
    public static void initRun(){
        //get target equation
        INumberleModel model = new NumberleModel();
        NumberleController controller = new NumberleController(model);
        CLIApp cliApp = new CLIApp(model);

    }

    public void roundProcess(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Attempts remaining: "+model.getRemainingAttempts());
        int firstIndex = userInputList.length-model.getRemainingAttempts()-1;
//        System.out.println(userInputList.length);
        for (int i=0;i<userInputList[firstIndex].length;i++){
            if (i%2==0){
                System.out.print("Please input a number 0-9: ");
                int userInputNum = 0;
                while (true){
                    if (scan.hasNextInt()){
                        userInputNum = scan.nextInt();
                        if (userInputNum>9||userInputNum<0){
                            System.out.print("The number is not valid! Please input a number 0-9: ");
                        }else {
                            userInputList[firstIndex][i] = Integer.toString(userInputNum);
                            break;
                        }
                    }else {
                        System.out.println("The number is not valid! Please input a number 0-9: ");
                        scan.next();
                    }
                }
            }else {
                System.out.print("Please input +, -, *, /, =: ");
                String userInputSign = "";
                while (scan.hasNext()){
                    userInputSign = scan.next();
                    if (userInputSign.equals("+")||userInputSign.equals("-")||userInputSign.equals("*")||userInputSign.equals("/")||userInputSign.equals("=")){
                        userInputList[firstIndex][i] = userInputSign;
                        break;
                    }else {
                        System.out.print("The number is not valid! Please input +, -, *, /, =: ");
                    }
                }
            }
            String backSign = "";
            System.out.print("Do you want to delete? Please input true(t) or false(f): ");
            while (scan.hasNext()){
                backSign = scan.next();
                if (backSign.equals("true")||backSign.equals("t")){
                    userInputList[firstIndex][i] = "";
                    i--;
                    if (i>0){
                        System.out.print("Do you want to delete more? Please input true(t) or false(f): ");
                    }else {
                        break;
                    }
                } else if (backSign.equals("false")||backSign.equals("f")) {
                    break;
                }else {
                    System.out.print("Not valid word! Please input true(t) or false(f): ");
                }
            }
        }
        String input = "";
        System.out.println("This is your: "+(firstIndex+1)+" try. And your input is: ");
        for (int i=0;i< userInputList[firstIndex].length;i++){
            input = input.concat(userInputList[firstIndex][i]);
            System.out.print(userInputList[firstIndex][i]);
        }
        boolean flag = model.processInput(input);
        if (flag){
            System.out.println("It's not an equation!");
            roundProcess();
        }
        ArrayList<ArrayList> returnList = new ArrayList<>();
        returnList = model.getReturnList();
        ArrayList<String> notExistGreyList = new ArrayList<>();
        notExistGreyList = returnList.get(0);
        System.out.println("");
        System.out.println("Not exist in equation: ");
        for (String i:notExistGreyList){
            System.out.print(i+", ");
        }
        ArrayList<String> exisOrangetList = new ArrayList<>();
        exisOrangetList = returnList.get(1);
        System.out.println("");
        System.out.println("Exist in equation but not in correct position: ");
        for (String i:exisOrangetList){
            System.out.print(i+", ");
        }
        ArrayList<String> tureGreenList = new ArrayList<>();
        tureGreenList = returnList.get(2);
        System.out.println("");
        System.out.println("Exist in equation and in correct position: ");
        for (String i:tureGreenList){
            System.out.print(i+", ");
        }
        if (model.isGameWon()){
            System.out.println("You won! Do you want to restart? Please input true(t) or false(f): ");
            String restartSign = "";
            while (scan.hasNext()){
                restartSign = scan.next();
                if (restartSign.equals("true")||restartSign.equals("t")){
                    model.startNewGame();
                    roundProcess();
                    userInputList = new String[7][7];
                    break;
                }else if (restartSign.equals("false")||restartSign.equals("f")){
                    return;
                }else {
                    System.out.print("Not valid word! Please input true(t) or false(f): ");
                }
            }
        }else if(model.isGameOver()){
            System.out.print("You lost! Do you want to restart? Please input true(t) or false(f): ");
            String restartSign = "";
            while (scan.hasNext()){
                restartSign = scan.next();
                if (restartSign.equals("true")||restartSign.equals("t")){
                    model.startNewGame();
                    roundProcess();
                    userInputList = new String[7][7];
                    break;
                }else if (restartSign.equals("false")||restartSign.equals("f")){
                    return;
                }else {
                    System.out.print("Not valid word! Please input true(t) or false(f): ");
                }
            }
        }else {
            System.out.println("");
            roundProcess();
        }

    }
}
