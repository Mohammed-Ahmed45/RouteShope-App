package com.mohamed.routeshop.ui.screens.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mohamed.routeshop.ui.navigation.Route
import com.mohamed.routeshop.ui.theme.colors
import com.mohamed.routeshop.ui.utils.AuthDialog
import com.mohamed.routeshop.ui.viewmodel.AuthViewModel

@Composable
fun LogInScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    authViewModel: AuthViewModel = hiltViewModel(),
) {


    if (authViewModel.isLoading) {
        AuthDialog()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Welcome Back!",
                fontSize = 36.sp,
                style = TextStyle(
                    color = colors.DarkGreen
                )

            )

            Text(
                text = "Enter your email to start shopping and get awesome deals today!",
                fontSize = 18.sp, modifier = modifier.padding(top = 10.dp)
            )
        }


        OutlinedTextField(
            modifier = modifier
                .padding(top = 22.dp)
                .width(343.dp),
            value = authViewModel.email,
            onValueChange = { authViewModel.updateEmail(it) },
            label = { Text(text = "Email") },
            singleLine = true,
            maxLines = 1,
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

        OutlinedTextField(
            modifier = modifier
                .padding(top = 10.dp)
                .width(343.dp),
            value = authViewModel.password,
            onValueChange = { authViewModel.updatePassword(it) },
            label = { Text(text = "Password") },
            singleLine = true,
            maxLines = 1,
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


        Text(
            text = "Forgot your password?",
            fontSize = 18.sp,
            color = colors.DarkGreen,
            modifier = modifier
                .padding(start = 28.dp, top = 20.dp)
        )



        Button(
            onClick = {
                authViewModel.signIn(
                    onSuccess = {
                        navController?.navigate(Route.MAIN_SCREEN) {
                            popUpTo(Route.REGISTER_SCREEN) { inclusive = true }
                        }
                    },
                    onFail = {
//                        authViewModel.clearForm()
                    }
                )

            },
            colors = ButtonDefaults.buttonColors(containerColor = colors.DarkGreen),
            modifier = modifier
                .padding(top = 22.dp)
                .width(343.dp)
        ) {
            Text(text = "Register")
        }
        Text(
            text = "OR",
            fontSize = 14.sp,
            modifier = modifier
                .padding(top = 20.dp)
        )
        Row(
            modifier = modifier
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don’t have an account?",
                fontSize = 14.sp,
            )
            TextButton(
                onClick = {
                    navController?.navigate(Route.REGISTER_SCREEN) {
                        popUpTo(Route.REGISTER_SCREEN) { inclusive = true }
                    }
                },
            ) {
                Text(
                    text = "Register",
                    color = colors.DarkGreen,
                    fontSize = 14.sp,
                )
            }
        }


    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    LogInScreen()
}