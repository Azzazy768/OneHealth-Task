package com.example.axaonehealth.Activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.axaonehealth.R
import com.example.axaonehealth.DataClasses.Patient
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.example.axaonehealth.databinding.ActivityPatientListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.axaonehealth.RecViewAdapters.PatientViewAdapter

class PatientListActivity : AppCompatActivity() {

    //enabiling view binding to make it eaiser to access UI stuff (my personal preference tbh)
    private lateinit var binding: ActivityPatientListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPatientListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Read database for patients, show list or text depending on if patients found
        val patientList = databaseParser("patients.json");

        if(patientList.isEmpty()){
            binding.PatientListView.visibility = View.GONE
            binding.PatientNotFoundText.visibility = View.VISIBLE
        }
        else{
            binding.PatientNotFoundText.visibility = View.INVISIBLE

            //Set up recycler view, its adapter and give it the data from the database
            binding.PatientListView.layoutManager = LinearLayoutManager(this)
            binding.PatientListView.adapter = PatientViewAdapter(patientList)
        }





    }



    fun databaseParser(databaseLink: String) : List<Patient>{ //Function that interprets the json file and returns array of patient Class.

        //Open json file, read it and give it back as a string
        val jsonText = assets.open(databaseLink)
            .bufferedReader()
            .use{it.readText()}

        //Turns json string into json object
        val jsonObject = JsonParser.parseString(jsonText).asJsonObject

        //Gets the array of patients from the json object
        val patientsJson = jsonObject.getAsJsonArray("patients")

        //Returns the array as patient Objects (something to note, Gson automaticlly takes each key and maps it to the patient class by comparing variables names to keys, so if i change the patient class variables, it can break).
        return patientsJson.map{Gson().fromJson(it, Patient::class.java)}
    }
}