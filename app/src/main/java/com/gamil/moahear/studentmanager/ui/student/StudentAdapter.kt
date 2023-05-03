package com.gamil.moahear.studentmanager.ui.student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gamil.moahear.studentmanager.data.Student
import com.gamil.moahear.studentmanager.databinding.LayoutStudentItemBinding

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private val students = ArrayList<Student>()

    inner class StudentViewHolder(private val binding: LayoutStudentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindStudent(student: Student) {
            binding.apply {
                txtCourseName.text=student.course
                txtFirstCharacter.text= student.name.first().uppercaseChar().toString()
                txtFullName.text=student.name
                txtScore.text=student.score.toString()
                root.setOnClickListener {
                    setOnStudentItemClickListener?.let {
                        it(student)
                    }
                }
                root.setOnLongClickListener {
                    setOnStudentItemLongClickListener?.let {
                        it(student)
                    }
                    true
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding =
            LayoutStudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindStudent(students[position])
    }

    private var setOnStudentItemClickListener: ((Student) -> Unit)? = null
    fun onStudentItemClick(listener: (Student) -> Unit) {
        setOnStudentItemClickListener = listener
    }

    private var setOnStudentItemLongClickListener: ((Student) -> Unit)? = null
    fun onStudentItemLongClick(listener: (Student) -> Unit) {
        setOnStudentItemLongClickListener = listener
    }

    private class StudentDiffUtillCallback(val oldList: List<Student>, val newList: List<Student>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

    }

    fun submitStudents(newStudents: List<Student>) {
        val difResult = DiffUtil.calculateDiff(StudentDiffUtillCallback(students, newStudents))
        difResult.dispatchUpdatesTo(this)
        students.clear()
        students.addAll(newStudents)
    }
}