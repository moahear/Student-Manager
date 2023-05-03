package com.gamil.moahear.studentmanager.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamil.moahear.studentmanager.data.Student
import com.gamil.moahear.studentmanager.databinding.FragmentHomeBinding
import com.gamil.moahear.studentmanager.remote.client.ApiClient
import com.gamil.moahear.studentmanager.ui.student.StudentAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val studentAdapter by lazy {
        StudentAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
        ApiClient.apiService.getAllStudents().enqueue(object:Callback<List<Student>>{
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {

                response.body()?.let {
                    studentAdapter.submitStudents(it)
                    rvStudent.adapter=studentAdapter
                    rvStudent.layoutManager=LinearLayoutManager(context,RecyclerView.VERTICAL,false)
                }

            }

            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                Log.i("TAGApp", "onFailure: ${t.message}")
            }
        })
            btnAddStudent.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddStudentFragment())
            }
        }
    }
}