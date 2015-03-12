package ResultViewer

/**
 * Created by sachin on 2/3/2015.
 */
class Admin {
    String userName
    String password
    static constraints = {
        userName(unique: true,blank: false)
        password(blank: false)
    }
}
