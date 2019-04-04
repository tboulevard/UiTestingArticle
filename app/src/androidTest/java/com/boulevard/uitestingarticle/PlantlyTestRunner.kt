package com.boulevard.uitestingarticle

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class PlantlyTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}
