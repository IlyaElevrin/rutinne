package com.example.rutinne.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_tasks")
data class RoutineTask(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val dayOfWeek: Int, // 1-7 for Monday-Sunday
    val isCompleted: Boolean = false
) 