package br.com.dio.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.todolist.databinding.ItemTaskBinding
import br.com.dio.todolist.model.Task
import java.util.zip.Inflater

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCalback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflate, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TaskViewHolder(
        private val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(item: Task){
                binding.tvTitle.text = item.title
                binding.tvDescription.text = item.description
                binding.tvData.text = "${item.date}:${item.hour}"
            }
    }
}

class DiffCalback : DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
}