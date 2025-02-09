package com.plcoding.bookpedia.book.prsentation.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.alert
import cmp_bookpedia.composeapp.generated.resources.book_icon
import cmp_bookpedia.composeapp.generated.resources.email
import cmp_bookpedia.composeapp.generated.resources.hide
import cmp_bookpedia.composeapp.generated.resources.login_to_your_account
import cmp_bookpedia.composeapp.generated.resources.logo_icon
import cmp_bookpedia.composeapp.generated.resources.not_have_account
import cmp_bookpedia.composeapp.generated.resources.ok
import cmp_bookpedia.composeapp.generated.resources.password
import cmp_bookpedia.composeapp.generated.resources.show
import cmp_bookpedia.composeapp.generated.resources.sign_in
import cmp_bookpedia.composeapp.generated.resources.sign_up
import com.plcoding.bookpedia.book.domaine.User
import com.plcoding.bookpedia.book.prsentation.animation.PulseAnimation
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.DesertWhite
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel,
    navigateToMain: () -> Unit,
    navigateToRegister: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    if (state.isLogin)
        navigateToMain()
    LoginScreen(
        modifier = Modifier,
        state = state,
        onAction = { action ->
            when (action) {
                is LoginActions.OnRegisterClick -> navigateToRegister()
                else -> {
                    viewModel.onAction(action)
                }
            }
        }
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginStates,
    onAction: (action: LoginActions) -> Unit
) {
    var userName by remember {
        mutableStateOf("")
    }
    var userPassword by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var showDialog = remember { mutableStateOf(false) }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DesertWhite)
            .padding(30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(Res.drawable.book_icon),
                contentDescription = stringResource(Res.string.logo_icon),
                modifier = Modifier.size(80.dp),
            )
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(Res.string.login_to_your_account),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
                    .padding(bottom = 16.dp),
            )
            OutlinedTextField(
                value = userName,
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
                onValueChange = {
                    userName = it
                },
                label = {
                    Text(
                        text = stringResource(Res.string.email)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
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
                singleLine = true,
                onValueChange = {
                    userPassword = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
                        LoginActions.OnLogin(
                            User(
                                email = userName,
                                name = "",
                                phone = "",
                                password = userPassword,
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
                    text = stringResource(Res.string.sign_in),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(Res.string.not_have_account),
                color = Color.DarkGray,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(Res.string.sign_up),
                color = DarkBlue,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .clickable(
                        enabled = true,
                        onClick = {
                            onAction(
                                LoginActions.OnRegisterClick
                            )
                        }
                    ),
            )
        }

        if (state.isLoading)
            PulseAnimation(modifier = modifier.size(80.dp).align(Alignment.Center))

        if (state.error != null) {
            showDialog.value = true

            if (showDialog.value) { // 2
                AlertDialog(
                    containerColor = Color.White,
                    onDismissRequest = { // 4
                        showDialog.value = false
                        onAction(LoginActions.ClearError)
                    },

                    title = { Text(text = stringResource(Res.string.alert)) },
                    text = { Text(text = state.error.asString()) },
                    confirmButton = { // 6
                        Button(
                            onClick = {
                                showDialog.value = false
                                onAction(LoginActions.ClearError)

                            }
                        ) {
                            Text(
                                text = stringResource(Res.string.ok),
                                color = Color.White
                            )
                        }
                    }
                )
            }

        }
    }


}