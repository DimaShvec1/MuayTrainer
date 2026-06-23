package com.example.muaythaitrainer.ui

interface TimerUpdateListener {
    fun onTimerUpdate(time: String, progress: Int, status: String, round: String)
    fun onTimerFinished()
}