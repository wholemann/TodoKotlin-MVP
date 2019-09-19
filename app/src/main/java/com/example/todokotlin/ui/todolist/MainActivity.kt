package com.example.todokotlin.ui.todolist

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.todokotlin.R
import com.example.todokotlin.data.PreferenceHelper
import com.example.todokotlin.data.TaskEntity
import com.example.todokotlin.databinding.ActivityMainBinding
import com.example.todokotlin.ui.BaseActivity
import com.example.todokotlin.ui.TaskDetailActivity
import com.example.todokotlin.ui.signin.SignInActivity
import com.example.todokotlin.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(),
    TaskListAdapter.ItemClickListener {

    override val layoutResourceId: Int = R.layout.activity_main

    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter().apply { setItemClickListener(this@MainActivity) }
    }

    private val taskViewModel: TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.lifecycleOwner = this

        if (!PreferenceHelper.signIn) {
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        taskViewModel.clickDone.observe(this, Observer {
        })

//        taskViewModel.getAllTasks()

        taskViewModel.tasks.observe(this, Observer {
            bindTasks(it)
        })

        with(rv_task_list) {
            adapter = this@MainActivity.adapter
        }
    }

    override fun onItemClicked(task: TaskEntity) {
        val intent = Intent(this@MainActivity, TaskDetailActivity::class.java)
        startActivity(intent)
    }


    private fun bindTasks(tasks: List<TaskEntity>) {
        adapter.bindTasks(tasks)
    }
}