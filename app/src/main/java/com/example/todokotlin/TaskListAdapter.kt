package com.example.todokotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    private var items: MutableList<TaskItem> = mutableListOf()

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let { task ->
            with(holder.itemView) {
                tv_task_title.text = task.title
                tv_creator_username.text = task.userName
                tv_comment_count.text =
                    if (task.commentCount > 0)
                        task.commentCount.toString()
                    else
                        ""

                setOnClickListener {
                    listener?.onItemClicked(task)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addTask(task: TaskItem) {
        this.items.add(0, task)
        notifyItemInserted(0)
    }

    fun bindTasks(items: List<TaskItem>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
    )

    interface ItemClickListener {

        fun onItemClicked(task: TaskItem)
    }
}