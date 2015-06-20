package ResultViewer

import grails.transaction.Transactional

@Transactional
class StudentControllerService {

    def serviceMethod() {

    }
    def Map<String,List> getStudentReport(int id){
        println "Getting"
        def percentage=[],Semester=[],Examination=[]
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
        println id
        //Now Getting all the record realatret to the student Id. ResultViewer.Result goes from Semester first Midterm to Semester 8 Preboard
            def resultSem1Mid = []
            for (int i = 0; i < subjectExamination[0].size(); i++) {
                resultSem1Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[0].id[i]), Student.findById(id)))
            }
            def resultSem1Pre = []
            for (int i = 0; i < subjectExamination[8].size(); i++) {
                resultSem1Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[8].id[i]), Student.findById(id)))
            }
            def resultSem2Mid = []
            for (int i = 0; i < subjectExamination[1].size(); i++) {
                resultSem2Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[1].id[i]), Student.findById(id)))
            }
            def resultSem2Pre = []
            for (int i = 0; i < subjectExamination[9].size(); i++) {
                resultSem2Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[9].id[i]), Student.findById(id)))
            }
            def resultSem3Mid = []
            for (int i = 0; i < subjectExamination[2].size(); i++) {
                resultSem3Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[2].id[i]), Student.findById(id)))
            }
            def resultSem3Pre = []
            for (int i = 0; i < subjectExamination[10].size(); i++) {
                resultSem3Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[10].id[i]), Student.findById(id)))
            }
            def resultSem4Mid = []
            for (int i = 0; i < subjectExamination[3].size(); i++) {
                resultSem4Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[3].id[i]), Student.findById(id)))
            }
            def resultSem4Pre = []
            for (int i = 0; i < subjectExamination[11].size(); i++) {
                resultSem4Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[11].id[i]), Student.findById(id)))
            }
            def resultSem5Mid = []
            for (int i = 0; i < subjectExamination[4].size(); i++) {
                resultSem5Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[4].id[i]), Student.findById(id)))
            }
            def resultSem5Pre = []
            for (int i = 0; i < subjectExamination[12].size(); i++) {
                resultSem5Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[12].id[i]), Student.findById(id)))
            }
            def resultSem6Mid = []
            for (int i = 0; i < subjectExamination[5].size(); i++) {
                resultSem6Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[5].id[i]), Student.findById(id)))
            }
            def resultSem6Pre = []
            for (int i = 0; i < subjectExamination[13].size(); i++) {
                resultSem6Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[13].id[i]), Student.findById(id)))
            }
            def resultSem7Mid = []
            for (int i = 0; i < subjectExamination[6].size(); i++) {
                resultSem7Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[6].id[i]), Student.findById(id)))
            }
            def resultSem7Pre = []
            for (int i = 0; i < subjectExamination[14].size(); i++) {
                resultSem7Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[14].id[i]), Student.findById(id)))
            }
            def resultSem8Mid = []
            for (int i = 0; i < subjectExamination[7].size(); i++) {
                resultSem8Mid.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[7].id[i]), Student.findById(id)))
            }
            def resultSem8Pre = []
            for (int i = 0; i < subjectExamination[15].size(); i++) {
                resultSem8Pre.add(Result.findBySubjectExaminationAndStudent(SubjectExamination.findById(subjectExamination[15].id[i]), Student.findById(id)))
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
            }/*
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
            }*/
        Map<String,List>map =new HashMap<String,List>();
        map.put("percentage",percentage)
        map.put("semester",Semester)
        map.put("examination",Examination)
        return map
        }

}
