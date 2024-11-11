package com.jaybigguy.kaitimer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class TimerViewModel(givenDuration: Duration) : ViewModel() {

    private lateinit var timer: CountDownTimer

    //time that the timer has been running for
    private val _time: MutableLiveData<Duration> = MutableLiveData(0.seconds)
    val time: LiveData<Duration> = _time

    //amount of time the timer is going to run for before alarming
    private val _remaining: MutableLiveData<Duration> = MutableLiveData(0.seconds)
    val remaining: LiveData<Duration> = _remaining

    //amount of time the timer is going to run for before alarming
    private val _duration: MutableLiveData<Duration> = MutableLiveData(0.seconds)
    val duration: LiveData<Duration> = _duration

    //current state of the timer
    private val _state = MutableLiveData(TimerState.INITIAL)
    val state: LiveData<TimerState> = _state

    //when the timer was started
    var timeStarted: Long = 0

    //percentage done
    private val _progress: MutableLiveData<Float> = MutableLiveData(0.5f)
    val progress: LiveData<Float> = _progress

    private var timerJob: Job? = null

    init {
        _duration.value = givenDuration
    }

    fun onTimeChange(newTime: Duration) {
        _time.value = newTime
    }

//    fun onTimerStart(time: Long) {
//        timer = object : CountDownTimer(time, 500) {
//            override fun onTick(millisUntilFinished: Long) {
//
//            }
//
//            override fun onFinish() {
//                _state.value = TimerState.ALARMING_FINISH
//            }
//        }
//        timer.start()
//    }

    fun start(){
        _state.value = TimerState.COUNTING
        timeStarted = System.currentTimeMillis()

        timerJob = viewModelScope.launch {
            while (true) {
                delay(10)
                onTick()
            }
        }
    }

    //TODO Optimise perf (only the progress float needs 10ms, rest are fine at 1 sec)
    fun onTick(){
        val currentClockTime = System.currentTimeMillis()

        val durationMillis: Long = _duration.value?.inWholeMilliseconds ?: Long.MIN_VALUE

        if (currentClockTime > (durationMillis + timeStarted)){
            _state.value = TimerState.ALARMING_FINISH
        } else if (_state.value == TimerState.ALARMING_FINISH){
            _state.value = TimerState.COUNTING
        }

        val elapsed = (currentClockTime - timeStarted)

        _progress.value = elapsed.toFloat()/durationMillis.toFloat()

        _time.value = elapsed.toDuration(DurationUnit.MILLISECONDS)

        _remaining.value = (durationMillis - (elapsed)).toDuration(DurationUnit.MILLISECONDS)

    }

    fun setDuration(newDuration: Duration){
        _duration.value = newDuration
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