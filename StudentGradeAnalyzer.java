import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeAnalyzer extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField sectionField;
    private JTextField physicsField;
    private JTextField mathsField;
    private JTextField chemistryField;
    private JTextField englishField;
    private JTextField totalField;
    private JTextField percentageField;
    private JTextField gradeField;
    private JTextArea reportArea;

    private List<Student> students;

    public StudentGradeAnalyzer() {
        students = new ArrayList<>();

        // Set the title for the frame
        setTitle("Student Grade Analyzer");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.PINK);

        // Create a JLabel for the header text
        JLabel headerLabel = new JLabel("VR Public School, Mumbai");
        Font font = new Font("Arial", Font.BOLD, 24); // Specify the font and style
        headerLabel.setFont(font); // Set the font for the JLabel
        headerLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the text

        // Add the header label to the top of the frame
        add(headerLabel, BorderLayout.NORTH);

        // Add some padding to the label
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add 10 pixels of padding at the top and bottom

        // Create a panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        // Student Info Panel
        JPanel studentInfoPanel = new JPanel(new FlowLayout());
        studentInfoPanel.setBackground(Color.CYAN); // Set the background color to CYAN
        JLabel idLabel = new JLabel("Student ID:");
        idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField(10);
        JLabel sectionLabel = new JLabel("Section:");
        sectionField = new JTextField(5);

        studentInfoPanel.add(idLabel);
        studentInfoPanel.add(idField);
        studentInfoPanel.add(nameLabel);
        studentInfoPanel.add(nameField);
        studentInfoPanel.add(sectionLabel);
        studentInfoPanel.add(sectionField);
        inputPanel.add(studentInfoPanel);

        // Scores Panel
        JPanel scoresPanel = new JPanel(new FlowLayout());
        scoresPanel.setBackground(Color.CYAN); // Set the background color to CYAN
        JLabel physicsLabel = new JLabel("Physics Score:");
        physicsField = new JTextField(5);
        JLabel mathsLabel = new JLabel("Maths Score:");
        mathsField = new JTextField(5);
        JLabel chemistryLabel = new JLabel("Chemistry Score:");
        chemistryField = new JTextField(5);
        JLabel englishLabel = new JLabel("English Score:");
        englishField = new JTextField(5);

        scoresPanel.add(physicsLabel);
        scoresPanel.add(physicsField);
        scoresPanel.add(mathsLabel);
        scoresPanel.add(mathsField);
        scoresPanel.add(chemistryLabel);
        scoresPanel.add(chemistryField);
        scoresPanel.add(englishLabel);
        scoresPanel.add(englishField);
        inputPanel.add(scoresPanel);

        // Results Panel
        JPanel resultsPanel = new JPanel(new FlowLayout());
        resultsPanel.setBackground(Color.CYAN); // Set the background color to CYAN
        JLabel totalLabel = new JLabel("Total Marks:");
        totalField = new JTextField(5);
        totalField.setEditable(false);
        JLabel percentageLabel = new JLabel("Percentage:");
        percentageField = new JTextField(5);
        percentageField.setEditable(false);
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(5);
        gradeField.setEditable(false);

        resultsPanel.add(totalLabel);
        resultsPanel.add(totalField);
        resultsPanel.add(percentageLabel);
        resultsPanel.add(percentageField);
        resultsPanel.add(gradeLabel);
        resultsPanel.add(gradeField);
        inputPanel.add(resultsPanel);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.PINK); // Set the background color to PINK
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        inputPanel.add(buttonPanel);

        add(inputPanel, BorderLayout.WEST);

        // Report Area
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void calculateGrade() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String section = sectionField.getText();
            int physics = Integer.parseInt(physicsField.getText());
            int maths = Integer.parseInt(mathsField.getText());
            int chemistry = Integer.parseInt(chemistryField.getText());
            int english = Integer.parseInt(englishField.getText());

            int totalMarks = physics + maths + chemistry + english;
            double percentage = (double) totalMarks / 4.0;
            String grade = calculateGradeFromPercentage(percentage);

            totalField.setText(String.valueOf(totalMarks));
            percentageField.setText(String.format("%.2f%%", percentage));
            gradeField.setText(grade);

            Student student = new Student(id, name, section, physics, maths, chemistry, english, totalMarks, percentage, grade);
            students.add(student);

            displayReport();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values for scores and ID.");
        }
    }

    private String calculateGradeFromPercentage(double percentage) {
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    private void displayReport() {
        StringBuilder report = new StringBuilder("All Student Reports:\n\n");
        for (Student student : students) {
            report.append("Student ID: ").append(student.getId()).append("\n");
            report.append("Student Name: ").append(student.getName()).append("\n");
            report.append("Section: ").append(student.getSection()).append("\n");
            report.append("Physics: ").append(student.getPhysics()).append("\n");
            report.append("Maths: ").append(student.getMaths()).append("\n");
            report.append("Chemistry: ").append(student.getChemistry()).append("\n");
            report.append("English: ").append(student.getEnglish()).append("\n");
            report.append("Total Marks: ").append(student.getTotalMarks()).append("\n");
            report.append("Percentage: ").append(student.getPercentage()).append("\n");
            report.append("Grade: ").append(student.getGrade()).append("\n");
            report.append("--------------------------------------------------\n");
        }
        reportArea.setText(report.toString());
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        sectionField.setText("");
        physicsField.setText("");
        mathsField.setText("");
        chemistryField.setText("");
        englishField.setText("");
        totalField.setText("");
        percentageField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeAnalyzer analyzer = new StudentGradeAnalyzer();
            analyzer.setVisible(true);
        });
    }
}

class Student {
    private int id;
    private String name;
    private String section;
    private int physics;
    private int maths;
    private int chemistry;
    private int english;
    private int totalMarks;
    private double percentage;
    private String grade;

    public Student(int id, String name, String section, int physics, int maths, int chemistry, int english, int totalMarks, double percentage, String grade) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.physics = physics;
        this.maths = maths;
        this.chemistry = chemistry;
        this.english = english;
        this.totalMarks = totalMarks;
        this.percentage = percentage;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public int getPhysics() {
        return physics;
    }

    public int getMaths() {
        return maths;
    }

    public int getChemistry() {
        return chemistry;
    }

    public int getEnglish() {
        return english;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getGrade() {
        return grade;
    }
}
