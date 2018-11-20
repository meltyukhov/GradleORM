import annotations.Student;
import annotations.TableManager;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        DBManager dbmanager = new DBManager("src/main/resources/db.properties");
        TableManager tm = new TableManager();
        dbmanager.create(tm.create("annotations.Student"));
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Ivan", "Ivanov", 4));
        studentList.add(new Student(2, "Petro", "Lysyak", 5));
        studentList.add(new Student(3, "Danil", "Orlov", 4));
        studentList.add(new Student(4, "Nikita", "Rak", 3));
        studentList.add(new Student(5, "Sophia", "Vus", 2));
        for (Student st : studentList)
            dbmanager.insert(tm.insert(st));
        //annotations.Student st;
    }
}
