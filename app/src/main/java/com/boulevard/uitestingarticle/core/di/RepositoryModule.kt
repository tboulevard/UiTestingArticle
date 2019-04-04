package com.boulevard.uitestingarticle.core.di

import com.boulevard.uitestingarticle.core.data.PlantRepository
import dagger.Module
import dagger.Provides

@Module
@Suppress("unused")
class RepositoryModule {

    @Provides
    internal fun provideMainRepository(): PlantRepository {
        return PlantRepository()
    }
}
