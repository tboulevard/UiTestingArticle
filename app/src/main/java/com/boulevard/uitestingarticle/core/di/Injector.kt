package com.boulevard.uitestingarticle.core.di

import com.boulevard.uitestingarticle.PlantlyApp


class Injector {
    companion object {
        fun get(): AppComponent =
            PlantlyApp.get().component
    }
}
