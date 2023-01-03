package com.example.postpone.repository

import com.example.postpone.data.todo.TodoDatabaseDao
import com.example.postpone.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDatabaseDao: TodoDatabaseDao
) {
    suspend fun addTodo(todo: Todo) {
        todoDatabaseDao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDatabaseDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDatabaseDao.deleteTodo(todo)
    }

    suspend fun deleteAllTodos() {
        todoDatabaseDao.deleteAllTodos()
    }

    fun getAllTodos(): Flow<List<Todo>> {
        return todoDatabaseDao.getAllTodos().flowOn(Dispatchers.IO).conflate()
    }
}