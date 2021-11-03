import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class Calculator {
    private static JTextField inputArea;
    private static JTextField resultArea;
    private static JTextField operationArea;

    public static void main(String[] args) {

        JFrame calculator = new JFrame("Calculator");
        calculator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calculator.setSize(new Dimension(400, 650));
        calculator.setLocationRelativeTo(null);
        calculator.setLayout(new FlowLayout());

        JPanel resaltPanel = new JPanel(new FlowLayout());
        resaltPanel.setPreferredSize(new Dimension(400, 40));
        initResaltPanel(resaltPanel);
        calculator.add(resaltPanel);

        JPanel operationPanel = new JPanel(new FlowLayout());
        operationPanel.setPreferredSize(new Dimension(400, 40));
        operationArea = new JTextField();
        initOperationPanel(operationPanel);
        calculator.add(operationPanel);


        JPanel inputPanel = new JPanel(new FlowLayout());
        inputArea = new JTextField("0");
        inputPanel.setPreferredSize(new Dimension(400, 70));
        initInputPanel(inputPanel);
        calculator.add(inputPanel);


        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        buttonPanel.setPreferredSize(new Dimension(350, 400));
        initButtonPanel(buttonPanel);
        calculator.add(buttonPanel);

        calculator.setVisible(true);

    }

    private static void initResaltPanel(JPanel panel) {
        resultArea = new JTextField();
        Font resaltFont = new Font("Arial", Font.PLAIN, 20);
        resultArea.setFont(resaltFont);
        resultArea.setPreferredSize(new Dimension(350, 40));
        panel.add(resultArea);
    }

    private static void initOperationPanel(JPanel panel) {
        Font operationFont = new Font(Font.SERIF, Font.PLAIN, 20);
        operationArea.setFont(operationFont);
        operationArea.setPreferredSize(new Dimension(350, 40));
        panel.add(operationArea);
    }

    private static void initInputPanel(JPanel panel) {
        inputArea = new JTextField("0");
        Font bigFontTR = new Font("Arial", Font.BOLD, 20);
        inputArea.setFont(bigFontTR);
        inputArea.setPreferredSize(new Dimension(350, 45));
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
                    String intermediateResult = resultArea.getText();
                    String operation = "";
                    String operationText = operationArea.getText();
                    String intermediateResultBeforEvents = "";
                    String inermedianteRezaltAfterEvents = "";
                    String resultOperation;
                    int after;
                    int before;
                    int result;
                    switch (buttonText) {
                        case "<<<":
                            if (currentText.length() > 1) {
                                currentText = currentText.substring(0, currentText.length() - 1);
                                intermediatText.set("");
                                break;
                            } else {
                                currentText = "0";
                                intermediatText.set("");
                                break;
                            }

                        case "+", "-", "*", "/":
                            if (intermediateResult.equals("")) {
                                operationArea.setText(buttonText);
                                intermediateResultBeforEvents = currentText;
                                intermediateResult = intermediateResultBeforEvents;
                                currentText = "0";
                                intermediatText.set("");
                                break;
                            } else {
                                if (buttonText.equals("+")) {
                                    operation = operationArea.getText();
                                    inermedianteRezaltAfterEvents = currentText;
                                    intermediateResultBeforEvents = intermediateResult;
                                    after = Integer.parseInt(inermedianteRezaltAfterEvents);
                                    before = Integer.parseInt(intermediateResultBeforEvents);
                                    result = after + before;
                                    resultOperation = String.valueOf(result);
                                    intermediateResult = resultOperation;
                                    operationArea.setText(operation);
                                    currentText = "0";
                                    intermediatText.set("");
                                    break;
                                }
                                if (buttonText.equals("-")) {

                                    inermedianteRezaltAfterEvents = currentText;
                                    intermediateResultBeforEvents = intermediateResult;
                                    after = Integer.parseInt(inermedianteRezaltAfterEvents);
                                    before = Integer.parseInt(intermediateResultBeforEvents);
                                    result = before - after;
                                    resultOperation = String.valueOf(result);
                                    intermediateResult = resultOperation;
                                    operationArea.setText(operation);
                                    currentText = "0";
                                    intermediatText.set("");
                                    break;
                                }
                                if (buttonText.equals("*")) {

                                    inermedianteRezaltAfterEvents = currentText;
                                    intermediateResultBeforEvents = intermediateResult;
                                    after = Integer.parseInt(inermedianteRezaltAfterEvents);
                                    before = Integer.parseInt(intermediateResultBeforEvents);
                                    result = before * after;
                                    resultOperation = String.valueOf(result);
                                    intermediateResult = resultOperation;
                                    operationArea.setText(operation);
                                    currentText = "0";
                                    intermediatText.set("");
                                    break;
                                }
                                if (buttonText.equals("/")) {

                                    inermedianteRezaltAfterEvents = currentText;
                                    intermediateResultBeforEvents = intermediateResult;
                                    after = Integer.parseInt(inermedianteRezaltAfterEvents);
                                    before = Integer.parseInt(intermediateResultBeforEvents);
                                    result = before / after;
                                    resultOperation = String.valueOf(result);
                                    intermediateResult = resultOperation;
                                    operationArea.setText(operation);
                                    currentText = "0";
                                    intermediatText.set("");
                                    break;
                                }
                            }
                        case "=":
//                            int inermediantInt = Integer.parseInt(intermediateResult);
//                            int inermediantInt1 = Integer.parseInt(currentText);
//                            switch (operation) {
//                                case "+" -> {
//                                    resalt = inermediantInt + inermediantInt1;
//                                    currentText = Integer.toString(resalt);
//                                }
//                                case "-" -> {
//                                    resalt = inermediantInt - inermediantInt1;
//                                    currentText = Integer.toString(resalt);
//                                }
//                                case "*" -> {
//                                    resalt = inermediantInt * inermediantInt1;
//                                    currentText = Integer.toString(resalt);
//                                }
//                                case "/" -> {
//                                    resalt = inermediantInt / inermediantInt1;
//                                    currentText = Integer.toString(resalt);
//                                }
//                            }
                            break;
                        case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9":
                            currentText = intermediatText.get();
                            currentText += buttonText;
                            intermediatText.set(currentText);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + buttonText);
                    }
                    resultArea.setText(intermediateResult);
                    inputArea.setText(currentText);

                });
                panel.add(currentButton);
            }
        }
    }
}
