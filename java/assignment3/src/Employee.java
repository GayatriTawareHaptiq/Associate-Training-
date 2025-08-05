import java.util.Objects;


public  final class Employee {

    private final int id;
    private final String name;
    private final String department;
    private final double salary;
    private final int experienceInYears;

    public Employee(int id, String name, String department, double salary, int experienceInYears) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.experienceInYears = experienceInYears;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }




    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Employee that = (Employee) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.department, that.department) &&
                Double.doubleToLongBits(this.salary) == Double.doubleToLongBits(that.salary) &&
                this.experienceInYears == that.experienceInYears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department, salary, experienceInYears);
    }

    @Override
    public String toString() {
        return "Employee[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "department=" + department + ", " +
                "salary=" + salary + ", " +
                "experienceInYears=" + experienceInYears + ']';
    }


}

