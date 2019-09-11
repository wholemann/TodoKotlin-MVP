package com.example.todokotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TaskListAdapter.ItemClickListener {

    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter().apply { setItemClickListener(this@MainActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!PreferenceHelper.signIn) {
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        et_add_task.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    addTask(
                        TaskItem(
                            title = v.text.toString(),
                            userName = "wholeman",
                            commentCount = 1
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

    override fun onStart() {
        super.onStart()
        fetchTasks()
    }

    override fun onItemClicked(task: TaskItem) {
        val intent = Intent(this@MainActivity, TaskDetailActivity::class.java)
        startActivity(intent)
    }

    private fun fetchTasks() {
//        bindTasks(tasks)
    }

    private fun addTask(task: TaskItem) {
        adapter.addTask(task)
    }

    private fun bindTasks(tasks: List<TaskItem>) {
        adapter.bindTasks(tasks)
    }

    private fun hideSoftKeyboard() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).run {
            hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}