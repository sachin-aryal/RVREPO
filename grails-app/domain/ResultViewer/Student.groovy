package ResultViewer
/**
 * Created by sachin on 2/3/2015.
 */
class Student {
    int rollno
    String name
    int batch
    String userName
    String password
    static constraints = {
        userName(unique: true)
        rollno(unique: true)
    }
    static hasMany = ['result',Result]
   /* static mapping={
        id column: "Rollno", //actual column name from db
                name: "rollno",   //used lower camelCase for field names
                type: 'integer',             //lower case
                generator: 'assigned'

    }*/
}

