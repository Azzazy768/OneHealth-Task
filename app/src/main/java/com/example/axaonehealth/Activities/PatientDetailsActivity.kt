package com.example.axaonehealth.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.axaonehealth.databinding.ActivityPatientDetailsBinding


class PatientDetailsActivity : AppCompatActivity() {

    //enabiling view binding to make it eaiser to access UI stuff (again) (my personal preference tbh)
    private lateinit var binding: ActivityPatientDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPatientDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back button setup
        binding.BackButton.setOnClickListener {
            val intent = Intent(this, PatientListActivity::class.java)
            startActivity(intent)
        }


        //getting the values from the previous intent
        val id = intent.getIntExtra("id",0)
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age",0)
        val department = intent.getStringExtra("department")
        val lastVisit = intent.getStringExtra("lastVisit")
        val isActiveBool = intent.getBooleanExtra("isActive",false)
        var isActive = "Inactive"

        //Changes the isActive variable to be "Active" if the boolean is true (UX choice i thought would be nice)
        if(isActiveBool){
            isActive = "Active"
        }


        //setting values
        binding.DetailPatientName.text = name
        binding.DetailPatientId.text = "Patient ID: " + id.toString()
        binding.ValueDepartment.text = department
        binding.ValueAge.text = age.toString()
        binding.ValueLastVisit.text = lastVisit
        binding.ValueStatus.text = isActive

        //sets isActive color to red if not active (UI choice i thought would be good for whoever reads the profile)
        if(isActive == "Inactive"){
            binding.ValueStatus.setTextColor(Color.RED)
        }

    }
}