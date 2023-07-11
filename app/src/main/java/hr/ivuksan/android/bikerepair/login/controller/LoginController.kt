package hr.ivuksan.android.bikerepair.login.controller

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import hr.ivuksan.android.bikerepair.login.view.ILoginView

class LoginController(var loginView: ILoginView, var context: Context): ILoginController {

    lateinit var auth: FirebaseAuth

    override fun authenticateUser(fName: String?, password: String?) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(fName!!, password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val user: FirebaseUser? = auth.currentUser
                    if (user != null) {
                        loginView.onLoginSuccess("Successfull login", user)
                    }
                } else {
                    loginView.onLogintError("Authentication failed.")
                }
            }
    }
}