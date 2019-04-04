package com.boulevard.uitestingarticle.core.di

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.boulevard.uitestingarticle.core.data.PlantRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun appContext(): Context
    fun weatherRepository(): PlantRepository
    fun viewModelFactory(): ViewModelProvider.Factory
}
