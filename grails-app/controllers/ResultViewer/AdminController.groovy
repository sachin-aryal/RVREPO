package ResultViewer

class AdminController {


    def Login() {

    }

    def AdminHome() {
        //auth()
    }

    def createAccount() {
        //auth()
        def Create = "New Account"
        def userField = []
        for (int i = 1; i < 6; i++) {
            userField.add(message(code: 'user.field' + i))//Create New Account fields accessing from message.properties
        }

        render(view: 'AdminHome', model: [UserField: userField, create: Create])
    }

    def loginValidator() {
        def admin = Admin.findByUserNameAndPassword(params.Username, params.Password)
        def student = Student.findByUserNameAndPassword(params.Username, params.Password)
        if (admin) {
            println("User is admin")
            session.setAttribute("Username", admin.userName)
            session.setAttribute("Role", "admin")
            redirect(action: "AdminHome")
        } else if (student) {
            println "User is student"
            session.setAttribute("Username", student.userName)
            session.setAttribute("Rollno", student.rollno)
            session.setAttribute("Role", "student")
            redirect controller: 'student', action: 'index'
        } else {
            redirect(action: 'Login')
        }
    }

    def logout() {
        session.setAttribute("Username", null)
        render view: 'Login'


    }

    def contactUs() {

    }

    def aboutUs() {

    }

    def chart() {

    }
}