package ResultViewer

class AdminController {
    def beforeInterceptor=[action: this.&auth]

    def Login() {

    }

    def AdminHome() {

    }

    def createAccount() {
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
            session.setAttribute("id", Student.findByRollno(student.rollno).id)
            session.setAttribute("Role", "student")
            redirect controller: 'student', action: 'index'
        } else {
            redirect(action: 'Login')
        }
    }

    def logout() {
        session.setAttribute("Username", null)
        session.setAttribute("Role", "")
        render view: 'Login'


    }

    def contactUs() {

    }
    def sendEmail(){
        def nameR = params?.name
        def rollnoR = params?.rollno
        def emailR = params?.email
        def subjectR = params?.subject
        def messageR = params?.message

        def bodyOfEmail = String.format("%s",

                "Name      : " + nameR
                        + "\rPhone     : " + rollnoR
                        + "\rEmail      : " + emailR
                        + "\rMessage : " + messageR
        )
        println "Working"

        try{
        sendMail{
            to 'sachin91719@gmail.com'
            println "sending"
            subject subjectR
            body bodyOfEmail
            println "sending1"
        }
    }catch(Exception e){
             e.printStackTrace()
        }
        render view:'contactUs'
    }

    def aboutUs() {

    }
    def auth(){
        println actionName+"-----"
        if(actionName.equalsIgnoreCase("loginValidator")||actionName.equalsIgnoreCase("Login")||actionName.equalsIgnoreCase("logout")
                ||actionName.equalsIgnoreCase("ContactUs")||actionName.equalsIgnoreCase("AboutUs")){
                return true
        }
        else{
            if(session.getAttribute("Username")!=null&&session.getAttribute("Role").equals("admin")){
                return true
            }
            else if(session.getAttribute("Username")!=null&&session.getAttribute("Role").equals("student")){
                flash.message="Access Denied!!Don't try to break"
                redirect controller: 'student', action: 'index'
            }
            else{
                redirect action: 'Login'
            }
        }
    }

}