package br.com.dio.todolist.datasource

import br.com.dio.todolist.model.Task

class TaskDatasource {

    val list = arrayListOf<Task>()

    fun insertTask(task: Task){
        list.add(task.copy(id = list.size+1))
    }
}