public class Employee {
    private int id;
    private String name;
    private int age;
    private String department;
    private double salary;
    private String performanceRating;

    public Employee(int id, String name, int age, String department, double salary, String performanceRating) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
        this.performanceRating = performanceRating;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getPerformanceRating() { return performanceRating; }
    public void setPerformanceRating(String performanceRating) { this.performanceRating = performanceRating; }
}
