package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.view.*
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var login_btn:Button
    private lateinit var register_text:TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var fAuth: FirebaseAuth
    private lateinit var email_id:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email = findViewById(R.id.login_email)
        pass = findViewById(R.id.login_password)
        fAuth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.pgBar)
        login_btn= findViewById(R.id.login_btn)
        register_text = findViewById(R.id.register_txt)



        val intent = intent
        val str= intent.getStringExtra("email_address")
        email.setText(str)


        login_btn.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            email_id=email.text.toString().trim()
            val password =pass.text.toString().trim()
            if(TextUtils.isEmpty(email_id)){
                email.error = "Email is required"
            }
            if(TextUtils.isEmpty(password)){
                pass.error = "Password is required"
            }
            else{
            fAuth.signInWithEmailAndPassword(email_id,password).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    Toast.makeText(this,"Log in successful",Toast.LENGTH_SHORT).show()
                    val intnt = Intent(this, MainActivity::class.java)
                    startActivity(intnt)
                }
                else {
                    Toast.makeText(this, "Error! " + it.exception?.message, Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.INVISIBLE

                }
            }
        } }

        register_text.setOnClickListener{
            val itt = Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(itt)
        }
}
}