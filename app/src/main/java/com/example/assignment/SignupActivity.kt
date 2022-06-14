package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var cPass:EditText
    private lateinit var fAuth:FirebaseAuth
    private lateinit var signup_btn: Button
    private lateinit var login_text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        fAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.email)
        pass = findViewById(R.id.password)
        cPass = findViewById(R.id.c_password)
        signup_btn = findViewById(R.id.signup_button)
        login_text =findViewById(R.id.login_text)

        //signup button listener
        signup_btn.setOnClickListener{
            val email_id=email.text.toString().trim()
            val passcode = pass.text.toString().trim()
            val cPasscode = cPass.text.toString().trim()
            if(TextUtils.isEmpty(email_id)){
                email.error = "Email is required"
            }
            else if(TextUtils.isEmpty(passcode)){
                pass.error = "Password is required"
            }
            else if(passcode.length < 6)
                pass.error = "Password must be more than 6 characters"
            else if(passcode!=cPasscode)
                pass.error = "Password doesn't match"
            else
            {
                fAuth.createUserWithEmailAndPassword(email_id,passcode).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "User ID created successfully.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignupActivity,LoginActivity::class.java)
                        intent.putExtra("email_address",email_id)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "Error! " + it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        login_text.setOnClickListener{
            val intent = Intent(this@SignupActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }

}
