package com.gamil.moahear.studentmanager.ui.student

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gamil.moahear.studentmanager.databinding.FragmentAddStudentBinding
import com.gamil.moahear.studentmanager.remote.client.ApiClient
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddStudentFragment : Fragment() {
    private lateinit var binding: FragmentAddStudentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddStudentBinding.inflate(inflater)
        //activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            edtFirstName.requestFocus()
            btnSave.setOnClickListener {
                val jsonObject=JsonObject()
                jsonObject.apply {
                    addProperty("name",edtFirstName.text.toString()+" "+edtLastName.text.toString())
                    addProperty("course",edtdCourseName.text.toString())
                    addProperty("score",edtScore.text.toString().toInt())
                }

                ApiClient.apiService.insertStudent(body = jsonObject).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Toast.makeText(context, "student added", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(context, t.message.toString(), Toast.LENGTH_LONG).show()
                        Log.i("TAGG", "onFailure: ${t.message.toString()}")
                    }

                })

            }
        }
    }
}