package com.example.sagapp.authentication.ui.di


import com.example.sagapp.BuildConfig
import com.example.sagapp.authentication.data.remote.service.AuthenticationServices
import com.example.sagapp.authentication.data.repo.AuthenticationRepo
import com.example.sagapp.authentication.data.repo.AuthenticationRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val localHttpLoggingInterceptor = HttpLoggingInterceptor()
        localHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return localHttpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val builder: Request.Builder = original.newBuilder()
                val lang="en"
                val newRequest = builder.build()

                return@addInterceptor chain.proceed(newRequest)
            }
            .build()

    @Provides
    @Singleton
    fun providesApiService(okHttpClient: OkHttpClient): AuthenticationServices =
        Retrofit.Builder()
            .run {
                baseUrl(BuildConfig.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
                build()
            }.create(AuthenticationServices::class.java)
    @Provides
    @Singleton
    fun provideAuthenticationRepoController(authenticationServices: AuthenticationServices): AuthenticationRepo {
        return AuthenticationRepoImpl(authenticationServices)
    }
//    @Singleton
//    @Provides
//    fun provideApplicationContext(
//        @ApplicationContext context: Context,
//    ) = context
//    @Singleton
//    @Provides
//    fun provideGlideInstance(
//        @ApplicationContext context: Context
//    ) = Glide.with(context).setDefaultRequestOptions(
//        RequestOptions()
//            .placeholder(R.drawable.placeholder)
//            .error(R.drawable.network_error)
//            .diskCacheStrategy(DiskCacheStrategy.DATA))
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context): HeroDB {
//        return Room.databaseBuilder(
//            appContext,
//            HeroDB::class.java,
//            "hero_db"
//        ).fallbackToDestructiveMigration().build()
//    }
//    @Provides
//    fun provideSearchHistoryDao(roomDataBase: HeroDB)= roomDataBase.heroDao
}