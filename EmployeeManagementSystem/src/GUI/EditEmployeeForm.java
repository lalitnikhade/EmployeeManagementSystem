import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class EditEmployeeForm extends JDialog {
    private JTextField nameField, ageField, departmentField, salaryField, ratingField;
    private JButton saveButton, cancelButton;
    private EmployeeDAO employeeDAO;

    public EditEmployeeForm(JFrame parent, Employee employee) {
        super(parent, "Edit Employee", true);
        employeeDAO = new EmployeeDAO();

        setLayout(new GridLayout(6, 2));

        // Pre-fill fields with employee data
        nameField = new JTextField(employee.getName());
        ageField = new JTextField(String.valueOf(employee.getAge()));
        departmentField = new JTextField(employee.getDepartment());
        salaryField = new JTextField(String.valueOf(employee.getSalary()));
        ratingField = new JTextField(employee.getPerformanceRating());
        saveButton = new JButton("Save Changes");
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
        add(saveButton);
        add(cancelButton);

        saveButton.addActionListener(e -> {
            try {
                // Update employee data
                employee.setName(nameField.getText());
                employee.setAge(Integer.parseInt(ageField.getText()));
                employee.setDepartment(departmentField.getText());
                employee.setSalary(Double.parseDouble(salaryField.getText()));
                employee.setPerformanceRating(ratingField.getText());

                employeeDAO.updateEmployee(employee);
                ((EmployeeTable) getParent()).loadEmployeeData(); // Reload data on parent
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                dispose(); // Close the form
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error updating employee: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setSize(300, 300);
        setLocationRelativeTo(parent);
    }
}
