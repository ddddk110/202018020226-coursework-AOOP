import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NumberleModelTest {
    NumberleModel numberleModel;

    @BeforeEach
    void setUp() {numberleModel = new NumberleModel();}

    @AfterEach
    void tearDown() {numberleModel = null;}


    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures The equation is valid then return false, else return ture.
     */
    @Test
    void judgeValid1() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "3+2-1=4";
        numberleModel.initialize();
        assertFalse(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures The first position in equation can not be symbol, it is invalid, return ture.
     */
    @Test
    void judgeValid2() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "+3+3+=6";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures The index of 1,3,5 in equation is not symbol, it is invalid, return ture.
     */
    @Test
    void judgeValid3() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "12345=6";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures The index of 0,2,4,6 in equation is not integer, it is invalid, return ture.
     */
    @Test
    void judgeValid4() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "+1+2+3=";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the equation contains 4 integer it is valid then return false, else return true.
     */
    @Test
    void judgeValid5() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "1234567";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the equation contains 2 symbols except "=" it is valid then return false, else return true.
     */
    @Test
    void judgeValid6() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "+++++++";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the equation only contains 1 "=" it is valid then return false, else return true.
     */
    @Test
    void judgeValid7() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "1+2+3+4";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the length of equation is 7 it is valid then return false, else return true.
     */
    @Test
    void judgeValid9() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "1+2+3+4+5";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the left in equation equal to right in equation and index of "=" is 1 it is valid then return false.
     */
    @Test
    void judgeValid10() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "6=1+2+3";
        numberleModel.initialize();
        assertFalse(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the left in equation not equal to right in equation and index of "=" is 1 it is invalid then return true.
     */
    @Test
    void judgeValid11() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "6=1+2+5";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the left in equation equal to right in equation and index of "=" is 5 it is valid then return false.
     */
    @Test
    void judgeValid12() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "1+2+3=6";
        numberleModel.initialize();
        assertFalse(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the left in equation not equal to right in equation and index of "=" is 5 it is invalid then return true.
     */
    @Test
    void judgeValid13() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "1+2+5=6";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the left in equation equal to right in equation and index of "=" is 3 it is valid then return false.
     */
    @Test
    void judgeValid14() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "2+4=9-3";
        numberleModel.initialize();
        assertFalse(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires an equation which is String and from user input.
     * @ensures If the left in equation not equal to right in equation and index of "=" is 3 it is invalid then return true.
     */
    @Test
    void judgeValid15() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "5+4=9-3";
        numberleModel.initialize();
        assertTrue(numberleModel.judgeValid(currentGuessList,input, 0, 0, 0, 0));
        assertTrue(numberleModel.invariant());
    }


    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires An array contains the equations entered by the user.
     * @ensures If the left in equation equal to right in equation and index of "=" is 1 it is valid then return true.
     */
    @Test
    void calcLeft1() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "6=1+2+3";
        for (int i = 0; i < input.length(); i++) {
            String sub = input.substring(i, i + 1);
            currentGuessList.add(sub);
        }
        numberleModel.initialize();
        assertTrue(numberleModel.calcLeft(currentGuessList,3));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires An array contains the equations entered by the user.
     * @ensures If the left in equation not equal to right in equation and index of "=" is 1 it is invalid then return false.
     */
    @Test
    void calcLeft2() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "6=1+2+5";
        for (int i = 0; i < input.length(); i++) {
            String sub = input.substring(i, i + 1);
            currentGuessList.add(sub);
        }
        numberleModel.initialize();
        assertFalse(numberleModel.calcLeft(currentGuessList,3));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires An array contains the equations entered by the user, and change the left in equation with the right in equation, now the index of "=" is 1.
     * @ensures If the left in equation equal to right in equation and index of "=" is 1 it is valid then return true.
     */
    @Test
    void calcLeft3() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "1+2+3=6";
        for (int i = 0; i < input.length(); i++) {
            String sub = input.substring(i, i + 1);
            currentGuessList.add(sub);
        }
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add(currentGuessList.get(6));
        tempList.add(currentGuessList.get(5));
        for (int i=0;i<5;i++){
            tempList.add(currentGuessList.get(i));
        }
        numberleModel.initialize();
        assertTrue(numberleModel.calcLeft(tempList,3));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires An array contains the equations entered by the user, and change the left in equation with the right in equation, now the index of "=" is 1.
     * @ensures If the left in equation not equal to right in equation and index of "=" is 1 it is invalid then return flase.
     */
    @Test
    void calcLeft4() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "1+2+5=6";
        for (int i = 0; i < input.length(); i++) {
            String sub = input.substring(i, i + 1);
            currentGuessList.add(sub);
        }
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add(currentGuessList.get(6));
        tempList.add(currentGuessList.get(5));
        for (int i=0;i<5;i++){
            tempList.add(currentGuessList.get(i));
        }
        numberleModel.initialize();
        assertFalse(numberleModel.calcLeft(tempList,3));
        assertTrue(numberleModel.invariant());
    }


    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires An array contains the equations entered by the user.
     * @ensures If the left in equation equal to right in equation and index of "=" is 3 it is valid then return true.
     */
    @Test
    void calcMiddle1() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "2+4=9-3";
        for (int i = 0; i < input.length(); i++) {
            String sub = input.substring(i, i + 1);
            currentGuessList.add(sub);
        }
        numberleModel.initialize();
        assertTrue(numberleModel.calcMiddle(currentGuessList,3));
        assertTrue(numberleModel.invariant());
    }
    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires An array contains the equations entered by the user.
     * @ensures If the left in equation not equal to right in equation and index of "=" is 3 it is invalid then return false.
     */
    @Test
    void calcMiddle2() {
        ArrayList<String> currentGuessList = new ArrayList<>();
        String input = "5+4=9-3";
        for (int i = 0; i < input.length(); i++) {
            String sub = input.substring(i, i + 1);
            currentGuessList.add(sub);
        }
        numberleModel.initialize();
        assertFalse(numberleModel.calcMiddle(currentGuessList,3));
        assertTrue(numberleModel.invariant());
    }

    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires Arrays contain the equations entered by the user, and the correct answer. isGameOver == false.
     * @ensures If the remaining attempts < 0, and the user have not input right answer, the user lost the game. isGameOver return true.
     */
    @Test
    void GameLost(){
        numberleModel.initialize();
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        assertTrue(numberleModel.isGameOver());
        assertTrue(numberleModel.invariant());
    }

    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires Arrays contain the equations entered by the user, and the correct answer. isGameWon == false.
     * @ensures If the remaining attempts > 0, and the user input right answer, the user won the game. isGameWon return true.
     */
    @Test
    void GameWon(){
        String answer = numberleModel.initialize();
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput(answer);
        assertTrue(numberleModel.isGameWon());
        assertFalse(numberleModel.isGameOver());
        assertTrue(numberleModel.invariant());
    }

    /**
     * @invariant (remainingAttempts>0 && !gameWon) || (remainingAttempts>0 && gameWon) || (remainingAttempts < 0 && !gameWon);
     * @requires Arrays contain the equations entered by the user, and the correct answer. isGameWon == true.
     * @ensures If user won the game, user can start new game and the all parameters initialized. isGameWon == false.
     */
    @Test
    void StartNewGame(){
        String answer = numberleModel.initialize();
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput("1+2+3=6");
        numberleModel.processInput(answer);
        assertTrue(numberleModel.isGameWon());
        assertFalse(numberleModel.isGameOver());
        assertTrue(numberleModel.invariant());
        numberleModel.startNewGame();
        numberleModel.initialize();
        assertFalse(numberleModel.isGameOver());
        assertFalse(numberleModel.isGameWon());
        assertTrue(numberleModel.invariant());
    }
}