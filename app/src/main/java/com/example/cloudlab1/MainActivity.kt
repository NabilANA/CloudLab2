package com.example.cloudlab1


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.cloudlab1.R.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore
    var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        var listBtn = findViewById<Button>(R.id.listBtn)
        listBtn.setOnClickListener {
            val intent = Intent(this , MainActivity2::class.java)
            startActivity(intent)
        }

        save.setOnClickListener {

            val database = Firebase.database
            val myRef = database.getReference()

            var name = PersonName.text.toString()
            var id = PersonId.text.toString()
            var age = PersonAge.text.toString()

            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )
            myRef.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext , "Success" , Toast.LENGTH_LONG).show()


            listBtn.setOnClickListener {
                myRef.addValueEventListener(object: ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        val value = snapshot.getValue()

                        //textData.text = value.toString()
                        Toast.makeText(applicationContext , "Success" , Toast.LENGTH_LONG).show()

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(applicationContext , "Failler" , Toast.LENGTH_LONG).show()

                    }

                })
            }

            db.collection("Person")
                .add(person)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(applicationContext , "${documentReference.id}" , Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext , "$e" , Toast.LENGTH_LONG).show()

                }




        }
    }
}