package ResultViewer

class SubjectExaminationController {
//    def beforeInterceptor=[action: this.&auth]

    def index() {

    }
    def getSubjectList(){
        def semester = params.Semester
        def examination = params.Examination
        println semester+examination
        def subjectList=SubjectExamination.findAllBySemesterAndExamination(params.Semester,params.Examination)
        println subjectList.fullMarks
        render (view: "PublishResult",model:[SubjectList:subjectList])
    }
    def auth(){
        if(session.getAttribute("Username")==null){
            redirect(controller:"admin", action:"Login")
            return false
        }
        else if(session.getAttribute("Role").equals("admin")){
            return true
        }else if(session.getAttribute("Role").equals("student")){
            redirect(controller:"student", action:"index")
            return true
        }else{
            redirect(controller:"admin", action:"Login")
            return false
        }

    }
}
