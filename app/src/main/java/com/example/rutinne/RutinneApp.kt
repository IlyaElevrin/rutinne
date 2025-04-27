package com.example.rutinne

import android.app.Application
import com.example.rutinne.data.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RutinneApp : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
} 