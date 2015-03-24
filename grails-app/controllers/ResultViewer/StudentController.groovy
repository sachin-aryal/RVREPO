package ResultViewer

import jxl.LabelCell
import jxl.NumberCell
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import org.springframework.dao.DataIntegrityViolationException
import resultviewer.StudentService

class StudentController {
//    def beforeInterceptor=[action: this.&auth]


    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        render view: 'StudentHome'
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [studentInstanceList: Student.list(params), studentInstanceTotal: Student.count()]
    }

    def create() {
        [studentInstance: new Student(params)]
    }

    def save() {

        if (Student.findByRollno(params.rollno)) {
            flash.message = message(code: 'student.label', default: 'Rollno Already Exist!!')
        } else if (Student.findByUserName(params.userName)) {
            flash.message = message(code: 'student.label', default: 'Username Already Exist!!')
        } else {
            def studentInstance = new Student(params)
            if (!studentInstance.save(flush: true)) {
                render(view: "create", model: [studentInstance: studentInstance])
                return
            }

            flash.message = message(code: 'student.label', default: 'Account Created Successfully')

        }
        redirect(action: "create")
    }


    def edit() {
        def studentInstance = Student.findByRollno(params.id)
        if (!studentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'ResultViewer.Student'), params.Rollno])
            redirect(action: "list")
        }
        [studentInstance: studentInstance]
    }

    def update() {
        def studentInstance = Student.findById(params.id)
        if(studentInstance){
            studentInstance.properties = params
            flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'ResultViewer.Student'), studentInstance.rollno])
            redirect action: 'list'
        }
        else{
            redirect action: 'edit',params: [id:params.id]
        }
    }

    def delete() {
        def studentInstance = Student.findByRollno(params.id)
        if (!studentInstance) {
            render action: 'list'
        }
        try {
            studentInstance.delete(flush: true)
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Something went wrong!!"
            redirect(action: 'list')
        }

    }

    def getResult() {
        def studentInstance
        studentInstance = Student.findByRollno(session.getAttribute("Rollno"))
        if (studentInstance) {
            def resultInstanceList = []
            def subjectExaminationInstance = SubjectExamination.findAllBySemesterAndExamination(params.Semester, params.Examination)
            def subjectName = subjectExaminationInstance.subjectName

            for (int i = 0; i < subjectExaminationInstance.size(); i++) {
                resultInstanceList.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findBySubjectName(subjectName[i]), Student.findByRollno(session.getAttribute("Rollno"))))

            }
            render view: 'StudentResult', model: [StudentInstance: studentInstance, ResultInstanceList: resultInstanceList, SubjectExaminationInstance: subjectExaminationInstance, semester: (message(code: 'semester' + params.Semester)), examination: (message(code: 'examination' + params.Examination))]
        } else {
            render view: 'StudentHome'
        }
    }

    def StudentList(Integer max) {
        params.max = Math.min(max ?: 10, 1000)
        def batch = params.batch
        if (batch.equals("All")) {
            [studentInstanceList: Student.list(params), studentInstanceTotal: Student.list().size()]

        } else {
            [studentInstanceList: Student.findAllByBatch(params.batch, params), studentInstanceTotal: Student.findAllByBatch(params.batch).size()]
        }
    }

    def SearchUser(Integer max) {
        if(params.query){
            params.max = Math.min(max ?: 10, 1000)
            def studentInstanceList
            try {
                Integer.parseInt(params.query)
                studentInstanceList = Student.findAllByRollno(params.query, params)
            } catch (NumberFormatException) {
                studentInstanceList = Student.findAllByNameIlike('%' + params.query + '%', params)
            }
        }
        render view: 'list', model: [studentInstanceList: studentInstanceList, studentInstanceTotal: studentInstanceList.size()]

    }

    def StudentReport() {
        StudentService studentService =new StudentService()
        int id=params.id as Integer
        studentService.getList(id)
////        def percentage=map.get("percentage")
////        def Semester=map.get("semester")
////        def Examination=map.get("examination")
////        println percentage
//        [Percentage:percentage,Semester:Semester,Examination:Examination]
        /*[Percentage1:percentage1,Percentage2:percentage2,Percentage3:percentage3,Percentage4:percentage4,Percentage5:percentage5,
         Percentage6:percentage6,Percentage7:percentage7,Percentage8:percentage8,Percentage9:percentage9,Percentage10:percentage10,
         Percentage11:percentage11,Percentage12:percentage12,Percentage13:percentage13,Percentage14:percentage14,
         Percentage15:percentage15,Percentage16:percentage16]*/
    }
    def doUploadNewStudent() {
        def file = request.getFile('file')
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        // skip first row (row 0) by starting from 1
        for (int row = 1; row < sheet.getRows(); row++) {
            LabelCell Name =  sheet.getCell(0, row)
            NumberCell Rollno = sheet.getCell(1, row)
            NumberCell Batch =  sheet.getCell(2, row)
            LabelCell Username = sheet.getCell(3, row)
            LabelCell Password = sheet.getCell(4, row)
            new Student(name: Name.string, rollno: Rollno.value, batch: Batch.value, userName: Username.string, password: Password.string).save(flush: true)
        }
        redirect controller: 'student',action: 'list'
    }
    def exportStudentList() {
        response.setContentType('application/vnd.ms-excel')
        response.setHeader('Content-Disposition', 'Attachment;Filename="StudentList.xls"')
        WritableWorkbook workbook = Workbook.createWorkbook(response.outputStream)
        WritableSheet sheet1 = workbook.createSheet("Students", 0)
        sheet1.addCell(new Label(0, 0, "Name"))
        sheet1.addCell(new Label(1, 0, "Rollno"))
        sheet1.addCell(new Label(2, 0, "Batch"))
        sheet1.addCell(new Label(3, 0, "Username"))
        sheet1.addCell(new Label(4, 0, "Password"))
        int i=0;
        for (int row = 1; row < Student.list().size(); row++) {
            int columnNumber=0
            sheet1.addCell(new Label(columnNumber++,row,Student.list()[i].name))
            sheet1.addCell(new Label(columnNumber++,row,Student.list()[i].rollno.toString()))
            sheet1.addCell(new Label(columnNumber++,row,Student.list()[i].batch.toString()))
            sheet1.addCell(new Label(columnNumber++,row,Student.list()[i].userName))
            sheet1.addCell(new Label(columnNumber,row,Student.list()[i].password))
            i++;
        }
        workbook.write();
        workbook.close();
    }
    def String wrapSearchParm(value) {
        '%'+value+'%'
    }
    def SearchStudent(){
        println "Coming"
        def studentInstanceList =Student.findAllByNameIlike(wrapSearchParm(params.StdName))
        render(template: 'userList', model:[studentInstanceList:studentInstanceList])
    }




    def auth() {
        if (session.getAttribute("Username") == null) {
            redirect(controller: "admin", action: "Login")
            return false
        } else if (session.getAttribute("Role").equals("admin")) {
            return true
        } else if (session.getAttribute("Role").equals("student")) {
            return true
        } else {
            redirect(controller: "admin", action: "Login")
            return false
        }

    }
    /* def importStudent(){
         println "Is Here"
         String path = grailsAttributes.getApplicationContext()
                 .getResource.request.getFile('file').toString()
         def f = new File(path)
         String fileName,sheetName=params.sheetName
         println f.name
         if( f.exists() ){
             f.eachFile(){ file->
                 if( !file.isDirectory() )
                 fileName=file.name
                 StudentExcelImporter importer = new StudentExcelImporter("data/"+fileName);
                 List studentList = importer.getStudents(sheetName);
             }
         }

         def report(){
         def studentInstance=Student.findById(params.id)
        def percentage=[16],Semester=[],Examination=[]
        if(studentInstance){
            def subjectExamination = []
            int semester = 1
            for (int i = 0; i < 8; i++) {
                subjectExamination[i] = SubjectExamination.findAllBySemesterAndExamination(semester, 1)
//subjectExamination[0] is first semester and Midterm
                semester++
            }
            semester = 1
            for (int i = 8; i < 16; i++) {
                subjectExamination[i] = SubjectExamination.findAllBySemesterAndExamination(semester, 2)
//ResultViewer.SubjectExamination[8] is First semester and Preboard
                semester++

            }
            //Now Getting all the record realatret to the student Id. ResultViewer.Result goes from Semester first Midterm to Semester 8 Preboard
            def resultSem1Mid = []
            for (int i = 0; i < subjectExamination[0].size(); i++) {
                resultSem1Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[0].id[i]), Student.findById(params.id)))
            }
            def resultSem1Pre = []
            for (int i = 0; i < subjectExamination[8].size(); i++) {
                resultSem1Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[8].id[i]), Student.findById(params.id)))
            }
            def resultSem2Mid = []
            for (int i = 0; i < subjectExamination[1].size(); i++) {
                resultSem2Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[1].id[i]), Student.findById(params.id)))
            }
            def resultSem2Pre = []
            for (int i = 0; i < subjectExamination[9].size(); i++) {
                resultSem2Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[9].id[i]), Student.findById(params.id)))
            }
            def resultSem3Mid = []
            for (int i = 0; i < subjectExamination[2].size(); i++) {
                resultSem3Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[2].id[i]), Student.findById(params.id)))
            }
            println resultSem3Mid
            def resultSem3Pre = []
            for (int i = 0; i < subjectExamination[10].size(); i++) {
                resultSem3Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[10].id[i]), Student.findById(params.id)))
            }
            def resultSem4Mid = []
            for (int i = 0; i < subjectExamination[3].size(); i++) {
                resultSem4Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[3].id[i]), Student.findById(params.id)))
            }
            def resultSem4Pre = []
            for (int i = 0; i < subjectExamination[11].size(); i++) {
                resultSem4Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[11].id[i]), Student.findById(params.id)))
            }
            def resultSem5Mid = []
            for (int i = 0; i < subjectExamination[4].size(); i++) {
                resultSem5Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[4].id[i]), Student.findById(params.id)))
            }
            def resultSem5Pre = []
            for (int i = 0; i < subjectExamination[12].size(); i++) {
                resultSem5Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[12].id[i]), Student.findById(params.id)))
            }
            def resultSem6Mid = []
            for (int i = 0; i < subjectExamination[5].size(); i++) {
                resultSem6Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[5].id[i]), Student.findById(params.id)))
            }
            def resultSem6Pre = []
            for (int i = 0; i < subjectExamination[13].size(); i++) {
                resultSem6Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[13].id[i]), Student.findById(params.id)))
            }
            def resultSem7Mid = []
            for (int i = 0; i < subjectExamination[6].size(); i++) {
                resultSem7Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[6].id[i]), Student.findById(params.id)))
            }
            def resultSem7Pre = []
            for (int i = 0; i < subjectExamination[14].size(); i++) {
                resultSem7Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[14].id[i]), Student.findById(params.id)))
            }
            def resultSem8Mid = []
            for (int i = 0; i < subjectExamination[7].size(); i++) {
                resultSem8Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[7].id[i]), Student.findById(params.id)))
            }
            def resultSem8Pre = []
            for (int i = 0; i < subjectExamination[15].size(); i++) {
                resultSem8Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[15].id[i]), Student.findById(params.id)))
            }

            if (resultSem1Mid.get(0)!=null) {
                def fullMarks = subjectExamination[0].fullMarks.sum()
                def marks = resultSem1Mid.marks.sum()
                percentage[0] = (marks / fullMarks) * 100
                Semester[0] = "Semester I"
                Examination[0] = "Midterm"
            }
            if (resultSem1Pre.get(0)!=null) {
                def fullMarks = subjectExamination[8].fullMarks.sum()
                def marks = resultSem1Pre.marks.sum()
                percentage[1] = (marks / fullMarks) * 100
                Semester[1] = "Semester I"
                Examination[1] = "Preboard"
            }
            if (resultSem2Mid.get(0)!=null) {
                def fullMarks = subjectExamination[1].fullMarks.sum()
                def marks = resultSem2Mid.marks.sum()
                percentage[2] = (marks / fullMarks) * 100
                Semester[2] = "Semester II"
                Examination[2] = "Midterm"
            }
            if (resultSem2Pre.get(0)!=null) {
                def fullMarks = subjectExamination[9].fullMarks.sum()
                def marks = resultSem2Pre.marks.sum()
                percentage[3] = (marks / fullMarks) * 100
                Semester[3] = "Semester II"
                Examination[3] = "Preboard"
            }
            if (resultSem3Mid.get(0)!=null) {
                def fullMarks = subjectExamination[2].fullMarks.sum()
                def marks = resultSem3Mid.marks.sum()
                percentage[4] = (marks / fullMarks) * 100
                Semester[4] = "Semester III"
                Examination[4] = "Midterm"
            }
            if (resultSem3Pre.get(0)!=null) {
                def fullMarks = subjectExamination[10].fullMarks.sum()
                def marks = resultSem1Pre.marks.sum()
                percentage[5] = (marks / fullMarks) * 100
                Semester[5] = "Semester III"
                Examination[5] = "Preboard"
            }
            if (resultSem4Mid.get(0)!=null) {
                def fullMarks = subjectExamination[3].fullMarks.sum()
                def marks = resultSem4Mid.marks.sum()
                percentage[6] = (marks / fullMarks) * 100
                Semester[6] = "Semester IV"
                Examination[6] = "Midterm"
            }
            if (resultSem4Pre.get(0)!=null) {
                def fullMarks = subjectExamination[11].fullMarks.sum()
                def marks = resultSem4Pre.marks.sum()
                percentage[7] = (marks / fullMarks) * 100
                Semester[7] = "Semester IV"
                Examination[7] = "Preboard"
            }
            if (resultSem5Mid.get(0)!=null) {
                def fullMarks = subjectExamination[4].fullMarks.sum()
                def marks = resultSem5Mid.marks.sum()
                percentage[8] = (marks / fullMarks) * 100
                Semester[8] = "Semester V"
                Examination[8] = "Midterm"
            }
            if (resultSem5Pre.get(0)!=null) {
                def fullMarks = subjectExamination[12].fullMarks.sum()
                def marks = resultSem5Pre.marks.sum()
                percentage[9] = (marks / fullMarks) * 100
                Semester[9] = "Semester V"
                Examination[9] = "Preboard"
            }
            if (resultSem6Mid.get(0)!=null) {
                def fullMarks = subjectExamination[5].fullMarks.sum()
                def marks = resultSem6Mid.marks.sum()
                percentage[10] = (marks / fullMarks) * 100
                Semester[10] = "Semester VI"
                Examination[10] = "Midterm"
            }
            if (resultSem6Pre.get(0)!=null) {
                def fullMarks = subjectExamination[13].fullMarks.sum()
                def marks = resultSem6Pre.marks.sum()
                percentage[11] = (marks / fullMarks) * 100
                Semester[11] = "Semester VI"
                Examination[11] = "Preboard"
            }
            if (resultSem7Mid.get(0)!=null) {
                def fullMarks = subjectExamination[6].fullMarks.sum()
                def marks = resultSem7Mid.marks.sum()
                percentage[12] = (marks / fullMarks) * 100
                Semester[12] = "Semester VII"
                Examination[12] = "Midterm"
            }
            if (resultSem7Pre.get(0)!=null) {
                def fullMarks = subjectExamination[14].fullMarks.sum()
                def marks = resultSem7Pre.marks.sum()
                percentage[13] = (marks / fullMarks) * 100
                Semester[13] = "Semester VII"
                Examination[13] = "Preboard"
            }
            if (resultSem8Mid.get(0)!=null) {
                def fullMarks = subjectExamination[7].fullMarks.sum()
                def marks = resultSem8Mid.marks.sum()
                percentage[14] = (marks / fullMarks) * 100
                Semester[14] = "Semester VIII"
                Examination[14] = "Midterm"
            }
            if (resultSem8Pre.get(0)!=null) {
                def fullMarks = subjectExamination[15].fullMarks.sum()
                def marks = resultSem8Pre.marks.sum()
                percentage[15] = (marks / fullMarks) * 100
                Semester[15] = "Semester VIII"
                Examination[15] = "Preboard"
            }
        }


         }

     }*/
}






