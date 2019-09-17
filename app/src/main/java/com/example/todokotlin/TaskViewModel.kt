package com.example.todokotlin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TaskViewModel(private val repository: TaskRepository) : DisposableViewModel() {

    private val _tasks = MutableLiveData<List<TaskEntity>>()
    private val _error = MutableLiveData<String>()

    val showResult = MutableLiveData<Boolean>()

    val tasks: LiveData<List<TaskEntity>> get() = _tasks
    val error: LiveData<String> get() = _error

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

    fun save(task: TaskEntity, next: () -> Unit) {
        addDisposable(repository.save(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
        )
    }

    fun delete(task: TaskEntity, next: () -> Unit) {
        addDisposable(repository.delete(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
        )
    }
}