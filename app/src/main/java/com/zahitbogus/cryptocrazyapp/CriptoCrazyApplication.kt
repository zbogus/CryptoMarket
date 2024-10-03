package com.zahitbogus.cryptocrazyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CriptoCrazyApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
    }
}