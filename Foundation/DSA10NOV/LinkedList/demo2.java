class Person {
    public String name;
    public int age;
    public double ht;

    // default constructor
    public Person() {
        this("A", 10, 5.1);
        System.out.println("Hello i'm inside default constructor");
    }

    // copy constructor;
    public Person(Person p) {
    }

    // parameterized consrtuctor
    private Person(String name, int age, double ht) {
        System.out.println("Hello i'm inside parameterized constructor");
        this.name = name;
        this.age = age;
        this.ht = ht;
    }

    public void printDetails() {
        System.out.println(this.name + " " + this.age + " " + this.ht);
    }

}

public class demo2 {
    public static void main(String[] args) {
        Person p1 = new Person();
        System.out.println("I'm in main method");
        p1.printDetails();

        // Person p2 = new Person("Shreesh", 22, 6.1);
        // p2.printDetails();

    }
}
