import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class User extends JFrame implements ActionListener {
    
JTextField tfname;
JTextField tffathername;
JTextField tage;
JTextField tqualification;
JButton back;
JButton next;
JComboBox<String> qualificationCombo;

    public User() {

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

   


JLabel heading = new JLabel("U S E R   D E T A I L S", SwingConstants.CENTER);
heading.setBounds(0, 30, 1100, 70);
heading.setFont(new Font("Tahoma", Font.BOLD, 40));
heading.setForeground(new Color(5, 5, 10));
this.setLayout(null); 
this.add(heading);

JLabel name = new JLabel("Enter your name");
      name.setBounds(480, 200, 300, 20);
      name.setFont(new Font("Mongolian Baiti", 1, 18));
      name.setForeground(new Color(5, 5, 10));
      this.add(name);

      this.tfname = new JTextField();
      this.tfname.setBounds(400, 230, 300, 25);
      this.tfname.setFont(new Font("Times New Roman", 0, 20));
      this.add(this.tfname);

      JLabel fathername = new JLabel("Enter your Father name");
      fathername.setBounds(460, 310, 300, 20);
      fathername.setFont(new Font("Mongolian Baiti", 1, 18));
      fathername.setForeground(new Color(5, 5, 10));
      this.add(fathername);

      this.tffathername = new JTextField();
      this.tffathername.setBounds(400, 340, 300, 25);
      this.tffathername.setFont(new Font("Times New Roman", 0, 20));
      this.add(this.tffathername);

      JLabel age = new JLabel("Enter your Age");
      age.setBounds(490, 420, 300, 20);
      age.setFont(new Font("Mongolian Baiti", 1, 18));
      age.setForeground(new Color(5, 5, 10));
      this.add(age);

      this.tage = new JTextField();
      this.tage.setBounds(400, 450, 300, 25);
      this.tage.setFont(new Font("Times New Roman", 0, 20));
      this.add(this.tage);

 
JLabel qualificationLabel = new JLabel("Select your Qualification:");
qualificationLabel.setBounds(460, 530, 300, 20);
qualificationLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
qualificationLabel.setForeground(new Color(5, 5, 10));
this.add(qualificationLabel);


String[] qualifications = {"Select", "Matric", "Intermediate", "Undergraduate", "Master"};
this.qualificationCombo = new JComboBox<>(qualifications);
this.qualificationCombo.setBounds(400, 560, 300, 25);
this.qualificationCombo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
this.qualificationCombo.setBackground(Color.WHITE);
this.add(this.qualificationCombo);



    

      this.back = new JButton("Back");
      this.back.setBounds(270, 650, 120, 25);
      this.back.setBackground(new Color(255, 16, 240));
      this.back.setForeground(Color.WHITE);
      this.back.addActionListener(this);
      this.add(this.back);

      this.next = new JButton("Next");
      this.next.setBounds(700, 650, 120, 25);
      this.next.setBackground(new Color(255, 16, 240));
      this.next.setForeground(Color.WHITE);
      this.next.addActionListener(this);
      this.add(this.next);

     

      setSize(1100, 800);
      setLocation(400, 100);
      setVisible(true);
    }

   public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == this.next) {
        String name = this.tfname.getText().trim();
        String fatherName = this.tffathername.getText().trim();
        String ageText = this.tage.getText().trim();
        String qualification = (String)this.qualificationCombo.getSelectedItem();
        
        if (name.isEmpty() || fatherName.isEmpty() || ageText.isEmpty() || 
            qualification.equals("Select")) {
            
            JOptionPane.showMessageDialog(this, 
                "Please fill all the required details!\nThank You!", 
                "Incomplete Form", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int age = Integer.parseInt(ageText);
            Conn c = new Conn();
            
           
            if (c.getConnection() == null) {
                JOptionPane.showMessageDialog(this, 
                    "Failed to connect to database. Please try again later.", 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String query = "INSERT INTO user (name, fathername, age, qualification) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = c.getConnection().prepareStatement(query)) {
                pstmt.setString(1, name);
                pstmt.setString(2, fatherName);
                pstmt.setInt(3, age);
                pstmt.setString(4, qualification);
                
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    this.setVisible(false);
                    new Rules(name);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Failed to save user details", 
                        "Database Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            c.close(); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid age!", 
                "Invalid Age", 
                JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Database error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } else if (ae.getSource() == this.back) {
        this.setVisible(false);
        new Login();
    }
}

   

    public static void main(String[] args) {
         new User();
    
    }
}