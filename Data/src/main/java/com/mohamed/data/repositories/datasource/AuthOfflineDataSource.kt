package com.mohamed.data.repositories.datasource

import com.mohamed.domain.model.auth.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthOfflineDataSource {
    fun retrieveUser(): Flow<AuthResponse?>
    suspend fun saveUser(response: AuthResponse)
}