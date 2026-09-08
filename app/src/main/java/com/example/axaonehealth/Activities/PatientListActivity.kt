package com.example.axaonehealth.Activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.axaonehealth.R

class PatientListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient_list)




    }



    fun databaseParser(databaseLink: String){ //Function that interprets the json file and returns array of patient Class.

    }
}