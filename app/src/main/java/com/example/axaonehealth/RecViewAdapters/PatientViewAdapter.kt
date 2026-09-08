package com.example.axaonehealth.RecViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.axaonehealth.DataClasses.Patient
import com.example.axaonehealth.R
class PatientViewAdapter(private val patients:List<Patient>) : RecyclerView.Adapter<PatientViewAdapter.PatientViewHolder>() {


    //Creating class for view holder
    class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name = itemView.findViewById<TextView>(R.id.PatientName)
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

        holder.name.text = patient.patientName
        holder.department.text = patient.department
        holder.patientLastVisit.text = "Last Visit:" + patient.lastVisit
    }

    override fun getItemCount(): Int {
        return patients.size
    }

}