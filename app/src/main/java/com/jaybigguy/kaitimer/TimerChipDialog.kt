package com.jaybigguy.kaitimer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaybigguy.kaitimer.ui.theme.KaitimerTheme
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerChipDialog(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    var number: MutableState<Int?> = remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(timePickerState) }) {
                Text("OK")
            }
        },
        text = {
            Column {
                TimeInput(
                    state = timePickerState,)

                //NumberInputField()

                Row(
                    //modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    TimeCard(
                        time = 22,//selectedHour,
                        isSelected = true,//selectedPart == TimePart.Hour,
                        onClick = {}//{ selectedPart = TimePart.Hour }
                    )

                    Text(
                        text = ":",
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    TimeCard(
                        time = 22,//selectedHour,
                        isSelected = false,//selectedPart == TimePart.Hour,
                        onClick = {}//{ selectedPart = TimePart.Hour }
                    )
//
//                    TimeCard(
//                        time = selectedMinute,
//                        isSelected = selectedPart == TimePart.Minute,
//                        onClick = { selectedPart = TimePart.Minute }
//                    )
                }
            }
        }
    )
}

@Composable
fun NumberInputField() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { input ->
            // Filter out non-numeric characters
            text = input.filter { it.isDigit() }
        },
        label = { Text("Enter a number") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewTimerChipDialog() {
    KaitimerTheme {
        TimerChipDialog(onConfirm = {}) {
            
        }
    }
}

@Composable
fun TimeCard(
    time: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
//
//    OutlinedCard(
//        modifier = Modifier.clickable { onClick() },
//        colors = CardDefaults.cardColors(
//            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
//        ),
//        shape = MaterialTheme.shapes.small,
//        border = if (isSelected) BorderStroke(1.dp,MaterialTheme.colorScheme.onPrimaryContainer) else BorderStroke(0.dp,MaterialTheme.colorScheme.surface)
//    )

    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            )
            .border(
                width = 2.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            ).width(88.dp)
    ){
        BasicTextField(
            value = "22",
            onValueChange = {},
            textStyle = MaterialTheme.typography.displayLarge.merge(TextStyle(color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface)),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

//        Text(
//            text = if (time == 0) "00" else time.toString(),
//            style = MaterialTheme.typography.displayLarge,
//            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
//            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
//        )


    }
}