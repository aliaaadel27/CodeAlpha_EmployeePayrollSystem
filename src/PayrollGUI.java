import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PayrollGUI extends JFrame {
    private final PayrollSystem payrollSystem;
    private JTextArea outputArea;

    public PayrollGUI() {
        super("Employee Payroll System");
        payrollSystem = new PayrollSystem();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField rateField = new JTextField();
        JTextField hoursField = new JTextField();

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Hourly Rate:"));
        inputPanel.add(rateField);
        inputPanel.add(new JLabel("Hours Worked:"));
        inputPanel.add(hoursField);

        JButton addEmployeeBtn = new JButton("Add Employee");
        addEmployeeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idField.getText();
                    String name = nameField.getText();
                    double rate = Double.parseDouble(rateField.getText());
                    double hours = Double.parseDouble(hoursField.getText());

                    Employee employee = new Employee(id, name, rate);
                    employee.setHoursWorked(hours);

                    payrollSystem.addEmployee(employee);
                    outputArea.append("Employee added: " + employee + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PayrollGUI.this,
                            "Please enter valid numeric values for Rate and Hours Worked.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton generatePayStubBtn = new JButton("Generate Pay Stub");
        generatePayStubBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Employee> employees = payrollSystem.getEmployees();
                if (employees.isEmpty()) {
                    JOptionPane.showMessageDialog(PayrollGUI.this,
                            "No employees found.", "Empty List", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder payStubs = new StringBuilder();
                    for (Employee employee : employees) {
                        payStubs.append(payrollSystem.generatePayStub(employee)).append("\n\n");
                    }
                    outputArea.setText(payStubs.toString());
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        buttonPanel.add(addEmployeeBtn);
        buttonPanel.add(generatePayStubBtn);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        PayrollGUI payrollGUI = new PayrollGUI();
        payrollGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        payrollGUI.pack();
        payrollGUI.setLocationRelativeTo(null); // Center the window
        payrollGUI.setVisible(true);
    }
}
