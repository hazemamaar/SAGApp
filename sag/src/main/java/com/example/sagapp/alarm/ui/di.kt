package com.example.sagapp.alarm.ui

import android.content.Context
import com.example.data.alarm.AndroidAlarmScheduler
import com.example.features.alarm.domain.AlarmScheduler
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