package com.example.sagapp.welcome.di

import android.content.Context
import com.example.sagapp.welcome.data.OnBoardPref
import com.example.sagapp.welcome.data.OnBoardPrefImpl
import com.example.sagapp.welcome.data.PreDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule  {

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context

    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext appContext: Context):PreDataStore =
        PreDataStore(appContext)

    @Provides
    @Singleton
    fun provideOnBoardDataStore(@ApplicationContext context: Context): OnBoardPref {
        return OnBoardPrefImpl(dataStoreManager(context))
    }
}