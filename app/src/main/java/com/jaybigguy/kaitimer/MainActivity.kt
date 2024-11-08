package com.jaybigguy.kaitimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaybigguy.kaitimer.ui.theme.KaitimerTheme
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InnerApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun InnerApp() {
    KaitimerTheme {
        Surface (modifier=Modifier.fillMaxSize(), color= MaterialTheme.colorScheme.background) {

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet {
                        Text("Drawer title", modifier = Modifier.padding(16.dp))
                        HorizontalDivider()
                        NavigationDrawerItem(
                            label = { Text(text = "Drawer Item") },
                            selected = false,
                            onClick = { /*TODO*/ }
                        )
                        // ...other drawer items
                    }
                },
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen
            ) {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Chicken & Rice")
                            },
                            navigationIcon = {

                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            actions = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    // Display 10 items
                    val pagerState = rememberPagerState(pageCount = {
                        4
                    })

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)  // Use padding provided by Scaffold
                    ) {

                        HorizontalPager(state = pagerState) { page ->
                            ImageCard(
                                title = "Page: $page",
                                description = "Bacon ipsum dolor amet pork shankle beef andouille ball tip. Meatball corned beef swine, strip steak bacon jerky doner tongue biltong pork loin drumstick sausage hamburger burgdoggen.",
                                modifier = Modifier.padding(16.dp).fillMaxHeight(0.9f)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        //.clip(CircleShape)
                                        .background(color)
                                        .size(16.dp)
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}