package com.example.cloudlab1

import android.graphics.ColorSpace.Model
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import java.util.*
import kotlin.collections.ArrayList

class MainActivity2 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var personArryList: ArrayList<Person>
    private lateinit var myAdabter: MyAdabter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        personArryList = arrayListOf()

        myAdabter = MyAdabter(personArryList)

        recyclerView.adapter = myAdabter

        EventChangeListener()

    }
    private  fun   EventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("Person").
        addSnapshotListener(object :
            com.google.firebase.firestore.EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null){
                    Log.e("Firestore Error" , error.message.toString())
                    return
                }
                for (dc : DocumentChange in value?.documentChanges!!){

                    if (dc.type == DocumentChange.Type.ADDED){

                        personArryList.add(dc.document.toObject(Person::class.java))

                    }

                }

                myAdabter.notifyDataSetChanged()
            }


        })


    }
}