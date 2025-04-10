package com.example.aiplannerapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AiPlannerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}