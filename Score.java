import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JFrame implements ActionListener {

    Score(String name, int score) {
        this.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(255, 105, 180), 
                    getWidth(), getHeight(), new Color(30, 144, 254)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                g2d.setColor(new Color(0, 0, 0, 20));
                int dotSize = 8;
                int gap = 20;
                for (int y = 0; y < getHeight(); y += gap) {
                    for (int x = 0; x < getWidth(); x += gap) {
                        int size = (int)(dotSize * (0.5 + Math.random() * 0.5));
                        g2d.fillOval(x, y, size, size);
                    }
                }
            }
        });

        JLabel heading = new JLabel("T H A N K  Y O U   " + name);
        heading.setBounds(250, 50, 1000, 30);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        this.add(heading);

        JLabel heading1 = new JLabel("FOR  INCREASING  YOUR  KNOWLEDGE");
        heading1.setBounds(150, 120, 1000, 30);
        heading1.setFont(new Font("Times New Roman", Font.BOLD, 26));
        heading1.setForeground(Color.WHITE);
        this.add(heading1);

        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(280, 220, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblscore.setForeground(new Color(5, 5, 10));
        this.add(lblscore);

        JLabel performanceMsg = new JLabel();
        performanceMsg.setBounds(275, 300, 400, 30);
        performanceMsg.setFont(new Font("Times New Roman", Font.BOLD, 24));
        performanceMsg.setForeground(Color.WHITE);
        
        if (score > 80 && score <= 100) {
            performanceMsg.setText("G R E A T  J O B,  " + name + "!");
        } else if (score > 50 && score <= 80) {
            performanceMsg.setText("W E L L  A T T E M P T E D,  " + name + "!");
        } else if (score > 0 && score <= 50) {
            performanceMsg.setText("BETTER LUCK NEXT TIME, " + name + "!");
        } else {
            performanceMsg.setText("K E E P  T R Y I N G,  " + name + "!");
        }
        
        this.add(performanceMsg);

        JButton submit = new JButton("Play Again");
        submit.setBounds(345, 400, 120, 50);
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        this.add(submit);

        this.setBounds(580, 200, 800, 550);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new Score("User", 85); 
    }
}