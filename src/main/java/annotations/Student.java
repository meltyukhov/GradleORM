package annotations;

@DBTable(name = "student")
public class Student {
    @SQLInteger(constraints = @Constraints(primaryKey = true)) Integer id;
    @SQLString(30) String name;
    @SQLString(30) String surname;
    @SQLInteger Integer mark;

    public Student(int id, String name, String surname, int mark) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mark = mark;
    }
}
