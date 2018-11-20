package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class TableManager {
    public String create(String className) {
        String query = "";
        try {
            Class<?> cls = Class.forName(className);
            DBTable dbTable = cls.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println("DBTable annotation not found!");
            }

            String tableName = dbTable.name();
            if(tableName.length() < 1)
                tableName = cls.getName();


            for(Field field : cls.getDeclaredFields()) {
                Annotation[] anns = field.getDeclaredAnnotations();
                if(anns[0] instanceof SQLInteger) {
                    SQLInteger sqlInt = (SQLInteger) anns[0];
                    query += (sqlInt.name().equals("")) ? field.getName() : sqlInt.name();
                    query += " INT" + getConstraints(sqlInt.constraints()) + ", ";
                }
                else if(anns[0] instanceof SQLString) {
                    SQLString sqlStr = (SQLString) anns[0];
                    query += (sqlStr.name().equals("")) ? field.getName() : sqlStr.name();
                    query += " VARCHAR(" + sqlStr.value() + ")" + getConstraints(sqlStr.constraints()) + ", ";
                }
            }
            query = "DROP TABLE IF EXISTS " + tableName + "; CREATE TABLE " + tableName + " (" + query.substring(0, query.length() - 2) + ");";

        }
        catch(ClassNotFoundException exc) {
            System.out.println("Class not found");
        }
        return query;
    }

    public String insert(Object obj) {
        String query = "INSERT INTO ";
        try {
            Class<?> cls = Class.forName(obj.getClass().getName());
            DBTable dbTable = cls.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println("DBTable annotation not found!");
            }

            String tableName = dbTable.name();
            if (tableName.length() < 1)
                tableName = cls.getName();

            query += tableName + " VALUES (";

            for(Field field : cls.getDeclaredFields()) {
                query += "'" + field.get(obj) + "', ";
            }
            query = query.substring(0, query.length() - 2) + ");";
        }
        catch (ClassNotFoundException | IllegalAccessException exc) {
            exc.printStackTrace();
        }
        return query;
    }

    private String getConstraints(Constraints con) {
        String constraints = "";
        if (con.notNull()) constraints += " NOT NULL";
        if (con.unique()) constraints += " UNIQUE";
        if (con.primaryKey()) constraints += " PRIMARY KEY";
        return constraints;
    }
}
