package com.example.axaonehealth.DataClasses

import android.os.Parcelable


data class Patient(val id: Int, val name: String, val age:Int, val department:String, val lastVisit:String, val isActive:Boolean)