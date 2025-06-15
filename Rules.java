//package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {
    JButton start, cancel;
    String name;
    public Rules(String name) {
        this.name = name;

        setSize(1000, 500);
        setLocation(500, 200);
        getContentPane().setBackground(Color.white);
        setLayout(null);

         JLabel heading = new JLabel("WELCOME TO QUIZ  LAND" + " " + name + " " + ""  );
        heading.setBounds(50, 50, 700, 30);
        heading.setFont(new Font("Mongolian Baiti",Font.BOLD, 30));
        heading.setForeground(new Color(30, 144, 254));
         add(heading);

        JLabel rules = new JLabel( );
        rules.setBounds(70, 0, 700, 470);
        rules.setFont(new Font("Tahoma",Font.PLAIN, 16));
        rules.setForeground(new Color(30, 144, 254));
        rules.setText(
             
                "<html>" +
                "1. The quiz consists of multiple-choice questions." + "<br><br>" +
                "2. Each question has four options, out of which only one is correct." + "<br><br>" +
                "3. You will have 15 seconds to answer each question." + "<br><br>" +
                "4. For each correct answer, you will earn 10 points." + "<br><br>" +
                "5. There is no negative marking for incorrect answers." + "<br><br>" +
                "6. Good luck!" + "<br><br>" +
                "</html>"
        );
        add(rules);
        
        cancel = new JButton("CANCEL");
        cancel.setBounds(100, 370,150, 40);
        cancel.setBackground(new Color(30, 144, 254));
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
        cancel.addActionListener(this);
        add(cancel);

        start = new JButton("START");
        start.setBounds(650, 370,150, 40);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.white);
        start.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
        start.addActionListener(this);
        add(start);

        
      

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == start){
          setVisible(false);
         new Quiz();
         


        }
        else if(ae.getSource() == cancel){
          

          setVisible(false);
            new Login();


        }

        }

   
    public static void main(String[] args) {
        new Rules("USER");
    }
}
