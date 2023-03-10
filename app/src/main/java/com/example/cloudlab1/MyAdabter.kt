package com.example.cloudlab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdabter(private val personList : ArrayList<Person>): RecyclerView.Adapter<MyAdabter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item ,
        parent, false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val person: Person = personList[position]
        holder.personName.text = person.name.toString()
        holder.personId.text = person.id.toString()
        holder.personAge.text = person.age.toString()
    }

    override fun getItemCount(): Int {
       return personList.size
    }
    public class  MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        val personName : TextView = itemView.findViewById(R.id.PersonName)
        val personId : TextView = itemView.findViewById(R.id.PersonId)
        val personAge : TextView = itemView.findViewById(R.id.PersonAge)


    }
}