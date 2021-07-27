import java.util.ArrayList;

class student {
    private String name;
    private int dob;
    private int age;

    private int calculateAge(int dob) {
        return 2021 - dob;
    }

    public student(String name, int dob) {
        this.name = name;
        this.dob = dob;
        this.age = calculateAge(dob);
    }

    public void allowedChange(String name) {
        this.name = name;
    }

    public void printDetails() {
        System.out.println(this.name + " " + this.age +" " + this. dob);
    }
}

class college {
    private int key = 123456;

    private ArrayList<student> students = new ArrayList<>();

    public boolean takeAdmission(String name, int dob, int key) {
        if(2021 - dob > 5 && this.key == key) {
            students.add(new student(name, dob));
            System.out.println("Succesfully admited : " + name);
            return true;
        } else {
            System.out.println("Invalid age OR Invalid key " + name);
            return false;
        }
    }

    public void printStudents() {
        for(student s : students) {
            s.printDetails();
        }
    }

    public boolean changeInData(int sid, String name, int key) {
        if(key == this.key) {
            students.get(sid).allowedChange(name);
            return true;
        } else {
            System.out.println("Invalid key");
            return false;
        }
    }
}

public class studentProblem {
    public static void main(String[] args) {
        // ArrayList<student> college = new ArrayList<>();

        // college.add(new student("A", 1998));
        // college.add(new student("B", 1997));
        // college.add(new student("C", 1999));
        // college.add(new student("D", 1996));

        // college.get(2).allowedChange("C2");

        // for(student s : college) {
        //     s.printDetails();
        // }

        college c = new college();
        int key = 123456;
        c.takeAdmission("A", 1998, key);
        c.takeAdmission("B", 1996, key);
        c.takeAdmission("C", 2018, key);
        c.takeAdmission("D", 2000, 12546);
        c.takeAdmission("E", 2020, key);
        c.takeAdmission("F", 2015, 123456);
        

        c.printStudents();
        c.changeInData(1, "H", 123456);
        c.printStudents();
    }
}
