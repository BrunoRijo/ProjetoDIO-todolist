package br.com.dio.todolist.datasource

import br.com.dio.todolist.model.Task
import br.com.dio.todolist.ui.TaskListAdapter

object TaskDatasource {

    val list = arrayListOf<Task>()

    fun insertTask(task: Task){
        if (task.id == 0) {
            list.add(task.copy(id = list.size + 1))
        } else {
            list.remove(task)
            list.add(task)
        }
    }
    
    fun getList() = list.toList()

    fun findById(id: Int) = list.find { it.id == id }

    fun deleteTask(task: Task) {
        list.remove(task)
    }
}