import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JTextField mathArea;

    public Main(){
        mathArea = new JTextField("");
        mathArea.setBounds(0,0,145,20);
        mathArea.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mathArea);
        calculations();
        numbers();
        clear();
    }

    private void numbers(){
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4,3,1,1));
        JButton[] nButtons = new JButton[10];
        for(int i = 1; i<=9; i++) {
            JButton button = new JButton(""+i);
            nButtons[i-1] = button;
            buttons.add(button);
            button.addActionListener(this);
        }
        buttons.setBounds(0,45,145,150);
        add(buttons);
        JButton zero = new JButton("0");
        nButtons[0] = zero;
        buttons.add(zero);
        zero.addActionListener(this);

    }

    private void calculations(){
        JPanel calcs = new JPanel();
        calcs.setLayout(new GridLayout(2,3,1,1));
        String[] bCalcs = new String[]{".", "+", "*", "=", "-", "/"};
        for (String xyz: bCalcs){
            JButton b = new JButton(xyz);
            b.setMargin( new Insets(5, 5, 5, 5) );
            calcs.add(b);
            b.addActionListener(this);
        }
        calcs.setBounds(49,157,95,37);
        add(calcs);
    }

    private void clear(){
        JPanel c = new JPanel();
        c.setLayout(new GridLayout(1,2,1,1));
        String[] bClear = {"CE","←"};
        for (String xyz: bClear){
            JButton b = new JButton(xyz);
            b.setMargin( new Insets(5, 5, 5, 5) );
            c.add(b);
            b.addActionListener(this);
        }
        c.setBounds(0,20,145,25);
        add(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        mathArea.setText( mathArea.getText() + cmd);

        if (cmd.equals("=")) {
            ScriptEngineManager mgr = new ScriptEngineManager(null);
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            String s = mathArea.getText();
            String result = s.substring(0, s.length() - 1);
            try {
                mathArea.setText(String.valueOf(engine.eval(result)));
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
        }else if (cmd.equals("CE")){
            mathArea.setText(null);
        }else if (cmd.equals("←")){
            String s = mathArea.getText();
            String oo = s.substring(0, s.length() - 2);
            mathArea.setText(oo);
        }
    }

    public static void main(String[] args) {
        Main window = new Main();

        window.setTitle("Kalkulator");
        window.setSize(151,226);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setResizable(false);
        window.setVisible(true);

    }
}