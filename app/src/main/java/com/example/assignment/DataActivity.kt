package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.assignment.datamodel.DataModel
import com.google.firebase.database.*


class DataActivity : AppCompatActivity() {
    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var mobile:EditText
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var save_button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        name=findViewById(R.id.name)
        email =findViewById(R.id.email)
        mobile = findViewById(R.id.mobile)
        save_button=findViewById(R.id.data)

        database=FirebaseDatabase.getInstance()
        reference= database.getReference("Contacts")

        save_button.setOnClickListener {
            val a = name.text.toString().trim()
            val b = email.text.toString().trim()
            val c = mobile.text.toString().trim()
            if (a.isEmpty()) {
                name.error = "Name field is required"
            }
            if (b.isEmpty()) {
                email.error = "Email field is required"
            }
            if (c.isEmpty()) {
                mobile.error = "Phone Number field is required"
            } else {
                val model = DataModel(a, b, c)
                reference.child(a).setValue(model).addOnSuccessListener {
                    Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
                    name.setText("")
                    email.setText("")
                    mobile.setText("")
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to add data.", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}