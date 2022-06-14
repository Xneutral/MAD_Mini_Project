package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity()
{
    private lateinit var create_btn:Button
    private lateinit var view_btn:Button
    private lateinit var updt_btn:Button
    private lateinit var del_btn:Button
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        create_btn = findViewById(R.id.create_data)
        view_btn = findViewById(R.id.view_data)
        del_btn= findViewById(R.id.delete)
        updt_btn=findViewById(R.id.update)

        create_btn.setOnClickListener{
            val intent = Intent(this,DataActivity::class.java)
            startActivity(intent)
        }
        view_btn.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        updt_btn.setOnClickListener{
            val intent = Intent(this,UpdateActivity::class.java)
            startActivity(intent)
        }
        del_btn.setOnClickListener{
            val intent = Intent(this,DeleteActivity::class.java)
            startActivity(intent)
        }


    }
}