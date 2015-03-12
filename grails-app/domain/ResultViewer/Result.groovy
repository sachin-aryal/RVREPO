package ResultViewer

/**
 * Created by sachin on 2/3/2015.
 */
class Result {
    float marks
    static belongsTo = ['student':Student,'subjectExamination':SubjectExamination]

}
