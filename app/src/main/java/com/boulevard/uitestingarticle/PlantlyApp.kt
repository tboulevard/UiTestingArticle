package com.boulevard.uitestingarticle

import android.app.Application
import com.boulevard.uitestingarticle.core.di.AppComponent
import com.boulevard.uitestingarticle.core.di.ContextModule
import com.boulevard.uitestingarticle.core.di.DaggerAppComponent

class PlantlyApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        private var INSTANCE: PlantlyApp? = null

        @JvmStatic
        fun get(): PlantlyApp = INSTANCE!!
    }
}
