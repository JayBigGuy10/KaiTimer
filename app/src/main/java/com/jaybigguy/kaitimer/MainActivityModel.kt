package com.jaybigguy.kaitimer

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainActivityModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        private const val TIMER_CARDS_KEY = "timer_cards"
    }

    // Initialize `timerCards` using SavedStateHandle or provide a default
    val timerCards = mutableStateListOf<TimerCardModel>().apply {
        savedStateHandle.get<List<TimerCardModel>>(TIMER_CARDS_KEY)?.let {
            addAll(it)
        }
    }

    // Update SavedStateHandle whenever `timerCards` changes
    fun addTimerCard(timerCard: TimerCardModel) {
        timerCards.add(timerCard)
        saveState()
    }

    fun removeTimerCard(timerCard: TimerCardModel) {
        timerCards.remove(timerCard)
        saveState()
    }

    private fun saveState() {
        savedStateHandle[TIMER_CARDS_KEY] = timerCards.toList()
    }
}