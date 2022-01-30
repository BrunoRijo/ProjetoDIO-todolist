package br.com.dio.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.todolist.R
import br.com.dio.todolist.databinding.ItemTaskBinding
import br.com.dio.todolist.datasource.TaskDatasource
import br.com.dio.todolist.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback()) {

    var listenerEdit : (Task) -> Unit = {}
    var listenerDelete : (Task, Int) -> Unit = {task: Task, i: Int -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvData.text = "${item.date}:${item.hour}"
            binding.btnMore.setOnClickListener {
                showPopup(item)
            }
        }
        private fun showPopup(item: Task) {
            val btnMore = binding.btnMore
            val popMenu = PopupMenu(btnMore.context, btnMore)
            popMenu.menuInflater.inflate(R.menu.popup_menu, popMenu.menu)
            popMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_action_edit -> listenerEdit(item)
                    R.id.menu_action_delete -> listenerDelete(item, adapterPosition)
                }
                return@setOnMenuItemClickListener true
            }
            popMenu.show()
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) :Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Task, newItem: Task):Boolean {
        return oldItem.id == newItem.id
    }
}