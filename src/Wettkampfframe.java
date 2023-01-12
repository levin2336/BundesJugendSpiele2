import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Wettkampfframe extends JFrame {
    private JTextField firstNameField, lastNameField, ageField, classField, pointsField;
    private JButton addButton, sortButton, saveButton, loadButton;
    private DefaultListModel<Student> studentListModel;
    private JList<Student> studentList;

    public Wettkampfframe() {
        // Initialize components
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        ageField = new JTextField();
        classField = new JTextField();
        pointsField = new JTextField();
        addButton = new JButton("Add");
        sortButton = new JButton("Sort");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);

        // Add action listeners to buttons
        addButton.addActionListener(new AddButtonListener());
        sortButton.addActionListener(new AddButtonListener());
        saveButton.addActionListener(new AddButtonListener());
        loadButton.addActionListener(new AddButtonListener());

        // Set up layout
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("First Name:"));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Last Name:"));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Class:"));
        inputPanel.add(classField);
        inputPanel.add(new JLabel("Points:"));
        inputPanel.add(pointsField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(studentList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Student List");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Action listener for add button
    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String className = classField.getText();
            int points = Integer.parseInt(pointsField.getText());
            Student student = new Student(firstName, lastName, age, className, points);
            studentListModel.addElement(student);
            firstNameField.setText("");
            lastNameField.setText("");
            ageField.setText("");
            classField.setText("");
            pointsField.setText("");
        }
    }

    // Action listener for sort button
    private class SortButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Collections.sort(null);
            studentList.updateUI();
        }
    }

    // Action listener for save button
    private class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showSaveDialog(Wettkampfframe.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                    for (int i = 0; i < studentListModel.size(); i++) {
                        out.writeObject(studentListModel.get(i));
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Action listener for load button
    private class LoadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(Wettkampfframe.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    studentListModel.clear();
                    Student student;
                    while ((student = (Student) in.readObject()) != null) {
                        studentListModel.addElement(student);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Wettkampfframe f = new Wettkampfframe();
    }
}

class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private int age;
    private String className;
    private int points;

    public Student(String firstName, String lastName, int age, String className, int points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.className = className;
        this.points = points;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getClassName() {
        return className;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(Student other) {
        return this.lastName.compareTo(other.lastName);
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + " | Age: " + age + " | Class: " + className + " | Points: " + points;
    }
}
