package hr.ivuksan.android.bikerepair.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import hr.ivuksan.android.bikerepair.databinding.LoginActivityBinding
import hr.ivuksan.android.bikerepair.login.controller.LoginController
import hr.ivuksan.android.bikerepair.login.view.ILoginView
import hr.ivuksan.android.bikerepair.main.MainActivity

class LoginActivity : AppCompatActivity(), ILoginView {

    lateinit var binding: LoginActivityBinding

    lateinit var loginController: LoginController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loginController = LoginController(this, applicationContext)

        binding.loginButton.setOnClickListener{
            if (isValid(binding.nameEditText.text.toString(), binding.passwordEditText.text.toString())){
                loginController.authenticateUser(" "," ")
            }else {
                loginController.authenticateUser(
                    "${binding.nameEditText.text}@firebase.com",
                    binding.passwordEditText.text.toString()
                )
            }

            binding.nameEditText.setText("")
            binding.passwordEditText.setText("")
        }
    }

    override fun onLoginSuccess(message: String?, user: FirebaseUser) {
        Toast.makeText(
            this@LoginActivity,
            message,
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLogintError(message: String?) {
        Toast.makeText(
            this@LoginActivity,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun isValid(user: String, pass: String): Boolean{
        return user.isEmpty() || pass.isEmpty()
    }
}