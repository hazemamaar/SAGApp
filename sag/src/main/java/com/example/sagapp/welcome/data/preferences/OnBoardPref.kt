package com.example.sagapp.welcome.data.preferences

import com.example.sagapp.common.preDataStore.PreDataStore
import com.example.sagapp.welcome.domain.preferences.OnBoardPref
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class OnBoardPref @Inject constructor(private val prefDataStore: PreDataStore) :
    OnBoardPref {
    private val _isWatched = MutableStateFlow(false)
    override val isWatched: StateFlow<Boolean>
        get() = _isWatched.asStateFlow()
    override suspend fun onBoardWriteToFinish() {
        prefDataStore.saveBooleanToDataStore(true)
    }

    override  fun onBoardReadToFinish() : Flow<Boolean> =
        prefDataStore.readFromDataStore

}