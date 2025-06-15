import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Quiz extends JFrame {

    private List<Question> questions = new ArrayList<>();
    private String[][] useranswers = new String[10][1];
    private JLabel qno, question;
    private JRadioButton opt1, opt2, opt3, opt4;
    private ButtonGroup groupoptions;
    public static int timer = 20;
    public static int ans_given = 0;
    public static int count = 0;

    class Question {
        String question;
        String option1;
        String option2;
        String option3;
        String option4;
        String correctAnswer;

        public Question(String question, String option1, String option2, String option3, String option4, String correctAnswer) {
            this.question = question;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.option4 = option4;
            this.correctAnswer = correctAnswer;
        }
    }

    Quiz() {
        // Load questions from database
        loadQuestionsFromDatabase();

        setBounds(280, 80, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpg"));     
        JLabel image = new JLabel(i1);     
        image.setBounds(0, 0, 1440, 392);
        add(image);

        qno = new JLabel("1.");
        qno.setBounds(50, 420, 60, 40);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        qno.setForeground(new Color(30, 144, 254));
        add(qno);

        question = new JLabel();
        question.setBounds(100, 420, 900, 40);
        question.setFont(new Font("Tahoma", Font.PLAIN, 20));
        question.setForeground(new Color(30, 144, 254));
        add(question);

        opt1 = new JRadioButton();
        opt1.setBounds(100, 500, 500, 40);  
        opt1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        opt1.setForeground(new Color(30, 144, 254));
        opt1.setBackground(Color.white);
        add(opt1);

        opt2 = new JRadioButton();  
        opt2.setBounds(100, 565, 500, 40);
        opt2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        opt2.setForeground(new Color(30, 144, 254));
        opt2.setBackground(Color.white);
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(100, 635, 500, 40);
        opt3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        opt3.setForeground(new Color(30, 144, 254));
        opt3.setBackground(Color.white);
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(100, 700, 500, 40);
        opt4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        opt4.setForeground(new Color(30, 144, 254));
        opt4.setBackground(Color.white);
        add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2); 
        groupoptions.add(opt3);
        groupoptions.add(opt4);  

        JButton next = new JButton("Next");
        next.setBounds(1100, 560, 150, 40);
        next.setBackground(new Color(30, 144, 254));
        next.setForeground(Color.white);
        next.setFont(new Font("Tahoma", Font.BOLD, 16));
        next.addActionListener(e -> {
            if (groupoptions.getSelection() != null) {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }
            count++;
            if (count < questions.size()) {
                start(count);
                ans_given = 1;
                groupoptions.clearSelection();
            } else {
                JOptionPane.showMessageDialog(this, "Quiz completed!");
            }
        });
        add(next);

        JButton lifeline = new JButton("LifeLine");
        lifeline.setBounds(1100, 630, 150, 40);
        lifeline.setBackground(new Color(30, 144, 254));
        lifeline.setForeground(Color.white);
        lifeline.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lifeline);

        JButton submit = new JButton("Submit");
        submit.setBounds(1100, 700, 150, 40);
        submit.setBackground(new Color(30, 144, 254));
        submit.setForeground(Color.white);
        submit.setFont(new Font("Tahoma", Font.BOLD, 16));
        submit.setEnabled(false);
        submit.addActionListener(e -> {
            // Calculate and show score
            int score = calculateScore();
            JOptionPane.showMessageDialog(this, "Your score: " + score + "/" + questions.size());
            System.exit(0);
        });
        add(submit);

        start(count);
        
        setVisible(true);
    }

    private void loadQuestionsFromDatabase() {
        try {
            // Replace with your database credentials
            String url = "jdbc:mysql://localhost:3306/quiz_application";
            String username = "root";
            String password = ""; // your password here

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM questions");

            while (resultSet.next()) {
                Question q = new Question(
                    resultSet.getString("question"),
                    resultSet.getString("option1"),
                    resultSet.getString("option2"),
                    resultSet.getString("option3"),
                    resultSet.getString("option4"),
                    resultSet.getString("correct_answer")
                );
                questions.add(q);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading questions from database");
            System.exit(1);
        }
    }

    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (useranswers[i][0] != null && useranswers[i][0].equals(questions.get(i).correctAnswer)) {
                score++;
            }
        }
        return score;
    }

    public void paint(Graphics g) {
        super.paint(g);

        String time = "Time Remaining: " + timer + " seconds";
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 24));
        if (timer > 0) {
            g.drawString(time, 1000, 500);
        } else {
            g.drawString("TIMES UP!!!", 1100, 500);
            g.setFont(new Font("Tahoma", Font.BOLD, 30));
        }
        
        timer--;

        try {
            Thread.sleep(1000);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ans_given == 1) {
            ans_given = 0;
            timer = 20;
        } else if (timer < 0) {
            timer = 20;

            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            } 
            count++;
            if (count < questions.size()) {
                start(count);
            } else {
                int score = calculateScore();
                JOptionPane.showMessageDialog(this, "Time's up! Your score: " + score + "/" + questions.size());
                System.exit(0);
            }
        }
    }

    public void start(int count) {
        if (count >= questions.size()) return;
        
        Question current = questions.get(count);
        qno.setText("" + (count + 1) + ". ");
        question.setText(current.question);
        opt1.setText(current.option1);
        opt1.setActionCommand(current.option1);
        opt2.setText(current.option2);
        opt2.setActionCommand(current.option2);
        opt3.setText(current.option3);
        opt3.setActionCommand(current.option3);
        opt4.setText(current.option4);
        opt4.setActionCommand(current.option4);
    }

    public static void main(String[] args) {
        new Quiz();
    }
}