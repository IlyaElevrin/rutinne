package com.example.rutinne

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutinne.data.RoutineTask
import com.example.rutinne.data.RoutineTaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val routineTaskDao: RoutineTaskDao
) : ViewModel() {
    private val _selectedDay = MutableStateFlow(1) // Monday by default
    val selectedDay: StateFlow<Int> = _selectedDay.asStateFlow()

    val tasks: StateFlow<List<RoutineTask>> = _selectedDay.flatMapLatest { day ->
        routineTaskDao.getTasksByDay(day)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onDaySelected(day: Int) {
        _selectedDay.value = day
    }

    fun onTaskChecked(task: RoutineTask, isChecked: Boolean) {
        viewModelScope.launch {
            routineTaskDao.updateTask(task.copy(isCompleted = isChecked))
        }
    }

    fun onTaskDelete(task: RoutineTask) {
        viewModelScope.launch {
            routineTaskDao.deleteTask(task)
        }
    }

    fun onAddTask() {
        // TODO: Implement task creation dialog
    }
} 