import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

   JButton cancel;
   JButton next;
   JTextField tfname;

   Login() {

       this.setContentPane(new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Base gradient
        GradientPaint gp = new GradientPaint(
            0, 0, new Color(255, 105, 180), 
            getWidth(), getHeight(), new Color(30, 144, 254)
        );
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Halftone dots
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

      this.getContentPane().setBackground(Color.WHITE);
      this.setLayout((LayoutManager)null);

      
      JLabel heading = new JLabel("W E L C O M E ");
      heading.setBounds(295, 50, 600, 35);
      heading.setFont(new Font("Times New Roman", 1, 35));
      heading.setForeground(Color.WHITE);
      this.add(heading);

      JLabel heading2 = new JLabel("T O" );
      heading2.setBounds(390, 120, 600, 35);
      heading2.setFont(new Font("Times New Roman", 1, 35));
      heading2.setForeground(Color.WHITE);
      this.add(heading2);

       JLabel heading1 = new JLabel("J A V A   Q U I Z   A P P L I C A T I O N");
      heading1.setBounds(80, 180, 1500, 55);
      heading1.setFont(new Font("Times New Roman", 1, 35));
      heading1.setForeground(Color.WHITE);
      this.add(heading1);

       JLabel heading3 = new JLabel("Lets Test Your Knowledge In Java");
      heading3.setBounds(250, 280, 600, 30);
      heading3.setFont(new Font("Mongolian Baiti", 1, 20));
      heading3.setForeground(Color.WHITE);
      this.add(heading3);

      this.next = new JButton("N e x t");
      this.next.setBounds(310, 400, 180, 60);
      this.next.setBackground(new Color(30, 144, 254));
      this.next.setFont(new Font("Mongolian Baiti", 1, 24));
      this.next.setForeground(Color.WHITE);
      this.next.addActionListener(this);
      this.add(this.next);

      this.setSize(800, 600);
      this.setLocation(580, 200);
      this.setVisible(true);
   }

   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == this.next) {
       
         this.setVisible(false);
         new User();
      } else if (ae.getSource() == this.cancel) {
         this.setVisible(false);
      }
   }

   public static void main(String[] args) {
      new Login();
   }
}
