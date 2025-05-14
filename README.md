# ROOMI
quiz application system
//Pakage quizapplication.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionEvent.*;
//import java.sql.*;

public class Login extends JFrame implements ActionListener{          //JFrame is a class in of java.swing pakage used to create a ui frame 
    Login(){
        setVisible(true);             //used to maek the ui frame frame visible 
        setSize(1000, 500);           //used to set the size of the frame
        setLocation(500, 200);       //used to set the location of the frame
        getContentPane().setBackground(Color .white);          //used to set the backgroud of the ui frame

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg"));      //it is used to import images form your system to the program
        JLabel image = new JLabel(i1);     ////used to create a label for the image
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
        name.setFont(new Font("Mongolian Baiti",Font.BOLD, 16));
        name.setForeground(new Color(30, 144, 254));
