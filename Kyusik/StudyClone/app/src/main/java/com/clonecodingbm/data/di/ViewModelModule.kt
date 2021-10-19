package com.clonecodingbm.data.di

import com.clonecodingbm.data.repository.login.LoginRepository
import com.clonecodingbm.data.repository.mypage.MyPageRepository
import com.clonecodingbm.ui.login.LoginViewModel
import com.clonecodingbm.ui.mypage.MyPageViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object ViewModelModule {
    @Provides
    fun provideLoginViewModel(repository: LoginRepository): LoginViewModel {
        return LoginViewModel(repository)
    }
    @Provides
    fun provideMyPageViewModel(repository: MyPageRepository): MyPageViewModel {
        return MyPageViewModel(repository)
    }
}