package com.example.todokotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TaskListAdapter.ItemClickListener {

    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter().apply { setItemClickListener(this@MainActivity) }
    }

    private lateinit var taskViewModel: TaskViewModel

    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!PreferenceHelper.signIn) {
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)

        taskViewModel = ViewModelProvider(this, viewModelFactory).get(TaskViewModel::class.java)

        taskViewModel.findAll().observe(this, Observer<List<TaskEntity>> {
            Log.i("taskViewModel", "update UI")
            bindTasks(it)
        })

        et_add_task.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    taskViewModel.save(
                        TaskEntity(
                            title = v.text.toString(),
                            userId = 1
                        )
                    )
                    v.text = ""
                    true
                }
                else -> false
            }
        }

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