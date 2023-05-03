package com.gamil.moahear.studentmanager.remote.service

import com.gamil.moahear.studentmanager.data.Student
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IApiService {
    @GET("/student")
    fun getAllStudents():Call<List<Student>>
    @POST("/student")
    fun insertStudent(@Body body:JsonObject):Call<String>
}