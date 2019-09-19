package com.example.todokotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todokotlin.data.TaskEntity
import com.example.todokotlin.data.TaskRepository
import com.tistory.deque.kotlinmvvmsample.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TaskViewModel(private val repository: TaskRepository) : DisposableViewModel() {

    private val _tasks = MutableLiveData<List<TaskEntity>>()
    private val _error = MutableLiveData<String>()
    private val _clickDone = SingleLiveEvent<Any>()

    val showResult = MutableLiveData<Boolean>()

    val tasks: LiveData<List<TaskEntity>> get() = _tasks
    val error: LiveData<String> get() = _error
    val clickDone: LiveData<Any> get() = _clickDone
    val title = MutableLiveData<String>()

    fun getAllTasks() {
        addDisposable(
            repository.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showResult.value = true
                    _tasks.value = it
                }, {
                    _error.value = it.message
                })
        )
    }

    fun findById(id: Long): LiveData<TaskEntity> {
        return repository.findById(id)
    }

    fun save() {
        val currentTitle = title.value ?: return
        val task = TaskEntity(title = currentTitle, userId = 1)
        Log.d("TaskViewModel", "save")

        addDisposable(repository.save(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { }
        )
    }

    fun delete(task: TaskEntity, next: () -> Unit) {
        addDisposable(repository.delete(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
        )
    }

    fun clickDone() {
        _clickDone.call()
    }
}