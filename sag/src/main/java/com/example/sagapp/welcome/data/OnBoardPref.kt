package com.example.sagapp.welcome.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface OnBoardPref {
    val isWatched: StateFlow<Boolean>

    suspend fun onBoardWriteToFinish()
    fun onBoardReadToFinish(): Flow<Boolean>
}