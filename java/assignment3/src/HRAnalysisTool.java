import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class HRAnalysisTool {

    public List<Employee> getHighEarners (List<Employee>employees){
        double salaryThreshold=50000.0;
        return employees.stream().filter(emp->emp.getSalary()>salaryThreshold).collect(Collectors.toList());


    }
    //mapping employees with corresponding to their department
    public Map<String ,List<Employee>>groupEmployeesByDepartment(List<Employee>employees){
        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

    }


    public Map<String, Double> getAverageSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
    }

    public List<Employee> getEmployeesSortedByExperienceAndSalary(List<Employee> employees) {
        Comparator<Employee> byExperienceDesc = Comparator.comparingInt(Employee::getExperienceInYears).reversed();
        Comparator<Employee> bySalaryDesc = Comparator.comparingDouble(Employee::getSalary);

        return employees.stream()
                .sorted(byExperienceDesc.thenComparing(bySalaryDesc.reversed()))
                .collect(Collectors.toList());
    }
}
