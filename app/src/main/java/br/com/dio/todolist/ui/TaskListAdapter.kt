package br.com.dio.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.todolist.R
import br.com.dio.todolist.databinding.ItemTaskBinding
import br.com.dio.todolist.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCalback()) {

    var ListenerEdit : (Task) -> Unit = {}
    var ListenerDelete : (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflate, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(item: Task){
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
                when(it.itemId){
                    R.id.menu_action_edit -> ListenerEdit(item)
                    R.id.menu_action_delete -> ListenerDelete(item)

                }
                return@setOnMenuItemClickListener true
            }
            popMenu.show()
        }

    }
    }
}

class DiffCalback : DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
}