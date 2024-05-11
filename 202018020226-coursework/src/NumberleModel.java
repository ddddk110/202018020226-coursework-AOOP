import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NumberleModel extends Observable implements INumberleModel {
    private ArrayList<ArrayList> returnList;
    private ArrayList<String> targetList;
    private int remainingAttempts;
    private boolean gameWon;

    @Override
    public String initialize() {
        Random rand = new Random();
        returnList = new ArrayList<>();
        targetList = new ArrayList<>();
        ArrayList<String> allTargetList = new ArrayList<>();

//        Read equation.txt, get target equation
        try {
            String path = "./equations.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String str;
            while (bufferedReader.readLine()!=null){
                str = bufferedReader.readLine();
                allTargetList.add(str);
            }
        }catch (IOException e){
            System.out.println("file error: "+ e.getMessage());
        }
        int targetNum = rand.nextInt(allTargetList.size());
        System.out.println("target: "+allTargetList.get(targetNum));
        for (int i = 0;i < allTargetList.get(targetNum).length();i++){
            String sub = allTargetList.get(targetNum).substring(i,i+1);
            targetList.add(sub);
        }


        remainingAttempts = MAX_ATTEMPTS;
        gameWon = false;
        setChanged();
        notifyObservers();

        return allTargetList.get(targetNum);
    }

    @Override
    public boolean processInput(String input) {
//        System.out.println("input: "+input);
        ArrayList<String> currentGuessList = new ArrayList<>();

//        Judge valid equation
        int intNum = 0;
        int symbolNum = 0;
        int equalSymbolFlag = 0;
        int equalSymbolIndex = 0;
        if (this.judgeValid(currentGuessList,input, intNum, symbolNum, equalSymbolFlag, equalSymbolIndex)){
            return true;
        }
        this.result(currentGuessList, targetList);

        remainingAttempts--;
        setChanged();
        notifyObservers();

        return false;
    }

    @Override
    public boolean isGameOver() {

        return remainingAttempts < 0;
    }

    @Override
    public boolean isGameWon() {
        return gameWon;
    }


    @Override
    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    @Override
    public void startNewGame() {
        initialize();
    }

    @Override
    public ArrayList<ArrayList> getReturnList(){return returnList;}

    @Override
    public boolean judgeValid(ArrayList<String> currentGuessList,String input,int intNum, int symbolNum, int equalSymbolFlag,int equalSymbolIndex){
//        System.out.println("input: "+input);
        for (int i = 0; i < input.length(); i++) {
            String sub = input.substring(i,i+1);
            currentGuessList.add(sub);
            for (int j = 0; j < 10; j++) {
                if (Integer.toString(j).equals(sub)){
                    intNum++;
                }
            }
            if (sub.equals("+")||sub.equals("-")||sub.equals("*")||sub.equals("/")){
                symbolNum++;
            }
            if (sub.equals("=")){
                equalSymbolFlag++;
            }
            if (i == 0) {//first must be number not symbol
                if (symbolNum>0){
//                    System.out.println("111");
                    return true;
                }
            } else if (i>0){
                if (i%2!=0){//1,3,5 position must be symbol
                    if (!sub.equals("=")&&!sub.equals("+")&&!sub.equals("-")&&!sub.equals("*")&&!sub.equals("/")){
//                        System.out.println("222");
                        return true;
                    }
                }else if (i%2==0){//0,2,4,6 position must be number
                    if (sub.equals("=")||sub.equals("+")||sub.equals("-")||sub.equals("*")||sub.equals("/")){
                        System.out.println("333");
                        return true;
                    }
                }
            }
            if (sub.equals("=")){
                equalSymbolIndex = i;
            }
        }
        if (intNum!=4||symbolNum!=2||equalSymbolFlag!=1||currentGuessList.size()!=7){//equation has 4 number, 2 symbol except "=", 1 "=", and total of number and symbol is 7
//            System.out.println("444");
            return true;
        }

        if (equalSymbolIndex==1){
            if (!this.calcLeft(currentGuessList, equalSymbolIndex)){
//                System.out.println("555");
                return true;
            }
        }else if (equalSymbolIndex==5){
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(currentGuessList.get(6));
            tempList.add(currentGuessList.get(5));
            for (int i=0;i<5;i++){
                tempList.add(currentGuessList.get(i));
            }
            if (!this.calcLeft(tempList,equalSymbolIndex)){
//                System.out.println("666");
                return true;
            }
        }else if (equalSymbolIndex==3){
            if (!this.calcMiddle(currentGuessList,equalSymbolIndex)){
//                System.out.println("777");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean calcLeft(ArrayList<String> currentGuessList, int equalSymbolIndex){
//        System.out.println(equalSymbolIndex);
        int equationLeft = Integer.parseInt(currentGuessList.get(0));
        int equationRight = 0;
        if (currentGuessList.get(3).equals("*")){
            equationRight = Integer.parseInt(currentGuessList.get(2)) * Integer.parseInt(currentGuessList.get(4));
            if (currentGuessList.get(5).equals("+")){
                equationRight = equationRight + Integer.parseInt(currentGuessList.get(6));
            }else if (currentGuessList.get(5).equals("-")){
                equationRight = equationRight - Integer.parseInt(currentGuessList.get(6));
            }else if (currentGuessList.get(5).equals("*")){
                equationRight = equationRight * Integer.parseInt(currentGuessList.get(6));
            }else if (currentGuessList.get(5).equals("/")){
                equationRight = equationRight / Integer.parseInt(currentGuessList.get(6));
            }
        }else if (currentGuessList.get(3).equals("/")){
            equationRight = Integer.parseInt(currentGuessList.get(2)) / Integer.parseInt(currentGuessList.get(4));
            if (currentGuessList.get(5).equals("+")){
                equationRight = equationRight + Integer.parseInt(currentGuessList.get(6));
            }else if (currentGuessList.get(5).equals("-")){
                equationRight = equationRight - Integer.parseInt(currentGuessList.get(6));
            }else if (currentGuessList.get(5).equals("*")){
                equationRight = equationRight * Integer.parseInt(currentGuessList.get(6));
            }else if (currentGuessList.get(5).equals("/")){
                equationRight = equationRight / Integer.parseInt(currentGuessList.get(6));
            }
        }else if (currentGuessList.get(3).equals("+")&&currentGuessList.get(5).equals("*")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) * Integer.parseInt(currentGuessList.get(6));
            equationRight = Integer.parseInt(currentGuessList.get(2)) + equationRight;
        }else if (currentGuessList.get(3).equals("+")&&currentGuessList.get(5).equals("/")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) / Integer.parseInt(currentGuessList.get(6));
            equationRight = Integer.parseInt(currentGuessList.get(2)) + equationRight;
        }else if (currentGuessList.get(3).equals("-")&&currentGuessList.get(5).equals("*")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) * Integer.parseInt(currentGuessList.get(6));
            equationRight = Integer.parseInt(currentGuessList.get(2)) - equationRight;
        }else if (currentGuessList.get(3).equals("-")&&currentGuessList.get(5).equals("/")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) / Integer.parseInt(currentGuessList.get(6));
            equationRight = Integer.parseInt(currentGuessList.get(2)) - equationRight;
        }else if (currentGuessList.get(3).equals("+")&&currentGuessList.get(5).equals("+")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) + Integer.parseInt(currentGuessList.get(6));
            equationRight = Integer.parseInt(currentGuessList.get(2)) + equationRight;
        }else if (currentGuessList.get(3).equals("+")&&currentGuessList.get(5).equals("-")){
            equationRight = Integer.parseInt(currentGuessList.get(2)) + Integer.parseInt(currentGuessList.get(4));
            equationRight = equationRight - Integer.parseInt(currentGuessList.get(6));
        } else if (currentGuessList.get(3).equals("-")&&currentGuessList.get(5).equals("-")){
            equationRight = Integer.parseInt(currentGuessList.get(2)) - Integer.parseInt(currentGuessList.get(4));
            equationRight = equationRight - Integer.parseInt(currentGuessList.get(6));
        }else if (currentGuessList.get(3).equals("-")&&currentGuessList.get(5).equals("+")){
            equationRight = Integer.parseInt(currentGuessList.get(2)) - Integer.parseInt(currentGuessList.get(4));
            equationRight = equationRight + Integer.parseInt(currentGuessList.get(6));
        }

        if (equationLeft == equationRight){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean calcMiddle(ArrayList<String> currentGuessList, int equalSymbolIndex){
        int equationLeft = 0;
        int equationRight =0;
        if (currentGuessList.get(1).equals("+")){
            equationLeft = Integer.parseInt(currentGuessList.get(0)) + Integer.parseInt(currentGuessList.get(2));
        }else if (currentGuessList.get(1).equals("-")){
            equationLeft = Integer.parseInt(currentGuessList.get(0)) - Integer.parseInt(currentGuessList.get(2));
        }else if (currentGuessList.get(1).equals("*")){
            equationLeft = Integer.parseInt(currentGuessList.get(0)) * Integer.parseInt(currentGuessList.get(2));
        }else if (currentGuessList.get(1).equals("/")){
            equationLeft = Integer.parseInt(currentGuessList.get(0)) / Integer.parseInt(currentGuessList.get(2));
        }

        if (currentGuessList.get(5).equals("+")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) + Integer.parseInt(currentGuessList.get(6));
        }else if (currentGuessList.get(5).equals("-")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) - Integer.parseInt(currentGuessList.get(6));
        }else if (currentGuessList.get(5).equals("*")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) * Integer.parseInt(currentGuessList.get(6));
        }else if (currentGuessList.get(5).equals("/")){
            equationRight = Integer.parseInt(currentGuessList.get(4)) / Integer.parseInt(currentGuessList.get(6));
        }

        if (equationLeft==equationRight){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void result(ArrayList<String> currentGuessList, ArrayList<String> targetList){
        ArrayList<String> exisOrangetList = new ArrayList<>();
        ArrayList<String> tureGreenList = new ArrayList<>();
        ArrayList<String> notExistGreyList = new ArrayList<>();
        int size = currentGuessList.size();
        int initIndex = size - 7;
        int score = 0;
        for (int i = 0; i < 7; i++){
            for (int j = initIndex; j < initIndex+7; j++) {
                if (currentGuessList.get(j).equals(targetList.get(i)) && i==j-initIndex){
                    int already=0;
                    for (String string: tureGreenList){
                        if (string.equals(currentGuessList.get(j))){
                            already++;
                        }
                    }
                    if (already==0){
                        tureGreenList.add(currentGuessList.get(j));
                    }

//                    tureGreenList.add(currentGuessList.get(j));
                    score++;
                } else if (currentGuessList.get(j).equals(targetList.get(i)) && i!=j-initIndex) {
                    int already=0;
                    for (String string:exisOrangetList){
                        if (string.equals(currentGuessList.get(j))){
                            already++;
                        }
                    }
                    if (already==0){
                        exisOrangetList.add(currentGuessList.get(j));
                    }
//                    exisOrangetList.add(currentGuessList.get(j));
                }else {
                    int already=0;
                    for (String string:notExistGreyList){
                        if (string.equals(currentGuessList.get(j))){
                            already++;
                        }
                    }
                    if (already==0){
                        notExistGreyList.add(currentGuessList.get(j));
                    }
//                    notExistGreyList.add(currentGuessList.get(j));
                }
            }
        }
        for (String string1:tureGreenList){
            for (int i=exisOrangetList.size()-1;i>=0;i--){
                if (string1.equals(exisOrangetList.get(i))){
                    exisOrangetList.remove(i);
                }
            }
        }
        for (String string1:tureGreenList){
            for (int i=notExistGreyList.size()-1;i>=0;i--){
                if (string1.equals(notExistGreyList.get(i))){
                    notExistGreyList.remove(i);
                }
            }
        }
        for (String string1:exisOrangetList){
            for (int i=notExistGreyList.size()-1;i>=0;i--){
                if (string1.equals(notExistGreyList.get(i))){
                    notExistGreyList.remove(i);
                }
            }
        }
        this.returnList.clear();//8-6/2=5   8-6/3=6
        returnList.add(notExistGreyList);//3
        returnList.add(exisOrangetList);//
        returnList.add(tureGreenList);//8,-,6,/,=

        if (score==7){
            gameWon=true;
        }
    }

    //remainingAttempts > 0 and game not won, remainingAttempts > 0 and game won, remainingAttempts < 0 and game lost
    @Override
    public boolean invariant(){
        return (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
    }
}
