import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

class Calculator extends JFrame implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static JFrame jf;
    static JTextField tf;

    String s0, s1, s2;

    Calculator(){
        s0 = s1 = s2 = "";
    }

    public static void main(String[] args) {
        jf = new JFrame("Calculator"); 
  
        try { 
            // set look and feel 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } 
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        }

        Calculator c = new Calculator();

        tf = new JTextField(16);//create textfield

        tf.setEditable(false);//not editable text field

        //declare buttons
        JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, be, beq, beq1;

        //create number buttons
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        //create operands
        ba = new JButton("+");
        bs = new JButton("-");
        bd = new JButton("/");
        bm = new JButton("*");
        beq = new JButton("C"); 

        //create equals button
        beq1 = new JButton("=");

        //create . button
        be = new JButton(".");

        JPanel p = new JPanel();
        

        //add actionlisteners to buttons
        bm.addActionListener(c); 
        bd.addActionListener(c); 
        bs.addActionListener(c); 
        ba.addActionListener(c); 
        b9.addActionListener(c); 
        b8.addActionListener(c); 
        b7.addActionListener(c); 
        b6.addActionListener(c); 
        b5.addActionListener(c); 
        b4.addActionListener(c); 
        b3.addActionListener(c); 
        b2.addActionListener(c); 
        b1.addActionListener(c); 
        b0.addActionListener(c); 
        be.addActionListener(c); 
        beq.addActionListener(c); 
        beq1.addActionListener(c);

        //add buttons to panel
        p.add(tf);
        p.add(ba); 
        p.add(b1); 
        p.add(b2); 
        p.add(b3); 
        p.add(bs); 
        p.add(b4); 
        p.add(b5); 
        p.add(b6); 
        p.add(bm); 
        p.add(b7); 
        p.add(b8); 
        p.add(b9); 
        p.add(bd); 
        p.add(be); 
        p.add(b0); 
        p.add(beq); 
        p.add(beq1);

        //set background color to dark gray
        p.setBackground(Color.DARK_GRAY);

        jf.add(p);

        jf.setSize(200, 220);
        jf.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();

        if((s.charAt(0)>='0' && s.charAt(0)<='9') || s.charAt(0) == '.')
        {
            if(!s1.equals(""))
            {
                s2 = s + s2;
            }
            else
            {
                s0 = s + s0;
            }

            tf.setText(s0 + s1 + s2);
        }
        else if( s.charAt(0) == 'C')
        {
            s0 = s1 = s2 = "";
        }
        else if( s.charAt(0) == '=')
        {
            double te;
            if(s1.equals("+"))
                te = (Double.parseDouble(s0)+Double.parseDouble(s2));
            else if(s1.equals("-"))
                te = (Double.parseDouble(s0)-Double.parseDouble(s2));
            else if(s1.equals("*"))
                te = (Double.parseDouble(s0)*Double.parseDouble(s2));
            else
                te = (Double.parseDouble(s0)/Double.parseDouble(s2));

            tf.setText(s0 + s1 + s2 + " = " + te);

            s0 = Double.toString(te);

            s1 = s2 = "";
        }
        else{

            if(s1.equals("")||s2.equals(""))
                s1 = s;
            else
            {
                double te;

                if(s1.equals("+"))
                    te = (Double.parseDouble(s0)+Double.parseDouble(s2));
                else if(s1.equals("-"))
                    te = (Double.parseDouble(s0)-Double.parseDouble(s2));
                else if(s1.equals("*"))
                    te = (Double.parseDouble(s0)*Double.parseDouble(s2));
                else
                    te = (Double.parseDouble(s0)/Double.parseDouble(s2));


                s0 = Double.toString(te);
                s1 = s;
                s2 = "";
            }

            tf.setText(s0 + s1 + s2);
        }
    }
}