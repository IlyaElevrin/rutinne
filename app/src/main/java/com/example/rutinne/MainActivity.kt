package com.example.rutinne

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rutinne.ui.screens.MainScreen
import com.example.rutinne.ui.theme.RutinneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RutinneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewModel = viewModel()
                    
                    MainScreen(
                        tasks = viewModel.tasks,
                        selectedDay = viewModel.selectedDay,
                        onDaySelected = viewModel::onDaySelected,
                        onTaskChecked = viewModel::onTaskChecked,
                        onTaskDelete = viewModel::onTaskDelete,
                        onAddTask = viewModel::onAddTask
                    )
                }
            }
        }
    }
} 