package ResultViewer

import jxl.NumberCell
import jxl.Sheet
import jxl.Workbook

class ResultController {
    //def beforeInterceptor=[action: this.&auth]

    def index() {
        render view: 'ViewResult'
    }

    def PublishResult() {
        def semester = params.Semester
        def examination = params.Examination
//        ChartService chartService=new ChartService()
        println semester + examination
        def SubjectName
        def subjectExamination = SubjectExamination.findAllBySemesterAndExamination(semester, examination)
        SubjectName = subjectExamination.subjectName
        float[] marks = new float[SubjectName.size()]
        for (int i = 0; i < SubjectName.size(); i++) {
            marks[i] = params.get(SubjectName[i]) as Float
            println "------------------"
            println SubjectName[i]
            println marks[i]
        }
        for (int i = 0; i < SubjectName.size(); i++) {
            def resultInstance = new Result()
            resultInstance.subjectExamination = SubjectExamination.findById(subjectExamination.id[i])
            resultInstance.marks = marks[i]
            resultInstance.student = Student.findByRollno(params.Rollno)
            if (!resultInstance.save(flush: true)) {
                flash.message = "Oops!! Some Error occurred Try Again"
            } else {
                flash.message = "ResultViewer.Result is successfully saved"
            }
        }
//        chartService.createChart(marks,SubjectName)

        forward(controller: "subjectExamination", action: "getSubjectList", params: [semester: semester, examination: examination])

    }

    def ViewResult() {
        def studentInstance
        if (params.RollNo) {
            studentInstance = Student.findByRollno(params.RollNo)
            if (studentInstance) {
                def resultInstanceList = []
                println params.Semester + params.Examination + "Semester and Examination"
                println "-------------"
                def subjectExaminationInstance = SubjectExamination.findAllBySemesterAndExamination(params.Semester, params.Examination)

                for (int i = 0; i < subjectExaminationInstance.size(); i++) {
                    println subjectExaminationInstance.id[i]
                    resultInstanceList.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExaminationInstance.id[i]), Student.findByRollno(params.RollNo)))
                }
                render view: 'ViewResult', model: [StudentInstance: studentInstance, ResultInstanceList: resultInstanceList, SubjectExaminationInstance: subjectExaminationInstance, semester: (message(code: 'semester' + params.Semester)), examination: (message(code: 'examination' + params.Examination))]
            }
        }

    }

    def edit() {
        def resultInstanceList = []
        def SubjectExaminationInstance = SubjectExamination.findAllBySemesterAndExamination(params.Semester, params.Examination)
        def count = SubjectExaminationInstance.size()
        def studentInstance = Student.findById(params.id)
        println studentInstance.id
        for (int i = 0; i < count; i++) {
            resultInstanceList.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(SubjectExaminationInstance.id[i]), Student.findById(studentInstance.id)))
        }
        if (resultInstanceList) {
            println "Contains Some Value"
        }
        render view: 'editMarks', model: [StudentInstance: studentInstance, ResultInstanceList: resultInstanceList, SubjectExaminationInstance: SubjectExaminationInstance]


    }

    def update() {
        if (params.Semester && params.Examination) {
            println "Inside First Loop"
            println params.id
            println "semester" + params.Semester + "and Examination" + params.Examination
            def SubjectExaminationInstance = SubjectExamination.findAllBySemesterAndExamination(params.Semester, params.Examination)
            def count = SubjectExaminationInstance.size()
            def subjectName = SubjectExaminationInstance.subjectName
            for (int i = 0; i < count; i++) {
                def resultList = new Result()
                resultList = (Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(SubjectExaminationInstance.id[i]), Student.findById(params.id)))
                resultList.delete()
            }
            float[] marks = new float[count]
            for (int i = 0; i < count; i++) {
                marks[i] = params.get(subjectName[i]) as Float
            }
            for (int i = 0; i < count; i++) {
                def resultInstance = new Result()
                resultInstance.subjectExamination = SubjectExamination.findById(SubjectExaminationInstance.id[i])
                resultInstance.marks = marks[i]
                resultInstance.student = Student.findByRollno(params.EditedRollno)
                if (!resultInstance.save(flush: true)) {
                    flash.message = "Oops!! Some Error occurred Try Again"
                } else {
                    flash.message = "ResultViewer.Result is successfully saved"
                }
            }
            def studentInstanceList = Student.findByRollno(params.EditedRollno)
            if (studentInstanceList) {
                println "Inside second Loop"

                def resultInstanceList = []
                for (int i = 0; i < count; i++) {
                    println "SubjectId" + SubjectExaminationInstance.id[i]
                    resultInstanceList.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(SubjectExaminationInstance.id[i]), Student.findById(studentInstanceList.id)))
                    println "Marks" + resultInstanceList.marks
                }
                render view: 'ViewResult', model: [StudentInstance: studentInstanceList, ResultInstanceList: resultInstanceList, SubjectExaminationInstance: SubjectExaminationInstance, semester: (message(code: 'semester' + params.Semester)), examination: (message(code: 'examination' + params.Examination))]

            }
        }

    }

    def delete() {
        def SubjectExaminationInstance = SubjectExamination.findAllBySemesterAndExamination(params.Semester, params.Examination)
        def count = SubjectExaminationInstance.size()
        def subjectName = SubjectExaminationInstance.subjectName
        Result[] resultList = new Result[count]
        for (int i = 0; i < count; i++) {
            resultList[i] = (Result.findBySubjectExaminationAndStudent(SubjectExamination.findBySubjectName(subjectName[i]), Student.findByRollno(params.id)))
            resultList[i].delete()
        }
        render view: 'ViewResult'
    }

    def importResult() {
//        StudentExcelImporter studentExcelImporter=new StudentExcelImporter(request.getFile)
        println "Import ResultViewer.Result"
        def subjectExamination = SubjectExamination.findAllBySemesterAndExamination(params.Semester, params.Examination)
        def file = request.getFile('file')
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        println sheet.getColumns()
        // skip first row (row 0) by starting from 1
        for (int row = 1; row < sheet.getRows(); row++) {
            /*def marks=[sheet.getColumns()-1]*/
            NumberCell Rollno = sheet.getCell(0, row)
            for (int column = 1; column < sheet.getColumns(); column++) {
                println "Inside the column loop"
                NumberCell Marks = sheet.getCell(column, row)
                def resultInstance = new Result()
                resultInstance.subjectExamination = SubjectExamination.findById(subjectExamination.id[column - 1])
                resultInstance.marks = Marks.value
                resultInstance.student = Student.findByRollno(Rollno.value)
                if (!resultInstance.save(flush: true)) {
                    flash.message = "Oops!! Some Error occurred Try Again"
                } else {
                    flash.message = "ResultViewer.Result is successfully saved"
                }
            }


        }

    }
    def exportService()
    {
        def grailsApplication  //inject GrailsApplication
        def list = {
            if (!params.max) params.max = 10

            if (params?.format && params.format != "html") {
                response.contentType = grailsApplication.config.grails.mime.types[params.format]
                response.setHeader("Content-disposition", "attachment; filename=books.${params.extension}")

                exportService.export(params.format, response.outputStream, Student.list(params), [:], [:])
            }


        }

    }
}

/*    all(controller:'*', action:'"\\\\b(?!loginValidator\\\\b)\\\\w+"') {
        before = {
            if(session.getAttribute("Username")==null){
                redirect(controller:"admin", action:"Login")
                return false
            }
            else if(session.getAttribute("Role").equals("admin")){
                return true
            }else if(session.getAttribute("Role").equals("student")){
                redirect(controller:"student", action:"index")
            }else{
                redirect(controller:"admin", action:"Login")
                return false
            }
        }*/
