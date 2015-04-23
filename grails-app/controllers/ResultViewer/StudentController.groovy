package ResultViewer

import jxl.LabelCell
import jxl.NumberCell
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import org.springframework.dao.DataIntegrityViolationException

class StudentController {
//    def beforeInterceptor=[action: this.&auth]
    def studentControllerService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        render view: 'StudentHome'
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [studentInstanceList: Student.list(params), studentInstanceTotal: Student.count(),listType:"All"]

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
        println params.id
        def studentInstance = Student.findById(params.id)
        if (!studentInstance) {
            flash.message ="Not Found!!"
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
        if (Student.findById(params.id)) {
            def studentInstance=Student.findById(params.id)
            int id = params.id as Integer
            Map<String, List> map = studentControllerService.getStudentReport(id)
            def percentage = [16]
            def semester = [16]
            def examination = [16]
            percentage = map.get("percentage")
            semester = map.get("semester")
            examination = map.get("examination")
            println percentage
            [Percentage: percentage, Semester: semester, Examination: examination,StudentInstance: studentInstance]
        }
        else{
            redirect(action: 'StudentList',params: [batch: "All"])
        }
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
    def SearchStudent(Integer max){
        println "Ajax Call"
        def studentInstanceList
        if(params.query){
            println params.query
            params.max = Math.min(max ?: 10, 1000)
            try {
                Integer.parseInt(params.query)
                studentInstanceList = Student.findAllByRollnoIlike('%' +params.query + '%', params)
            } catch (NumberFormatException) {
                studentInstanceList = Student.findAllByNameIlike('%' + params.query + '%', params)
            }
        }
        else{
            redirect(action: 'list')
        }
        println studentInstanceList.name
        render(template: 'userList', model:[studentInstanceList:studentInstanceList,studentInstanceTotal: studentInstanceList.size()])
    }

    def auth() {
        println actionName+"-----"
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
*/
}






