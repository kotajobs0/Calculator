import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // コンポーネントの宣言
    private JTextField display;
    private JPanel panel;
    private String[] buttons = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
    	
        "0", "C", "=", "+"
    };
    private JButton[] btnObjects = new JButton[buttons.length];
    
    // 計算用変数
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        // フレームの設定
        setTitle("Java Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ディスプレイ部分
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // ボタンパネル
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        for (int i = 0; i < buttons.length; i++) {
            btnObjects[i] = new JButton(buttons[i]);
            btnObjects[i].setFont(new Font("Arial", Font.BOLD, 18));
            btnObjects[i].addActionListener(this);
            panel.add(btnObjects[i]);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // 数字ボタンの処理
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } 
        // クリアボタン
        else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        } 
        // イコールボタン
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
            }
            display.setText(String.valueOf(result));
            num1 = result; // 連続計算用
        } 
        // 演算子ボタン
        else {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}