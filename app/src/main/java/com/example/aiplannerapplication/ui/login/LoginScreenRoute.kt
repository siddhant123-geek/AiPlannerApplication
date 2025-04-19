package com.example.aiplannerapplication.ui.login

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aiplannerapplication.R
import com.example.aiplannerapplication.data.models.AuthState
import com.example.aiplannerapplication.data.models.Credentials
import com.example.aiplannerapplication.navHost.navigateTo
import com.example.aiplannerapplication.viewmodel.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser

@Composable
fun LoginScreenRoute(onLoginSuccess: (user: FirebaseUser)->Unit) {
    LoginScreen(onLoginSuccess=onLoginSuccess, onSignUpClick={})
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (user: FirebaseUser) -> Unit,
    onSignUpClick: () -> Unit
) {
//    val isLoading by viewModel.isLoading.collectAsState()
//    val errorMessage by viewModel.errorMessage.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        Log.d("###", "LoginScreen: comig insidne authState laucnhed eff with auth State as " +
                authState.toString())
        if (authState is AuthState.Authenticated) {
            onLoginSuccess((authState as AuthState.Authenticated<FirebaseUser>).data)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Section
            Spacer(modifier = Modifier.height(48.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "AiPlanner Logo",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE)
            )

            Text(
                text = "Sign in to continue using AiPlanner",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Form Section
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("Enter your email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = Color.Gray
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color(0xFF6200EE)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = Color.Gray
                    )
                },
//                trailingIcon = {
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        Icon(
//                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
//                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
//                            tint = Color.Gray
//                        )
//                    }
//                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color(0xFF6200EE)
                )
            )

            // Remember me and Forgot password row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { rememberMe = !rememberMe }
                ) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF6200EE)
                        )
                    )

                    Text(
                        text = "Remember me",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Text(
                    text = "Forgot Password?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.clickable { /* Handle forgot password */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

//            // Error message if any
//            AnimatedVisibility(visible = errorMessage != null) {
//                errorMessage?.let {
//                    Text(
//                        text = it,
//                        color = Color.Red,
//                        style = MaterialTheme.typography.bodyMedium,
//                        modifier = Modifier.padding(vertical = 8.dp)
//                    )
//                }
//            }

            // Login button
            Button(
                onClick = {
                    Log.d("###", "LoginScreen: login clciked")
                    val creds = Credentials(email, password, email.take(3))
                    Log.d("###", "LoginScreen: creds name " + creds.name)
                    Log.d("###", "LoginScreen: creds email " + creds.email)
                    Log.d("###", "LoginScreen: creds password " + creds.password)
                    viewModel.register(creds)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE)
                ),
                enabled = email.isNotBlank() && password.isNotBlank()
            ) {
//                if (isLoading) {
//                    CircularProgressIndicator(
//                        modifier = Modifier.size(24.dp),
//                        color = Color.White,
//                        strokeWidth = 2.dp
//                    )
//                } else {
//                    Text(
//                        text = "Login",
//                        style = MaterialTheme.typography.bodyLarge,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Divider with "OR" text
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Color.LightGray
                )
                Text(
                    text = "OR",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Social login options
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                SocialLoginButton(
//                    icon = R.drawable.ic,
//                    contentDescription = "Sign in with Google"
//                )
//
//                SocialLoginButton(
//                    icon = R.drawable.ic_facebook,
//                    contentDescription = "Sign in with Facebook"
//                )
//
//                SocialLoginButton(
//                    icon = R.drawable.ic_apple,
//                    contentDescription = "Sign in with Apple"
//                )
//            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sign up link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFF6200EE),
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SocialLoginButton(
    icon: Int,
    contentDescription: String
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color(0xFFF5F5F5))
            .clickable { /* Handle social login */ },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
}