package br.com.dio.todolist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import br.com.dio.todolist.databinding.ActivityMainBinding
import br.com.dio.todolist.datasource.TaskDatasource

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }
    private val register =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                updateList()
            }
        }

    companion object{
        private const val CREATE_NEW_TASK = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter

        updateList()
        setListeners()
    }

    private fun setListeners() {
        binding.fabNewTask.setOnClickListener{
            register.launch(Intent(this, AddTaskActivity::class.java))
        }

        adapter.listenerEdit = {
            val intent = Intent(this,AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            register.launch(intent)
        }

        adapter.listenerDelete = {
            TaskDatasource.deleteTask(it)
            updateList()
        }
    }

    private fun updateList() {
        val list = TaskDatasource.getList()
        binding.includeEmpty.emptyState.visibility = if (list.isEmpty()) View.VISIBLE
        else View.GONE

        adapter.submitList(list)
    }
}