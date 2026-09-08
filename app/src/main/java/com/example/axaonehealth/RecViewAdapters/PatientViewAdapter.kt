package com.example.axaonehealth.RecViewAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.axaonehealth.Activities.PatientDetailsActivity
import com.example.axaonehealth.DataClasses.Patient
import com.example.axaonehealth.R
class PatientViewAdapter(private val patients:List<Patient>) : RecyclerView.Adapter<PatientViewAdapter.PatientViewHolder>() {


    //Creating class for view holder
    class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name = itemView.findViewById<TextView>(R.id.PatientFullName)
        val department = itemView.findViewById<TextView>(R.id.PatientDepartment)
        val patientLastVisit = itemView.findViewById<TextView>(R.id.PatientLastVisit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.patient_item, parent, false)

        return PatientViewHolder(view)
    }


    //For each patient, take the data and assign it to the holder.
    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {

        val patient = patients[position]

        holder.name.text = patient.name
        holder.department.text = patient.department
        holder.patientLastVisit.text = "Last Visit: " + patient.lastVisit


        //putting a listner to hear for user click
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PatientDetailsActivity::class.java) //prepping intent to patient details acitvity

            //sending the data to the next intent (key value pair like usual)
            intent.putExtra("id",patient.id)
            intent.putExtra("name",patient.name)
            intent.putExtra("age",patient.age)
            intent.putExtra("department", patient.department)
            intent.putExtra("lastVisit", patient.lastVisit)
            intent.putExtra("isActive", patient.isActive)

            //starting the next intent (to patient details activity)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return patients.size
    }

}