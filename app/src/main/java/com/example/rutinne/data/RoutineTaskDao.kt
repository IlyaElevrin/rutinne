package com.example.rutinne.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineTaskDao {
    @Query("SELECT * FROM routine_tasks ORDER BY dayOfWeek ASC")
    fun getAllTasks(): Flow<List<RoutineTask>>

    @Query("SELECT * FROM routine_tasks WHERE dayOfWeek = :dayOfWeek")
    fun getTasksByDay(dayOfWeek: Int): Flow<List<RoutineTask>>

    @Insert
    suspend fun insertTask(task: RoutineTask)

    @Update
    suspend fun updateTask(task: RoutineTask)

    @Delete
    suspend fun deleteTask(task: RoutineTask)
} 