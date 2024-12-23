import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class EmployeeTable extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private EmployeeDAO employeeDAO;

    public EmployeeTable() {
        setTitle("Employee Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        employeeDAO = new EmployeeDAO();

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Department");
        tableModel.addColumn("Salary");
        tableModel.addColumn("Performance Rating");

        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane);

        loadEmployeeData();

        // Add Add, Update, and Delete buttons
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, "South");

        setVisible(true);
    }

    public void loadEmployeeData() {
        try {
            List<Employee> employees = employeeDAO.getAllEmployees();
            tableModel.setRowCount(0); // Clear existing rows

            for (Employee employee : employees) {
                tableModel.addRow(new Object[]{
                        employee.getId(),
                        employee.getName(),
                        employee.getAge(),
                        employee.getDepartment(),
                        employee.getSalary(),
                        employee.getPerformanceRating()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data");
            e.printStackTrace();
        }
    }

    // Method to add a new employee
    private void addEmployee() {
        // Show a dialog or form to add a new employee
        JTextField nameField = new JTextField(15);
        JTextField ageField = new JTextField(3);
        JTextField departmentField = new JTextField(15);
        JTextField salaryField = new JTextField(10);
        JTextField ratingField = new JTextField(5);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Department:"));
        panel.add(departmentField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);
        panel.add(new JLabel("Performance Rating:"));
        panel.add(ratingField);

        int option = JOptionPane.showConfirmDialog(this, panel, "Add Employee", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String department = departmentField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String performanceRating = ratingField.getText();

                Employee newEmployee = new Employee(0, name, age, department, salary, performanceRating);
                employeeDAO.addEmployee(newEmployee);
                loadEmployeeData();  // Reload data on the table
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding employee: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check the entered values.");
            }
        }
    }

    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) employeeTable.getValueAt(selectedRow, 0);
            String name = (String) employeeTable.getValueAt(selectedRow, 1);
            int age = (int) employeeTable.getValueAt(selectedRow, 2);
            String department = (String) employeeTable.getValueAt(selectedRow, 3);
            double salary = (double) employeeTable.getValueAt(selectedRow, 4);
            String rating = (String) employeeTable.getValueAt(selectedRow, 5);

            Employee employee = new Employee(id, name, age, department, salary, rating);
            try {
                employeeDAO.updateEmployee(employee);
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                loadEmployeeData(); // Reload the table data
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating employee: " + e.getMessage());
            }
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) employeeTable.getValueAt(selectedRow, 0);
            try {
                employeeDAO.deleteEmployee(id);
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                loadEmployeeData(); // Reload the table data
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting employee: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeTable());
    }
}
