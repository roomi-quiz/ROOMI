//Pakage quizapplication.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionEvent.*;
//import java.sql.*;

public class Login extends JFrame implements ActionListener{  

  JButton next, exit; 
  JTextField tfname;
    
      
    Login(){
                   
        setSize(1000, 500);           
        setLocation(500, 200);       
        getContentPane().setBackground(Color .white);          

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg"));     
        JLabel image = new JLabel(i1);     
        add(image);
        setLayout(null);
        image.setBounds(0, 0, 400, 480);
        
        JLabel heading = new JLabel("WELCOME TO QUIZ LAND");
        heading.setBounds(500, 60, 600, 45);
        add(heading);
        heading.setFont(new Font("Mongolian Baiti",Font.BOLD, 30));
        heading.setForeground(new Color(30, 144, 254));
        
         JLabel name=new JLabel("Enter Your Name");
        name.setBounds(625, 200, 300,20);
        add(name);
        name.setFont(new Font("Mongolian Baiti",Font.BOLD, 20));
        name.setForeground(new Color(30, 144, 254));

        tfname = new JTextField();
        tfname.setBounds(535, 230, 300, 30);
        add(tfname);
        tfname.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        tfname.setForeground(new Color(30, 144, 254));

        exit = new JButton("Exit");
        exit.setBounds(550, 370,70, 35);
        exit.setBackground(new Color(30, 144, 254));
        exit.setForeground(Color.white);
        exit.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
        exit.addActionListener(this);
        add(exit);

        next = new JButton("Next");
        next.setBounds(755, 370,70, 35);
        next.setBackground(new Color(30, 144, 254));
        next.setForeground(Color.white);
        next.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
        next.addActionListener(this);
        add(next);






         setVisible(true); 
        }


      public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
          String name = tfname.getText();
          setVisible(false);
          new Rules(name);


        }
        else if(ae.getSource() == exit){
          

          setVisible(false);


        }

        }

    public static void main(String[] args){

    new Login();
}
}

