package com.example.todokotlin

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), TaskListAdapter.ItemClickListener {

    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter().apply { setItemClickListener(this@MainActivity) }
    }

    private val taskViewModel: TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!PreferenceHelper.signIn) {
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        taskViewModel.getAllTasks()

        taskViewModel.tasks.observe(this, Observer {
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
                    ) { v.text = "" }

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