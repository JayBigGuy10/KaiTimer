package com.jaybigguy.kaitimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class TimerGroupViewModel (): ViewModel(){

    //time that the timer has been running for
    private val _time: MutableLiveData<Duration> = MutableLiveData(0.seconds)
    val time: LiveData<Duration> = _time

}