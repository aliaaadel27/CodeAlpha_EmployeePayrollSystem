import java.util.ArrayList;
import java.util.List;

public class PayrollSystem {
    private final List<Employee> employees;

    public PayrollSystem() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public double calculateSalary(Employee employee) {
        return employee.getHourlyRate() * employee.getHoursWorked();
    }


    public String generatePayStub(Employee employee) {
        double salary = calculateSalary(employee);
        return String.format("""
                        Pay Stub for Employee %s:
                        Name: %s
                        Total Hours Worked: %.2f
                        Hourly Rate: $%.2f
                        Total Salary: $%.2f""",
                employee.getId(), employee.getName(), employee.getHoursWorked(),
                employee.getHourlyRate(), salary);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
