package br.com.dio.todolist.model

data class Task(
    val id : Int = 0,
    val title: String,
    val description : String,
    val date: String,
    var hour: String
)
