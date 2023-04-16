package com.example.sagapp.di

import android.content.Context
import com.example.sagapp.ble.data.AndroidBluetoothController
import com.example.sagapp.ble.domin.BluetoothController
import com.example.sagapp.common.preDataStore.PreDataStore
import com.example.sagapp.welcome.domain.preferences.OnBoardPref
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
    fun dataStoreManager(@ApplicationContext appContext: Context) : PreDataStore =
        PreDataStore(appContext)

    @Provides
    @Singleton
    fun provideOnBoardDataStore(@ApplicationContext context: Context): OnBoardPref {
        return com.example.sagapp.welcome.data.preferences.OnBoardPref(dataStoreManager(context))
    }

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return AndroidBluetoothController(context)
    }
}