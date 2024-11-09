package com.jaybigguy.kaitimer

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaybigguy.kaitimer.ui.theme.KaitimerTheme
import java.util.Timer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

@SuppressLint("DefaultLocale")
@Composable
fun TimerChip(tvm: TimerViewModel){

    //val state: MutableState<Int> = rememberSaveable { mutableStateOf(1) }

    val time by tvm.time.observeAsState()

    val duration by tvm.duration.observeAsState()

    val state by tvm.state.observeAsState()

    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /*TODO*/ }) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 16.dp, end = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column (modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5F), verticalArrangement = Arrangement.Center) {

                if (state == TimerState.INITIAL){
                    Text(
                        text = duration!!.toComponents { minutes, seconds, _ -> "${String.format("%02d",minutes)}:${String.format("%02d",seconds)}"  },
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.background)
                } else {
                    Text(
                        text = time!!.toComponents { minutes, seconds, _ -> "${String.format("%02d",minutes)}:${String.format("%02d",seconds)}"  },
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.background)
                }


                Text(text = state.toString(), color = MaterialTheme.colorScheme.background)
            }
            Column (modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {

                if (state == TimerState.INITIAL) {

                    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                        Icon(painter = painterResource(R.drawable.running_with_errors), "Play", tint = MaterialTheme.colorScheme.onError)
                    }

                    Button(onClick = { tvm.start() }) {
                        Icon(Icons.Default.PlayArrow, "Play")
                    }
                } else if (state == TimerState.COUNTING || state == TimerState.ALARMING_START || state == TimerState.ALARMING_FINISH){

                    Button(onClick = { tvm.onTick() }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                        Icon(painter = painterResource(R.drawable.pause), "Play", tint = MaterialTheme.colorScheme.onError)
                    }

                    Button(onClick = { tvm.addTime(1.minutes) }) {
                        Icon(painter = painterResource(R.drawable.plus_one), "Play", tint = MaterialTheme.colorScheme.onError)
                    }
                }
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewTimerChip() {
    KaitimerTheme {
        TimerChip(TimerViewModel(1.5.minutes))
    }
}