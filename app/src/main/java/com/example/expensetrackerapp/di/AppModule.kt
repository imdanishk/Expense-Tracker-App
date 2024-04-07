package com.example.expensetrackerapp.di

import android.content.Context
import com.example.expensetrackerapp.app.ExpenseTrackerApp
import com.example.expensetrackerapp.common.utils.FirebaseManager
import com.example.expensetrackerapp.core.DataStore
import com.example.expensetrackerapp.core.PrefsManager
import com.example.expensetrackerapp.data.repository.AuthRepo
import com.example.expensetrackerapp.data.repository.LocalRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(): ExpenseTrackerApp {
        return ExpenseTrackerApp()
    }

    @Provides
    @Singleton
    fun providePrefsManager(@ApplicationContext context: Context): PrefsManager {
        return PrefsManager(context)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore {
        return DataStore(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseManager(): FirebaseManager {
        return FirebaseManager()
    }

    @Provides
    @Singleton
    fun provideLocalRepo(
        dataStore: DataStore,
        prefsManager: PrefsManager
    ): LocalRepo = LocalRepo(dataStore, prefsManager)

    @Singleton
    @Provides
    fun provideAuthRepository(
        firebaseManager: FirebaseManager,
    ) = AuthRepo(firebaseManager)

}