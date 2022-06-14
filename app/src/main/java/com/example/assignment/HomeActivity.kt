package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.adapter.MyAdapter
import com.example.assignment.datamodel.DataModel
import com.google.firebase.database.*

class HomeActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var adpt: MyAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var userList : ArrayList<DataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.rV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userList = arrayListOf()
        getData()

    }

    private fun getData()
    {
        dbref = FirebaseDatabase.getInstance().getReference("Contacts")
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for (contact in snapshot.children)
                    {
                        val user = contact.getValue<DataModel>(DataModel::class.java)
                        if(user!=null)
                        {
                            userList.add(user)
                        }

                    }
                    adpt = MyAdapter(userList)
                    recyclerView.adapter = adpt
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}