package com.gamil.moahear.studentmanager.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(var id:Int=0,val name:String,val course:String,val score:Int):Parcelable
