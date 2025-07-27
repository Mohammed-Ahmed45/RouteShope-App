package com.mohamed.routeshop.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.data.repositories.datasource.AuthOfflineDataSource
import com.mohamed.domain.model.auth.SignInRequest
import com.mohamed.domain.model.auth.SignUpRequest
import com.mohamed.domain.usecases.auth.SignInUseCase
import com.mohamed.domain.usecases.auth.SignUpUseCase
import com.mohamed.routeshop.ui.error.handleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val authOfflineDataSource: AuthOfflineDataSource, // Add this dependency
) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    // Form fields
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var phone by mutableStateOf("")
    var rePassword by mutableStateOf("")

    // Error states
    var nameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var phoneError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var rePasswordError by mutableStateOf<String?>(null)
    var registrationError by mutableStateOf("")

    // UI states with private setters
    var isPasswordVisible by mutableStateOf(false)
    var isRePasswordVisible by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    // Update functions with validation clearing
    fun updateName(newName: String) {
        name = newName.trim()
        if (nameError != null) nameError = null
        clearRegistrationError()
    }

    fun updateEmail(newEmail: String) {
        email = newEmail.trim().lowercase()
        if (emailError != null) emailError = null
        clearRegistrationError()
    }

    fun updatePhone(newPhone: String) {
        val cleanPhone = newPhone.filter { it.isDigit() || it in "+-(). " }
        phone = cleanPhone
        if (phoneError != null) phoneError = null
        clearRegistrationError()
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
        if (passwordError != null) passwordError = null
        // Also clear confirm password error if passwords match
        if (rePasswordError != null && newPassword == rePassword) {
            rePasswordError = null
        }
        clearRegistrationError()
    }

    fun updateRePassword(newRePassword: String) {
        rePassword = newRePassword
        if (rePasswordError != null) rePasswordError = null
        clearRegistrationError()
    }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    fun toggleConfirmPasswordVisibility() {
        isRePasswordVisible = !isRePasswordVisible
    }

    private fun clearRegistrationError() {
        if (registrationError.isNotBlank()) {
            registrationError = ""
        }
    }

    private fun validateName(): Boolean {
        nameError = when {
            name.isBlank() -> "Name is required"
            name.length < 2 -> "Name must be at least 2 characters"
            name.length > 50 -> "Name must be less than 50 characters"
            !name.matches(Regex("^[a-zA-Z\\s]+$")) -> "Name can only contain letters and spaces"
            name.trim().split("\\s+".toRegex()).size < 2 -> "Please enter your full name"
            else -> null
        }
        return nameError == null
    }

    private fun validateEmail(): Boolean {
        emailError = when {
            email.isBlank() -> "Email is required"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                "Please enter a valid email address"

            email.length > 254 -> "Email address is too long"
            else -> null
        }
        return emailError == null
    }

    private fun validatePhone(): Boolean {
        // Remove all non-digit characters for validation
        val digitsOnly = phone.filter { it.isDigit() }

        phoneError = when {
            phone.isBlank() -> "Phone number is required"
            digitsOnly.length < 10 -> "Phone number must be at least 10 digits"
            digitsOnly.length > 15 -> "Phone number is too long"
            !phone.matches(Regex("^[+]?[0-9\\s\\-().]+$")) ->
                "Please enter a valid phone number"

            else -> null
        }
        return phoneError == null
    }

    private fun validatePassword(): Boolean {
        passwordError = when {
            password.isBlank() -> "Password is required"
            password.length < 8 -> "Password must be at least 8 characters"
            password.length > 128 -> "Password must be less than 128 characters"
            !password.matches(Regex(".*[A-Z].*")) ->
                "Password must contain at least one uppercase letter"

            !password.matches(Regex(".*[a-z].*")) ->
                "Password must contain at least one lowercase letter"

            !password.matches(Regex(".*\\d.*")) ->
                "Password must contain at least one number"

            !password.matches(Regex(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) ->
                "Password must contain at least one special character"

            password.contains(" ") -> "Password cannot contain spaces"
            else -> null
        }
        return passwordError == null
    }

    private fun validateConfirmPassword(): Boolean {
        rePasswordError = when {
            rePassword.isBlank() -> "Please confirm your password"
            rePassword != password -> "Passwords do not match"
            else -> null
        }
        return rePasswordError == null
    }

    fun validateSignUp(): Boolean {
        val isNameValid = validateName()
        val isEmailValid = validateEmail()
        val isPhoneValid = validatePhone()
        val isPasswordValid = validatePassword()
        val isConfirmPasswordValid = validateConfirmPassword()

        return isNameValid && isEmailValid && isPhoneValid &&
                isPasswordValid && isConfirmPasswordValid
    }

    fun validateSignIn(): Boolean {
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()

        return isEmailValid && isPasswordValid
    }

    fun clearErrors() {
        nameError = null
        emailError = null
        passwordError = null
        rePasswordError = null
        phoneError = null
        registrationError = ""
    }

    fun clearForm() {
        name = ""
        email = ""
        password = ""
        phone = ""
        rePassword = ""
        clearErrors()
        isPasswordVisible = false
        isRePasswordVisible = false
        isLoading = false
    }

    fun signUp(onSuccess: () -> Unit, onFail: () -> Unit) {
        if (!validateSignUp()) {
            onFail()
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                registrationError = ""

                val request = SignUpRequest(
                    name = name.trim(),
                    email = email.trim().lowercase(),
                    password = password,
                    phone = phone.trim(),
                    rePassword = password
                )

                val response = signUpUseCase.invoke(request)

                if (response != null) {
                    try {
                        authOfflineDataSource.saveUser(response)
                        Log.d(TAG, "User data saved successfully after signup")
                        Log.d(TAG, "Saved token: ${!response.token.isNullOrEmpty()}")
                        Log.d(TAG, "Saved user: ${response.user?.email}")
                    } catch (e: Exception) {
                        Log.e(TAG, "Error saving user data after signup: ${e.message}")
                    }

                    onSuccess()
                } else {
                    registrationError = "Registration failed. Please try again."
                    onFail()
                }

            } catch (e: Exception) {
                handleError(e)
                registrationError = when {
                    e.message?.contains("email", ignoreCase = true) == true ->
                        "This email is already registered"

                    e.message?.contains("network", ignoreCase = true) == true ->
                        "Network error. Please check your connection"

                    e.message?.contains("timeout", ignoreCase = true) == true ->
                        "Request timed out. Please try again"

                    else -> "Registration failed. Please try again"
                }
                onFail()
                Log.e(TAG, "signUp error: ${e.message}", e)
            } finally {
                isLoading = false
            }
        }
    }

    fun signIn(onSuccess: () -> Unit, onFail: () -> Unit) {
        if (!validateSignIn()) {
            onFail()
            return
        }
        viewModelScope.launch {
            try {
                isLoading = true
                registrationError = ""

                val request = SignInRequest(
                    email = email.trim().lowercase(),
                    password = password.trim()
                )
                val response = signInUseCase.invoke(request)

                if (response != null) {
                    try {
                        authOfflineDataSource.saveUser(response)
                        Log.d(TAG, "User data saved successfully after sign in")
                        Log.d(TAG, "Saved token: ${!response.token.isNullOrEmpty()}")
                        Log.d(TAG, "Saved user: ${response.user?.email}")
                    } catch (e: Exception) {
                        Log.e(TAG, "Error saving user data after sign in: ${e.message}")
                    }

                    onSuccess()
                } else {
                    registrationError = "Sign in failed. Please try again."
                    onFail()
                }

            } catch (e: Exception) {
                handleError(e)
                registrationError = when {
                    e.message?.contains("401") == true ||
                            e.message?.contains("unauthorized", ignoreCase = true) == true ->
                        "Invalid email or password"

                    e.message?.contains("email", ignoreCase = true) == true ->
                        "Invalid email or password"

                    e.message?.contains("network", ignoreCase = true) == true ->
                        "Network error. Please check your connection"

                    e.message?.contains("timeout", ignoreCase = true) == true ->
                        "Request timed out. Please try again"

                    else -> "Sign in failed. Please try again"
                }

                onFail()
                Log.e(TAG, "signIn error: ${e.message}", e)
            } finally {
                isLoading = false
            }
        }
    }
}