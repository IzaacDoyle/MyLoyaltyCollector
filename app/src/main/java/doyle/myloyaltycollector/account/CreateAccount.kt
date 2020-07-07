package doyle.myloyaltycollector.account

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

import doyle.myloyaltycollector.StartUp_Screen_Start
import doyle.myloyaltycollector.R
import kotlinx.android.synthetic.main.create_account.*


class CreateAccount : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)
        mAuth = FirebaseAuth.getInstance()



        SignUp_SignUp.setOnClickListener {
            signUpUser()
        }

    }


    private fun signUpUser() {

        if (Signup_Email.text.toString().isEmpty()) {
            Signup_Email.error = "Please Enter a Valid Email"
            Signup_Email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Signup_Email.text.toString()).matches()) {
            Signup_Email.error = "Please Enter a Valid Email"
            Signup_Email.requestFocus()
            return

        }

        if (Signup_Password.text.toString().isEmpty()) {
            Signup_Password.error = "Please enter Valid Password"
            Signup_Password.requestFocus()
            return

        }

        mAuth.createUserWithEmailAndPassword(
            Signup_Email.text.toString(),
            Signup_Password.text.toString()
        )
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {

                    //When SignUp is done direct user back to login
                    startActivity(Intent(this, StartUp_Screen_Start::class.java))
                    finish()
                } else { // If sign in fails, display a message to the user.

                    Toast.makeText(
                        this, "Authentication failed. Please Try Again",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

    }
}