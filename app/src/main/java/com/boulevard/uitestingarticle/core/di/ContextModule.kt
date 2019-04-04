package com.boulevard.uitestingarticle.core.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
@Suppress("unused")
class ContextModule(private val appContext: Context) {

    @Provides
    internal fun appContext(): Context = appContext
}
