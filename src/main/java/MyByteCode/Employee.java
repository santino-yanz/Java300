package MyByteCode;

/**
 * @Classname Employee
 * @Description TODO
 * @Date 6/10/20 23:22
 * @Created by mcy
 */

@Author(name="yanz", year=2010)
public class Employee {

    private String name;
    private int age;

    public Employee() {
        super();
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void sayHello() {
        System.out.println("What's up?");
    }
}
