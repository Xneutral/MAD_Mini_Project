package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var updt_name: EditText
    private lateinit var userName: EditText
    private lateinit var updt_email: EditText
    private lateinit var updt_mobile: EditText
    private lateinit var updt_button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        userName= findViewById(R.id.user_name)
        updt_name=findViewById(R.id.updt_name)
        updt_email =findViewById(R.id.updt_email)
        updt_mobile = findViewById(R.id.updt_mobile)
        updt_button=findViewById(R.id.data)


       updt_button.setOnClickListener{
           val user= userName.text.toString().trim()
           val name = updt_name.text.toString().trim()
           val email = updt_email.text.toString().trim()
           val phoneNo = updt_mobile.text.toString().trim()


           updateData(user,name,email,phoneNo)
       }
    }

     private fun updateData(user:String, name:String, email:String, phoneNo:String) {
        val contact = mutableMapOf<String,Any>()
        contact["name"] = name
        contact["email"] = email
        contact["phoneNo"] = phoneNo
        FirebaseDatabase.getInstance().getReference("Contacts")
            .child(user).updateChildren(contact)
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    Toast.makeText(this,"Record Updated",Toast.LENGTH_SHORT).show()
                    userName.setText("")
                    updt_name.setText("")
                    updt_email.setText("")
                    updt_mobile.setText("")
                }
                else
                    Toast.makeText(this,"Update failed",Toast.LENGTH_SHORT).show()
            }
    }
}