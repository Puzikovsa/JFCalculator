import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Calculator {
    private static JTextField inputArea;
    private static JTextField resaltArea;


    public static void main(String[] args) {

        JFrame calculator = new JFrame("Calculator");
        calculator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calculator.setSize(new Dimension(450, 700));
        calculator.setLocationRelativeTo(null);
        calculator.setLayout(new FlowLayout());

        JPanel resaltPanel = new JPanel(new FlowLayout());
        resaltPanel.setPreferredSize(new Dimension(400, 60));
        initresaltPanel(resaltPanel);
        calculator.add(resaltPanel);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setPreferredSize(new Dimension(400, 70));
        initInputPanel(inputPanel);
        calculator.add(inputPanel);


        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        buttonPanel.setPreferredSize(new Dimension(400, 400));
        initButtonPanel(buttonPanel);
        calculator.add(buttonPanel);

        calculator.setVisible(true);

    }

    private static void initresaltPanel(JPanel panel) {
        resaltArea = new JTextField();
        Font resaltFont = new Font(Font.SERIF, Font.PLAIN, 15);
        resaltArea.setFont(resaltFont);
        resaltArea.setPreferredSize(new Dimension(380, 40));
        panel.add(resaltArea);
    }

    private static void initInputPanel(JPanel panel) {
        inputArea = new JTextField(String.valueOf("0"));
        Font bigFontTR = new Font("Arial", Font.BOLD, 20);
        inputArea.setFont(bigFontTR);
        inputArea.setPreferredSize(new Dimension(380, 45));
        panel.add(inputArea);
    }


    private static void initButtonPanel(JPanel panel) {
        AtomicReference<String> intermediatText = new AtomicReference<>("");
        String[][] button = {
                {"7", "8", "9", "<<<"},
                {"4", "5", "6", "+"},
                {"1", "2", "3", "-"},
                {"0", "=", "*", "/"}
        };
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[i].length; j++) {
                JButton currentButton = new JButton(button[i][j]);
                Font bigFontTR = new Font("Arial", Font.BOLD, 20);
                currentButton.setFont(bigFontTR);
                currentButton.addActionListener(actionEvents -> {
                    String buttonText = actionEvents.getActionCommand();
                    String currentText = inputArea.getText();
                    String intermediateResult = resaltArea.getText();
                    String operation = inputArea.getText();;
                    int resalt;
                    switch (buttonText) {
                        case "<<<":
                            currentText = currentText.substring(0, currentText.length() - 1);
                            break;

                        case "+", "-", "*", "/":
//                            if(Objects.equals(intermediateResult, "")){
                                operation = buttonText;
                                intermediateResult = currentText;
                                intermediateResult = intermediateResult + buttonText;
                                currentText = "0";
                                intermediatText.set("");
                                break;
//                            } else {
//                                resalt = Integer.parseInt(intermediateResult);
//                            }

                        case "=":
                            int inermediantInt = Integer.parseInt(intermediateResult);
                            int inermediantInt1 = Integer.parseInt(currentText);
                            switch (operation) {
                                case "+" -> {
                                    resalt = inermediantInt + inermediantInt1;
                                    currentText = Integer.toString(resalt);
                                }
                                case "-" -> {
                                    resalt = inermediantInt - inermediantInt1;
                                    currentText = Integer.toString(resalt);
                                }
                                case "*" -> {
                                    resalt = inermediantInt * inermediantInt1;
                                    currentText = Integer.toString(resalt);
                                }
                                case "/" -> {
                                    resalt = inermediantInt / inermediantInt1;
                                    currentText = Integer.toString(resalt);
                                }
                            }
                            break;
                        case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9":
                            currentText = intermediatText.get();
                            currentText += buttonText;
                            intermediatText.set(currentText);
                            break;
                    }
                    resaltArea.setText(intermediateResult);
                    inputArea.setText(currentText);

                });
                panel.add(currentButton);
            }
        }
    }
}
