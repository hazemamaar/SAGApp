package com.example.core.di

//import com.example.data.local.roomdb.UserDB
//import com.example.data.local.roomdb.UserDao
//import com.example.data.network.ApiService
//import com.example.features.common.data.repository.UserRepoImpl
//import com.example.features.common.domain.repository.UserRepo
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object RepoModule {
//
//    @Provides
//    fun provideSearchHistoryDao(roomDataBase: UserDB) = roomDataBase.userDao
//
//    @Provides
//    fun provideRepository(userDao: UserDao, apiService: ApiService): UserRepo =
//        UserRepoImpl(userDao, apiService)
//}