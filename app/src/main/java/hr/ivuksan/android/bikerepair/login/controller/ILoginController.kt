package hr.ivuksan.android.bikerepair.login.controller

interface ILoginController {
    fun authenticateUser(fName: String?, password: String?)
}