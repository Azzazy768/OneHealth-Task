package com.example.axaonehealth.Activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.axaonehealth.R
import com.example.axaonehealth.DataClasses.Patient
import com.google.gson.Gson
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

        val adapter = PatientViewAdapter(patientList) //assigns the patientList to the Rec View adapter

        if(patientList.isEmpty()){
            binding.PatientListView.visibility = View.GONE
            binding.PatientNotFoundText.visibility = View.VISIBLE
        }
        else{
            binding.PatientNotFoundText.visibility = View.INVISIBLE

            //Set up recycler view and its adapter.
            binding.PatientListView.layoutManager = LinearLayoutManager(this)
            binding.PatientListView.adapter = adapter
        }


        //Filtering setup (uses a searchview to filter list by user input!)
        binding.PatientSearch.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

                //No submit button so i set it to false automaticlly
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                //If user changes text inside the "search patient" box, search the list and update the recview
                override fun onQueryTextChange(newText: String?): Boolean {

                    val searchText = newText.orEmpty() //Or empty is used to conver null to "" (basiaclly avoids null pointer errors)

                    val filteredPatients = patientList.filter { //Filters the list using in-built function. Removes those who dont fit the case.
                        it.name.contains(searchText, ignoreCase = true) // Ignorecase is so that its not case-sensitive (More user friendly and ensures better searches basiaclly)
                    }

                    adapter.updateList(filteredPatients)

                    return true
                }
            }
        )


        //Button setup for language change
        val languageToggle = binding.LanguageToggleGroup
        languageToggle.addOnButtonCheckedListener { group, checkedId, isChecked ->

            if(isChecked){
                when(checkedId){
                    R.id.ButtonEN ->{  //Had to use R.id. here because binding would not work
                        changeLanguage("en")
                    }
                    R.id.ButtonAR ->{
                        changeLanguage("ar")
                    }
                }
            }

        }

        //Change the highlighted button depending on the current language
        val currentLanguage = resources.configuration.locales.get(0).language

        if (currentLanguage == "ar") {
            languageToggle.check(R.id.ButtonAR)
        } else {
            languageToggle.check(R.id.ButtonEN)
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


    //Function that changes language of app depending on language inputted
    private fun changeLanguage(languageCode: String) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}