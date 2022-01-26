package br.com.dio.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.todolist.databinding.ActivityAddTaskBinding
import br.com.dio.todolist.extensions.format
import br.com.dio.todolist.extensions.text
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*
import java.util.Calendar.DATE

class AddTaskActivity: AppCompatActivity() {

    lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener{
            var datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener(){
                var timeZone =  TimeZone.getDefault()
                var offset = timeZone.getOffset(Date().time) *-1
                binding.tilDate.text = Date(it+offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }
    }
}