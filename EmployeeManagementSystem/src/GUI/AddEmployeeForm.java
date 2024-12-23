import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AddEmployeeForm extends JDialog {
    private JTextField nameField, ageField, departmentField, salaryField, ratingField;
    private JButton addButton, cancelButton;
    private EmployeeDAO employeeDAO;

    public AddEmployeeForm(JFrame parent) {
        super(parent, "Add Employee", true);
        employeeDAO = new EmployeeDAO();

        setLayout(new GridLayout(6, 2));

        nameField = new JTextField();
        ageField = new JTextField();
        departmentField = new JTextField();
        salaryField = new JTextField();
        ratingField = new JTextField();
        addButton = new JButton("Add Employee");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Department:"));
        add(departmentField);
        add(new JLabel("Salary:"));
        add(salaryField);
        add(new JLabel("Performance Rating:"));
        add(ratingField);
        add(addButton);
        add(cancelButton);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String department = departmentField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String performanceRating = ratingField.getText();

                Employee newEmployee = new Employee(0, name, age, department, salary, performanceRating);
                employeeDAO.addEmployee(newEmployee);
                ((EmployeeTable) getParent()).loadEmployeeData();  // Reload data on parent
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
                dispose(); // Close the form
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding employee: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setSize(300, 300);
        setLocationRelativeTo(parent);
    }
}
