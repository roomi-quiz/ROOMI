import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Rules extends JFrame implements ActionListener {

   String name;
   JButton start;
   JButton back;

   Rules(String name) {

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

      this.name = name;
      this.getContentPane().setBackground(Color.WHITE);
      this.setLayout((LayoutManager)null);

      JLabel heading = new JLabel("DEAR " + name + ", PLEASE READ THE FOLLOWING RULES");
      heading.setBounds(50, 50, 1000, 30);
      heading.setFont(new Font("Times New Roman", 1, 24));
      heading.setForeground(Color.WHITE);
      this.add(heading);

      JLabel rules = new JLabel();
      rules.setBounds(40, 100, 700, 350);
      rules.setFont(new Font("Mongolian Baiti", 0, 20));
      rules.setText("<html>1. This is a JAVA LANGUAGE quiz.<br><br>"
            + "2. You will be asked 10 questions.<br><br>"
            + "3. Each question carries 10 marks.<br><br>"
            + "4. There is no negative marking.<br><br>"
            + "5. You will get 15 seconds to answer each question.<br><br>"
            + " GOOD LUCK!<br></html>");
      this.add(rules);

      this.back = new JButton("Back");
      this.back.setBounds(200, 500, 120, 25);
      this.back.setBackground(new Color(255, 16, 240));
      this.back.setForeground(Color.WHITE);
      this.back.addActionListener(this);
      this.add(this.back);

      this.start = new JButton("Next");
      this.start.setBounds(500, 500, 120, 25);
      this.start.setBackground(new Color(255, 16, 240));
      this.start.setForeground(Color.WHITE);
      this.start.addActionListener(this);
      this.add(this.start);

      this.setSize(800, 650);
      this.setLocation(600, 100);
      this.setVisible(true);
   }

   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == this.start) {
         this.setVisible(false);
         new Quiz(this.name);
      } else {
         this.setVisible(false);
         new User();
      }
   }

   public static void main(String[] args) {
      new Rules("Guest");
   }
}
