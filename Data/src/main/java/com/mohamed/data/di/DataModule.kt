package com.mohamed.data.di

import com.mohamed.data.repositories.auth.SignInRepoImp
import com.mohamed.data.repositories.auth.SignUpRepoImp
import com.mohamed.domain.repositories.auth.SignInRepo
import com.mohamed.domain.repositories.auth.SignUpRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {


    @Binds
    @Singleton
    abstract fun bindSignUp(signUpRepoImp: SignUpRepoImp): SignUpRepo

    @Binds
    @Singleton
    abstract fun bindSignIn(signInRepoImp: SignInRepoImp): SignInRepo

}