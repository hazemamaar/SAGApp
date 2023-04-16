package com.example.sagapp.welcome.domain.preferences

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface OnBoardPref {
    val isWatched: StateFlow<Boolean>

    suspend fun onBoardWriteToFinish()
    fun onBoardReadToFinish(): Flow<Boolean>
}