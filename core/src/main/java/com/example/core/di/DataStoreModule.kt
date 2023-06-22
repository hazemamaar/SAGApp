package com.example.core.di

import android.content.Context
import com.example.data.welcom.data.OnBoardPref
import com.example.data.welcom.data.OnBoardPrefImpl
import com.example.data.welcom.data.PreDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule  {

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context

    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext appContext: Context): PreDataStore =
        PreDataStore(appContext)

    @Provides
    @Singleton
    fun provideOnBoardDataStore(@ApplicationContext context: Context): OnBoardPref {
        return OnBoardPrefImpl(dataStoreManager(context))
    }
}