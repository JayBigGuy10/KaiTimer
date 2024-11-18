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
fun TimerCard(
    tcm: TimerCardModel,
    modifier: Modifier = Modifier
) {
    val title = tcm.title
    val description = tcm.desc

    val tvm = TimerChipModel(30.seconds)

    //dialog that will appear when adding a new timer
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        TimerChipDialog(onConfirm = { timePickerState ->
            tcm.timers.add(TimerChipModel(timePickerStateToDuration(timePickerState)))
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

                //todo work out which tvm to use here
                VerticalProgress(tvm)

                Spacer(modifier = Modifier.width(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TimerChip(tvm)

                    LazyColumn (verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(tcm.timers.count()){
                                timesetIndex -> TimerChip(tvm = tcm.timers.get(timesetIndex))
                        }
                    }

                    SmallFloatingActionButton(
                        onClick = { showDialog = true },
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(Icons.Filled.Add, "Small floating action button.")
                    }

                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewTimerCard() {
    KaitimerTheme {
        TimerCard(
            TimerCardModel(),
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