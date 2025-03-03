package com.plcoding.bookpedia.book.prsentation.setting

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.arabic
import cmp_bookpedia.composeapp.generated.resources.back
import cmp_bookpedia.composeapp.generated.resources.english
import cmp_bookpedia.composeapp.generated.resources.go_back
import cmp_bookpedia.composeapp.generated.resources.language
import cmp_bookpedia.composeapp.generated.resources.languages
import cmp_bookpedia.composeapp.generated.resources.setting
import com.plcoding.bookpedia.book.prsentation.register.RegisterActions
import com.plcoding.bookpedia.book.prsentation.register.RegisterScreen
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.DesertWhite
import io.ktor.util.hex
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource


@Composable
fun SettingScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel,
    startApp: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    if (state.isLaunchApp) {
        startApp()
    }
    SettingScreen(
        modifier = modifier,
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )


}

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    state: SettingStates,
    onAction: (SettingActions) -> Unit

) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DesertWhite)
            .padding(top = 120.dp, bottom = 10.dp, start = 8.dp, end = 8.dp)
    ) {

        Column {

            Text(
                text = stringResource(Res.string.setting),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                ),
                modifier = Modifier
                    .padding(8.dp)
            )

            Box(
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    text = stringResource(Res.string.language),
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                var isToggled by remember { mutableStateOf(false) }
                isToggled = if (state.selectLanguage == Language.English.iso)
                    false
                else
                    true

                Button(
                    onClick = {
                        isToggled = !isToggled
                        if (isToggled)
                            onAction(SettingActions.ChangeLanguage(Language.Arabic))
                        else
                            onAction(SettingActions.ChangeLanguage(Language.English))
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isToggled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        contentColor = if (isToggled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        if (isToggled)
                            stringResource(Res.string.arabic)
                        else
                            stringResource(Res.string.english)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )

        }

    }
}