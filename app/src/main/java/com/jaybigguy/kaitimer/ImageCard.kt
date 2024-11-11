package com.jaybigguy.kaitimer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaybigguy.kaitimer.ui.theme.KaitimerTheme
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@ExperimentalMaterial3Api
@Composable
fun ImageCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {

    val tvm = TimerViewModel(30.seconds)

    val timeset = remember { mutableStateListOf<TimerViewModel>() }
   // timeset.add(TimerViewModel(10.5.minutes))

    //dialog that will appear when adding a new timer
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        TimerChipDialog(onConfirm = { timePickerState ->
            timeset.add(TimerViewModel(timePickerStateToDuration(timePickerState)))
            showDialog = false
        }) {
            showDialog = false
        }
    }

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

            Row() {

                VerticalProgress(tvm)

                Spacer(modifier = Modifier.width(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TimerChip(tvm)

                    //TimerChip(TimerViewModel(5.minutes))

                    LazyColumn (verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(timeset.count()){
                                timesetIndex -> TimerChip(tvm = timeset.get(timesetIndex))
                        }
                    }

                    SmallFloatingActionButton(
                        onClick = { showDialog = true },//timeset.add(TimerViewModel(10.5.minutes)) },
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(Icons.Filled.Add, "Small floating action button.")
                    }

                }

            }

//            Spacer(modifier = Modifier.height(8.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End
//            ) {
//                SmallFloatingActionButton(
//                    onClick = { /**/ },
//                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
//                    contentColor = MaterialTheme.colorScheme.secondary
//                ) {
//                    Icon(Icons.Filled.Add, "Small floating action button.")
//                }
//            }

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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewImageCard() {
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

@OptIn(ExperimentalMaterial3Api::class)
fun timePickerStateToDuration(timePickerState: TimePickerState): Duration {
    val hours = timePickerState.hour
    val minutes = timePickerState.minute
    return hours.hours + minutes.minutes
}