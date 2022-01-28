package br.com.dio.todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dio.todolist.databinding.ActivityMainBinding
import br.com.dio.todolist.datasource.TaskDatasource

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }

    companion object{
        private const val CREATE_NEW_TASK = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setListeners()
        updateList()
    }

    private fun setListeners() {
        binding.fabNewTask.setOnClickListener{
            startActivityForResult(Intent(this,AddTaskActivity::class.java), CREATE_NEW_TASK)
        }

        adapter.ListenerEdit = {
            val intent = Intent(this,AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }

        adapter.ListenerDelete = {
            TaskDatasource.deleteTask(it)
            updateList()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CREATE_NEW_TASK ){
            binding.rvTasks.adapter = adapter
            updateList()
        }
    }

    private fun updateList(){
        adapter.submitList(TaskDatasource.getList())
    }

}