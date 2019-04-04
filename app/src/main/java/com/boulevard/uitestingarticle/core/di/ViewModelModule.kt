package com.boulevard.uitestingarticle.core.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.boulevard.uitestingarticle.core.viewmodel.PlantlyViewModelFactory
import com.boulevard.uitestingarticle.ui.PlantListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlantListViewModel::class)
    abstract fun bindUserViewModel(mainViewModel: PlantListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: PlantlyViewModelFactory): ViewModelProvider.Factory
}
