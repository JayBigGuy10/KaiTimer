package com.jaybigguy.kaitimer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class TimerViewModel(givenDuration: Duration) : ViewModel() {

    private lateinit var timer: CountDownTimer

    //time that the timer has been running for
    private val _time: MutableLiveData<Duration> = MutableLiveData(0.seconds)
    val time: LiveData<Duration> = _time

    //amount of time the timer is going to run for before alarming
    private val _duration: MutableLiveData<Duration> = MutableLiveData(0.seconds)
    val duration: LiveData<Duration> = _duration

    //current state of the timer
    private val _state = MutableLiveData(TimerState.INITIAL)
    val state: LiveData<TimerState> = _state

    var timeStarted: Long = 0

    private var timerJob: Job? = null


    init {
        _duration.value = givenDuration
    }

    fun onTimeChange(newTime: Duration) {
        _time.value = newTime
    }

    fun onTimerStart(time: Long) {
        timer = object : CountDownTimer(time, 500) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                _state.value = TimerState.ALARMING_FINISH
            }
        }
        timer.start()
    }

    fun start(){
        _state.value = TimerState.COUNTING
        timeStarted = System.currentTimeMillis()

        timerJob = viewModelScope.launch {
            while (true) {
                delay(500)
                onTick()
            }
        }
    }

    fun onTick(){
        val currentClockTime = System.currentTimeMillis()

        val durationMillis: Long = _duration.value?.inWholeMilliseconds ?: Long.MIN_VALUE

        if (currentClockTime > (durationMillis + timeStarted)){
            _state.value = TimerState.ALARMING_FINISH
        } else if (_state.value == TimerState.ALARMING_FINISH){
            _state.value = TimerState.COUNTING
        }

        _time.value = (currentClockTime - timeStarted).toDuration(DurationUnit.MILLISECONDS)

    }

    //call while running
    fun addTime(additionalDuration: Duration){
        _duration.value = _duration.value!! + additionalDuration;
    }

    fun onTimerFinish() {
        timer.cancel()

    }

}

enum class TimerState{
    ALARMING_FINISH,
    ALARMING_START,
    INITIAL,
    COUNTING
}