import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class NumberleView implements Observer {
    private final INumberleModel model;
    private final NumberleController controller;
    private final JFrame frame = new JFrame("Numberle");
    private final JTextField inputTextField = new JTextField(1);
    private JTextField[][] outputFields;
    private final JLabel attemptsLabel = new JLabel("Attempts remaining: ");
    private HashMap<String, JButton> buttonsList;
    private JPanel center;

    public NumberleView(INumberleModel model, NumberleController controller) {
        this.controller = controller;
        this.model = model;
        this.controller.startNewGame();
        ((NumberleModel)this.model).addObserver(this);
        initializeFrame();
        this.controller.setView(this);
        update((NumberleModel)this.model, null);
    }

    public void initializeFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLayout(new BorderLayout());

//        JPanel north = new JPanel();
//        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
//        center.add(new JPanel());

//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(new GridLayout(3, 1));

        JPanel attemptPanel = new JPanel();
        attemptPanel.setLayout(new GridLayout(1,1));

//        inputPanel.add(inputTextField);
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(7, 7));

        outputFields = new JTextField[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JTextField outputField = new JTextField(1);
                outputField.setHorizontalAlignment(JTextField.CENTER);
                outputField.setPreferredSize(new Dimension(100, 50));
                outputFields[i][j] = outputField;
                outputPanel.add(outputField);
            }
        }
//        System.out.println(outputFields[1].length);

        JButton submitButton = new JButton("Enter");
        submitButton.addActionListener(e -> {
            String input = "";
            int firstIndex = outputFields.length-1-controller.getRemainingAttempts();
            if (outputFields[firstIndex].length!=7){
                JOptionPane.showMessageDialog(null, "It's not an equation");
            }
            for (int i=0;i<outputFields[firstIndex].length;i++){
//                System.out.println(outputFields[firstIndex][i].getText());
                input = input.concat(outputFields[firstIndex][i].getText());
            }
            System.out.println(input);
            boolean flag = controller.processInput(input);
            if (flag){
                JOptionPane.showMessageDialog(null, "It's not an equation");
            }
            inputTextField.setText("");
        });
        submitButton.setPreferredSize(new Dimension(100,50));

        attemptsLabel.setText("Attempts remaining: " + controller.getRemainingAttempts());
        attemptPanel.add(attemptsLabel);
        center.add(outputPanel);

        frame.add(center, BorderLayout.NORTH);


        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new BoxLayout(keyboardPanel, BoxLayout.X_AXIS));
        keyboardPanel.add(new JPanel());
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(2, 5));
        keyboardPanel.add(numberPanel);

        buttonsList = new HashMap<>();

        for (int i = 0; i < 10; i++) {

            JButton button = new JButton(Integer.toString(i));
            button.setEnabled(true);
            button.addActionListener(e -> {
//                inputTextField.setText(inputTextField.getText() + button.getText());
                int firstIndex = outputFields.length-1-controller.getRemainingAttempts();
                for (int j=0;j<outputFields[firstIndex].length;j++){
//                    System.out.println(outputFields[firstIndex][j].getText());
                    if (outputFields[firstIndex][j].getText().equals("")){
//                        System.out.println("firstIndex: "+firstIndex+"second: "+j);
                        outputFields[firstIndex][j].setText(button.getText());
                        break;
                    }
                }
            });
            button.setPreferredSize(new Dimension(50, 50));
            button.setBackground(Color.WHITE);
//            button.setForeground(Color.black);
            buttonsList.put(Integer.toString(i),button);
            numberPanel.add(button);
        }

        JButton addButton = new JButton("+");
        addButton.setPreferredSize(new Dimension(50, 50));
        addButton.addActionListener(e -> {
//            inputTextField.setText(inputTextField.getText() + addButton.getText());
            int firstIndex = outputFields.length-1-controller.getRemainingAttempts();
            for (int j=0;j<outputFields[firstIndex].length;j++){
                if (outputFields[firstIndex][j].getText().equals("")){
                    outputFields[firstIndex][j].setText(addButton.getText());
                    break;
                }
            }
        });
        buttonsList.put("+",addButton);
        addButton.setBackground(Color.WHITE);
        numberPanel.add(addButton);

        JButton subtractButton = new JButton("-");
        subtractButton.setPreferredSize(new Dimension(50, 50));
        subtractButton.addActionListener(e -> {
//            inputTextField.setText(inputTextField.getText() + subtractButton.getText());
            int firstIndex = outputFields.length-1-controller.getRemainingAttempts();
            for (int j=0;j<outputFields[firstIndex].length;j++){
                if (outputFields[firstIndex][j].getText().equals("")){
                    outputFields[firstIndex][j].setText(subtractButton.getText());
                    break;
                }
            }
        });
        buttonsList.put("-",subtractButton);
        subtractButton.setBackground(Color.WHITE);
        numberPanel.add(subtractButton);

        JButton multiplyButton = new JButton("*");
        multiplyButton.setPreferredSize(new Dimension(50, 50));
        multiplyButton.addActionListener(e -> {
//            inputTextField.setText(inputTextField.getText() + multiplyButton.getText());
            int firstIndex = outputFields.length-1-controller.getRemainingAttempts();
            for (int j=0;j<outputFields[firstIndex].length;j++){
                if (outputFields[firstIndex][j].getText().equals("")){
                    outputFields[firstIndex][j].setText(multiplyButton.getText());
                    break;
                }
            }
        });
        buttonsList.put("*",multiplyButton);
        multiplyButton.setBackground(Color.WHITE);
        numberPanel.add(multiplyButton);

//        JButton divideButton = new JButton("÷");
        JButton divideButton = new JButton("/");
        divideButton.setPreferredSize(new Dimension(50, 50));
        divideButton.addActionListener(e -> {
//            inputTextField.setText(inputTextField.getText() + divideButton.getText());
            int firstIndex = outputFields.length-1-controller.getRemainingAttempts();
            for (int j=0;j<outputFields[firstIndex].length;j++){
                if (outputFields[firstIndex][j].getText().equals("")){
                    outputFields[firstIndex][j].setText(divideButton.getText());
                    break;
                }
            }
        });
        buttonsList.put("/",divideButton);
        divideButton.setBackground(Color.WHITE);
        numberPanel.add(divideButton);

        JButton equalButton = new JButton("=");
        equalButton.setPreferredSize(new Dimension(50, 50));
        equalButton.addActionListener(e -> {
//            inputTextField.setText(inputTextField.getText() + equalButton.getText());
            int firstIndex = outputFields.length-1-controller.getRemainingAttempts();
            for (int j=0;j<outputFields[firstIndex].length;j++){
                if (outputFields[firstIndex][j].getText().equals("")){
                    outputFields[firstIndex][j].setText(equalButton.getText());
                    break;
                }
            }
        });
        buttonsList.put("=",equalButton);
        equalButton.setBackground(Color.WHITE);
        numberPanel.add(equalButton);


        JButton backButton = new JButton("BackSpace");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.addActionListener(e -> {
//            if (inputTextField.getText().length()>1){
//                inputTextField.setText(inputTextField.getText().substring(0,inputTextField.getText().length()-1));
//            }else {
//                inputTextField.setText("");
//            }
            int firstIndex = outputFields.length-1-controller.getRemainingAttempts();
            for (int i=6;i>=0;i--){
                if (!outputFields[firstIndex][i].getText().equals("")){
                    outputFields[firstIndex][i].setText("");
                    break;
                }
            }
        });
//        buttonsList.put("=",equalButton);
        backButton.setBackground(Color.WHITE);
        numberPanel.add(backButton);
        numberPanel.add(submitButton);
        numberPanel.add(attemptPanel);
//        keyboardPanel.add(new JPanel());

        frame.add(keyboardPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
//        for (String i : buttonsList.keySet()) {
//            System.out.println("key: " + i + " value: " + buttonsList.get(i));
//        }
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ArrayList> returnList;
        returnList = controller.getReturnList();
        ArrayList<String> notExistGreyList;
        notExistGreyList = returnList.get(0);
        ArrayList<String> exisOrangetList;
        exisOrangetList = returnList.get(1);
        ArrayList<String> tureGreenList;
        tureGreenList = returnList.get(2);
        for (String i: notExistGreyList){
            buttonsList.get(i).setBackground(Color.GRAY);
        }
        for (String i: exisOrangetList){
            buttonsList.get(i).setBackground(Color.ORANGE);
        }
        for (String i: tureGreenList){
            buttonsList.get(i).setBackground(Color.GREEN);
        }
        attemptsLabel.setText("Attempts remaining: " + controller.getRemainingAttempts());
        if (controller.isGameWon()){
            JPanel gameOver = new JPanel();
            gameOver.setLayout(new GridLayout(1, 1));
            JButton restartButton = new JButton("Yon won! Do you want to Restart?");
            restartButton.addActionListener(e -> {
                controller.startNewGame();
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                initializeFrame();
            });
            gameOver.add(restartButton);
            center.add(gameOver);
        }
        if (controller.isGameOver()){
            JPanel gameOver = new JPanel();
            gameOver.setLayout(new GridLayout(1, 1));
            JButton restartButton = new JButton("Yon lost! Do you want to Restart?");
            restartButton.addActionListener(e -> {
                controller.startNewGame();
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                initializeFrame();
            });
            gameOver.add(restartButton);
            center.add(gameOver);
        }
    }
}