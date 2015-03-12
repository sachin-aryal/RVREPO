package ResultViewer

/**
 * Created by sachin on 2/3/2015.
 */
class SubjectExamination {
    String subjectName
    int passMarks
    int fullMarks
    String examination
    String semester
    static hasMany = [result: Result]

}
