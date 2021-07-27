import java.util.*;

class Student {
    private String name;
    private int dob;
    private int age;
    
    private int calculateAge(int dob) {
        return 2021 - dob;
    }

    public Student(String name, int dob) {
        this.name = name;
        this.dob = dob;
        this.age = calculateAge(this.dob);
    }

    public void printDetails() {
        System.out.println(this.name + " " + this.age + " " + this.dob);
    }

    public boolean changeName(String name) {
        this.name = name;
        return true;
    }
}

class College {
    private ArrayList<Student> students;
    private int key = 12345;

    public College() {
        this.students = new ArrayList<>(); // class 11th
    }

    public void getAdmission(String name, int dob, int key) {
        if(this.key == key) {
            if(2021 - dob >= 15) {
                students.add(new Student(name, dob));
                System.out.println(name + " is succesfully Admitted");
            } else {
                System.out.println(name + " is not eligible because of age");
            }
        } else {
            System.out.println("Invalid Key");
        }
    }

    public void ChangeName(int stID, String name, int key) {
        if(this.key == key) {
            if(stID >= 0 && stID < students.size()) {
                students.get(stID).changeName(name);
                System.out.println("Name is succesfully changed to " + name);
            } else {
                System.out.println("Invalid Student ID");
            }
        } else {
            System.out.println("Invalid Key");
        }
    }

    public void printStudents() {
        for(Student s : students) {
            s.printDetails();
        }
    }
}

public class game {
    public static void main(String[] args) {
        College pep = new College();

        pep.getAdmission("A", 1997, 12345);
        pep.getAdmission("B", 1997, 12345);
        pep.getAdmission("C", 2007, 12345);
        pep.getAdmission("D", 1997, 12355);
        pep.getAdmission("E", 1998, 12345);
        pep.getAdmission("F", 2006, 12345);
        pep.getAdmission("G", 1999, 12345);
        pep.getAdmission("H", 1997, 12365);
        pep.getAdmission("I", 1994, 12345);

        pep.printStudents();
        pep.ChangeName(1, "BB", 12345);
        pep.ChangeName(10, "GG", 12345);
        // pep.printStudents();

    }
}
