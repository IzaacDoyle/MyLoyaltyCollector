package doyle.myloyaltycollector


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import doyle.myloyaltycollector.account.CreateAccount

import kotlinx.android.synthetic.main.login_main.*


class StartUp_Screen_Start : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        mAuth = FirebaseAuth.getInstance()



        Login_Create_Account.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)

            setContentView(R.layout.create_account)
        }



        Login_Sign_In.setOnClickListener {
            doLogin()
        }


    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }


    private fun doLogin() {
        if (Login_Email.text.toString().isEmpty()) {
            Login_Email.error = "Please Enter a Valid Email"
            Login_Email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Login_Email.text.toString()).matches()) {
            Login_Email.error = "Please Enter a Valid Email"
            Login_Email.requestFocus()
            return

        }

        if (Login_Password.text.toString().isEmpty()) {
            Login_Password.error = "Please enter Valid Password"
            Login_Password.requestFocus()
            return

        }
        mAuth.signInWithEmailAndPassword(
            Login_Email.text.toString(),
            Login_Password.text.toString()
        )
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(
                        this, "Login failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }

            }
    }

    //user will always be logged in as of meta data
    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {

            startActivity(Intent(this, Main_Screen_Menu::class.java))

            finish()



        } else {
          //  Toast.makeText(baseContext, "Logged Out", Toast.LENGTH_SHORT).show()
        }


    }


}