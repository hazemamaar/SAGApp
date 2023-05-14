package com.example.sagapp.alarm.ui

import android.content.Context
import com.example.data.alarm.data.AndroidAlarmScheduler
import com.example.sagapp.alarm.domain.AlarmScheduler
import com.example.sagapp.ble.data.AndroidBluetoothController
import com.example.sagapp.ble.domin.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlarmModule {
    @Provides
    @Singleton
    fun provideAlarmController(@ApplicationContext context: Context): AlarmScheduler {
        return AndroidAlarmScheduler(context)
    }
}