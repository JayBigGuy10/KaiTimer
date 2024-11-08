package com.jaybigguy.kaitimer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaybigguy.kaitimer.ui.theme.KaitimerTheme

@ExperimentalMaterial3Api
@Composable
fun ImageCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                VerticalProgress(progress = 0.3f)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = "Hi")
                }
            }


//            Box(
//                modifier = Modifier
//            ) {
//                LinearProgressIndicator(
//                    progress = { 0.35f }, modifier = Modifier
//                        .width(200.dp).height(90.dp)
//                        .rotate(90F),
//                    trackColor = MaterialTheme.colorScheme.primaryContainer,
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }

//            BoxWithConstraints (
//                modifier = Modifier.fillMaxSize()
//            ) {
//                val width = maxWidth // Width becomes height
//                val height = maxWidth // Height becomes width
//                Box(
//                    modifier = Modifier
//                        .size(width, height)
//                        .rotate(90f)
//                ) {
//                    LinearProgressIndicator(
//                    progress = { 0.35f }, modifier = Modifier
//                        .fillMaxSize()
//                        .rotate(0F),
//                    trackColor = MaterialTheme.colorScheme.primaryContainer,
//                    color = MaterialTheme.colorScheme.primary
//                    )
//
//                }
//                Text(text = "Hi", color = MaterialTheme.colorScheme.inversePrimary)
//            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                SmallFloatingActionButton(
                    onClick = { /**/ },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Add, "Small floating action button.")
                }


//                AssistChip(
//                    onClick = { },
//                    colors = AssistChipDefaults.assistChipColors(
//                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
//                    ),
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Outlined.FavoriteBorder,
//                            contentDescription = null
//                        )
//                    },
//                    label = {
//                        Text(text = "Mark as favorite")
//                    }
//                )
//                AssistChip(
//                    onClick = { },
//                    colors = AssistChipDefaults.assistChipColors(
//                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
//                    ),
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Outlined.Share,
//                            contentDescription = null
//                        )
//                    },
//                    label = {
//                        Text(text = "Share with others")
//                    }
//                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun preview() {
    KaitimerTheme {
        ImageCard(
            title = "Page: ",
            description = "Bacon ipsum dolor amet pork shankle beef andouille ball tip. Meatball corned beef swine, strip steak bacon jerky doner tongue biltong pork loin drumstick sausage hamburger burgdoggen.",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(0.9f)
        )
    }
}

@Composable
fun VerticalProgress(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val mProgress = animateFloatAsState(targetValue = progress)
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.background)
            .width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1 - mProgress.value)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ){
            Text(text = "Hi", color = MaterialTheme.colorScheme.onBackground)
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .weight(mProgress.value)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary
                )
        )
    }
}