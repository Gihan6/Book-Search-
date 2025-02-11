package com.plcoding.bookpedia.book.prsentation.nav_drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.home
import cmp_bookpedia.composeapp.generated.resources.logout
import cmp_bookpedia.composeapp.generated.resources.logout_light
import cmp_bookpedia.composeapp.generated.resources.setting
import cmp_bookpedia.composeapp.generated.resources.user
import cmp_bookpedia.composeapp.generated.resources.user_icon
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.app.Utils.Companion.loginUser
import com.plcoding.bookpedia.core.presentation.DarkBlue
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    navigateToUserProfile: () -> Unit={},
    closeDrawer: () -> Unit = {}
) {
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    ModalDrawerSheet(modifier = Modifier) {
        Box(modifier.fillMaxHeight()) {
            Column(modifier = modifier.fillMaxWidth()) {
                Spacer(Modifier.size(60.dp))
                IconButton(modifier = modifier.size(80.dp)
                    .align(Alignment.CenterHorizontally),
                    onClick = {
                        navigateToUserProfile()
                        closeDrawer()
                    }) {
                    Image(
                        painter = painterResource(Res.drawable.user),
                        contentDescription = stringResource(Res.string.user_icon),
                    )
                }

                Text(
                    text = loginUser?.name ?: "",
                    color = DarkBlue,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.align(Alignment.CenterHorizontally).padding(20.dp)
                )

                Spacer(Modifier.size(16.dp))
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(Res.string.home),
                            color = DarkBlue,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = 0 == selectedItemIndex,
                    onClick = {
                        selectedItemIndex=0
                        navigateToHome()
                        closeDrawer()

                    },
                    icon = {
                        Icon(
                            imageVector = if (0 == selectedItemIndex) Icons.Filled.Home else Icons.Outlined.Home,
                            contentDescription = stringResource(Res.string.home),
                            tint = DarkBlue
                        )
                    },
                    badge = {

                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    shape = MaterialTheme.shapes.small

                )

                Spacer(Modifier.size(16.dp))
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(Res.string.setting),
                            color = DarkBlue,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = 1 == selectedItemIndex,
                    onClick = {
                        selectedItemIndex=1
                        navigateToSettings()
                        closeDrawer()
                    },
                    icon = {
                        Icon(
                            imageVector = if (0 == selectedItemIndex) Icons.Filled.Settings else Icons.Outlined.Settings,
                            contentDescription = stringResource(Res.string.setting),
                            tint = DarkBlue
                        )
                    },
                    badge = {

                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    shape = MaterialTheme.shapes.small

                )
            }

            Row(
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            ) {
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(Res.string.logout),
                            color = DarkBlue,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = 2== selectedItemIndex,
                    onClick = {
                        selectedItemIndex=2
                        closeDrawer()

                    },
                    icon = {
                        Icon(
                            painter = if (2 == selectedItemIndex)
                                painterResource(Res.drawable.logout)
                            else
                                painterResource(Res.drawable.logout_light),
                            contentDescription = stringResource(Res.string.logout),
                            tint = DarkBlue,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    badge = {

                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    shape = MaterialTheme.shapes.small

                )
            }

        }


    }
}
