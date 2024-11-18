package com.jaybigguy.kaitimer

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerCardModel : ViewModel(){

    //all the timer chips that belong to this card
    val timers = mutableStateListOf<TimerChipModel>()

    // add new timer chip
    fun addItem(item: TimerChipModel) {
        timers.add(item)
    }

    // remove a timer chip
    fun removeItem(item: TimerChipModel) {
        timers.remove(item)
    }

    private var timerJob: Job? = null

    val title: String = "Hello"
    val desc: String = "HI"

    init {
        timerJob = viewModelScope.launch {
            while (true) {
                delay(10)
                timers.forEach {
                    timer -> timer.onTick()

                    //todo figure out which one will finish last
                }
            }
        }
    }
}