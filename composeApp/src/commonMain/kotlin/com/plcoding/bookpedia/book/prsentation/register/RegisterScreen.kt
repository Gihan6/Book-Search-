package com.plcoding.bookpedia.book.prsentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.book_icon
import cmp_bookpedia.composeapp.generated.resources.create_your_account
import cmp_bookpedia.composeapp.generated.resources.email
import cmp_bookpedia.composeapp.generated.resources.full_name
import cmp_bookpedia.composeapp.generated.resources.go_back
import cmp_bookpedia.composeapp.generated.resources.hide
import cmp_bookpedia.composeapp.generated.resources.logo_icon
import cmp_bookpedia.composeapp.generated.resources.password
import cmp_bookpedia.composeapp.generated.resources.phone
import cmp_bookpedia.composeapp.generated.resources.show
import cmp_bookpedia.composeapp.generated.resources.sign_up
import com.plcoding.bookpedia.book.domaine.User
import com.plcoding.bookpedia.book.prsentation.animation.PulseAnimation
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.DesertWhite
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterViewModel,
    onBackClick: () -> Unit,
    navigateToMain: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    if (state.registerSuccess)
        navigateToMain()

    RegisterScreen(
        modifier = Modifier,
        state = state,
        onAction = { action ->
            when (action) {
                is RegisterActions.OnBackPress -> onBackClick()
                else -> viewModel.onAction(action)
            }

        }
    )
}

@Composable
fun RegisterScreen(modifier: Modifier, state: RegisterState, onAction: (RegisterActions) -> Unit) {
    var email by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var phone by remember {
        mutableStateOf("")
    }
    var userPassword by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DesertWhite)
            .padding(30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = stringResource(Res.string.go_back),
                tint = DarkBlue,
                modifier = Modifier.align(Alignment.Start).clickable(
                    enabled = true,
                    onClick = {
                        onAction(RegisterActions.OnBackPress)
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(Res.drawable.book_icon),
                contentDescription = stringResource(Res.string.logo_icon),
                modifier = Modifier.size(80.dp),
            )
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(Res.string.create_your_account),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
                    .padding(bottom = 16.dp),
            )
            OutlinedTextField(
                value = email,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = DarkBlue,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = DarkBlue
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = {
                    email = it
                },
                label = {
                    Text(
                        text = stringResource(Res.string.email)
                    )
                }
            )
            OutlinedTextField(
                value = name,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = DarkBlue,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = DarkBlue
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                onValueChange = {
                    name = it
                },
                label = {
                    Text(
                        text = stringResource(Res.string.full_name)
                    )
                }
            )
            OutlinedTextField(
                value = phone,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = DarkBlue,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = DarkBlue
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = {
                    phone = it
                },
                label = {
                    Text(
                        text = stringResource(Res.string.phone)
                    )
                }
            )
            OutlinedTextField(
                value = userPassword,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = DarkBlue,
                    focusedTextColor = Color.Black,
                    focusedLabelColor = DarkBlue
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                singleLine = true,
                onValueChange = {
                    userPassword = it
                },
                label = {
                    Text(
                        text = stringResource(Res.string.password)
                    )
                },
                visualTransformation = if (showPassword) {

                    VisualTransformation.None

                } else {

                    PasswordVisualTransformation()

                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                painter = painterResource(Res.drawable.show),
                                contentDescription = "hide_password",
                                modifier = Modifier.size(20.dp)

                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }) {
                            Icon(
                                painter = painterResource(Res.drawable.hide),
                                contentDescription = "hide_password",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

            )
            Button(
                onClick = {
                    onAction(
                        RegisterActions.Register(
                            User(
                                email = email,
                                password = userPassword,
                                name = name,
                                phone = phone
                            )
                        )
                    )
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(55.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = DarkBlue
                )

            )
            {
                Text(
                    text = stringResource(Res.string.sign_up),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                )
            }

        }
        if (state.isLoading)
            PulseAnimation(
                modifier = modifier
                    .size(80.dp)
                    .align(Alignment.Center)
            )

        state.error?.let {
            Text(
                text = it.asString(),
                modifier = modifier.align(Alignment.BottomCenter).padding(bottom = 50.dp),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red
            )
        }

    }
}
