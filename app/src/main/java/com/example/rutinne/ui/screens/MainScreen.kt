package com.example.rutinne.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rutinne.data.RoutineTask
import com.example.rutinne.ui.components.TaskList
import com.example.rutinne.ui.components.WeekSelector
import java.time.DayOfWeek

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    tasks: List<RoutineTask>,
    selectedDay: Int,
    onDaySelected: (Int) -> Unit,
    onTaskChecked: (RoutineTask, Boolean) -> Unit,
    onTaskDelete: (RoutineTask) -> Unit,
    onAddTask: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rutinne") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTask,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add task"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            WeekSelector(
                selectedDay = selectedDay,
                onDaySelected = onDaySelected,
                modifier = Modifier.padding(8.dp)
            )
            
            TaskList(
                tasks = tasks,
                onTaskChecked = onTaskChecked,
                onTaskDelete = onTaskDelete,
                modifier = Modifier.weight(1f)
            )
        }
    }
} 