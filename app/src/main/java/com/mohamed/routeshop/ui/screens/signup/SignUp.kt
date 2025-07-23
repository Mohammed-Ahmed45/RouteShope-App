package com.mohamed.routeshop.ui.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamed.routeshop.ui.navigation.Route
import com.mohamed.routeshop.ui.theme.colors
import com.mohamed.routeshop.ui.utils.AuthDialog
import com.mohamed.routeshop.ui.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        // Clear form when entering register screen
        authViewModel.clearForm()
    }

    // Show loading dialog
    if (authViewModel.isLoading) {
        AuthDialog()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Title
        Text(
            text = "Create Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Form Fields
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Name Field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authViewModel.name,
                onValueChange = { authViewModel.updateName(it) },
                label = { Text("Full Name") },
                singleLine = true,
                isError = authViewModel.nameError != null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                supportingText = {
                    authViewModel.nameError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }
                }
            )

            // Email Field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authViewModel.email,
                onValueChange = { authViewModel.updateEmail(it) },
                label = { Text("Email Address") },
                singleLine = true,
                isError = authViewModel.emailError != null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                supportingText = {
                    authViewModel.emailError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }
                }
            )

            // Phone Field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authViewModel.phone,
                onValueChange = { authViewModel.updatePhone(it) },
                label = { Text("Phone Number") },
                singleLine = true,
                isError = authViewModel.phoneError != null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                supportingText = {
                    authViewModel.phoneError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }
                }
            )

            // Password Field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authViewModel.password,
                onValueChange = { authViewModel.updatePassword(it) },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (authViewModel.isPasswordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { authViewModel.togglePasswordVisibility() }) {
                        Icon(
                            imageVector = if (authViewModel.isPasswordVisible)
                                Icons.Default.Face else Icons.Default.Lock,
                            contentDescription = if (authViewModel.isPasswordVisible)
                                "Hide password" else "Show password"
                        )
                    }
                },
                isError = authViewModel.passwordError != null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                supportingText = {
                    authViewModel.passwordError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }
                }
            )

            // Confirm Password Field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = authViewModel.rePassword,
                onValueChange = { authViewModel.updateRePassword(it) },
                label = { Text("Confirm Password") },
                singleLine = true,
                visualTransformation = if (authViewModel.isRePasswordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { authViewModel.toggleConfirmPasswordVisibility() }) {
                        Icon(
                            imageVector = if (authViewModel.isRePasswordVisible)
                                Icons.Default.Face else Icons.Default.Lock,
                            contentDescription = if (authViewModel.isRePasswordVisible)
                                "Hide confirm password" else "Show confirm password"
                        )
                    }
                },
                isError = authViewModel.rePasswordError != null,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                supportingText = {
                    authViewModel.rePasswordError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }
                }
            )

            // General Registration Error
            if (authViewModel.registrationError.isNotBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = authViewModel.registrationError,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Register Button
        Button(
            onClick = {

                authViewModel.signUp(
                    onSuccess = {
                        navController?.navigate(Route.MAIN_SCREEN) {
                            popUpTo(Route.REGISTER_SCREEN) { inclusive = true }
                        }
                    },
                    onFail = {
                        // Registration failed, error is already shown via registrationError
                    }
                )

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.DarkGreen,
                disabledContainerColor = colors.DarkGreen.copy(alpha = 0.6f)
            ),
            enabled = !authViewModel.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            if (authViewModel.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Create Account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen()
}