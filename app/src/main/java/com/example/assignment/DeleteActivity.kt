package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var userName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        button = findViewById(R.id.del_btn)
        userName = findViewById(R.id.userName)


        button.setOnClickListener{
            val name=userName.text.toString().trim()
            FirebaseDatabase.getInstance().getReference("Contacts")
                .child(name)
                .removeValue().addOnCompleteListener {
                    Toast.makeText(this,"Data Deletion successful.",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this,"Data not found in the Database.",Toast.LENGTH_SHORT).show()
                }

        }
    }
}