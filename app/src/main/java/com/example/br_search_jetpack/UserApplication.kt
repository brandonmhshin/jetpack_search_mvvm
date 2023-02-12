package com.example.br_search_jetpack

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}