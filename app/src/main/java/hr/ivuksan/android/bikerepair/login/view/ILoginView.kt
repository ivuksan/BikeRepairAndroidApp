package hr.ivuksan.android.bikerepair.login.view

import com.google.firebase.auth.FirebaseUser

interface ILoginView {
    fun onLoginSuccess(message: String?, user: FirebaseUser)
    fun onLogintError(message: String?)
}