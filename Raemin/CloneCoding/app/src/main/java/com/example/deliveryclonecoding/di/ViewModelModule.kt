package com.example.deliveryclonecoding.di

import com.example.deliveryclonecoding.data.Repository
import com.example.deliveryclonecoding.ui.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object ViewModelModule {
    @Provides
    fun provideLoginViewModel(repository: Repository): LoginViewModel {
        return LoginViewModel(repository)
    }
}