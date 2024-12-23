import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new EmployeeTable();
            JDialog addEmployeeForm = new AddEmployeeForm(frame);
            addEmployeeForm.setVisible(true);
        });
    }
}
