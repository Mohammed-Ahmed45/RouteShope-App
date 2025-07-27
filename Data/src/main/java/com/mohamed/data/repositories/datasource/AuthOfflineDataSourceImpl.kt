package com.mohamed.data.repositories.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.mohamed.data.interceptor.UserDataStore
import com.mohamed.domain.model.auth.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthOfflineDataSourceImpl @Inject constructor(
    @UserDataStore private val userDataStore: DataStore<Preferences>,
    private val gson: Gson,
) :
    AuthOfflineDataSource {

    val loginPreferencesKey = stringPreferencesKey("user")

    override fun retrieveUser(): Flow<AuthResponse?> {
        return userDataStore.data.map { data ->
            val loginResponseJson = data[loginPreferencesKey]
            gson.fromJson(loginResponseJson, AuthResponse::class.java)
        }
    }

    override suspend fun saveUser(response: AuthResponse) {
        userDataStore.edit { settings ->
            settings[loginPreferencesKey] = gson.toJson(response)
        }
    }
}