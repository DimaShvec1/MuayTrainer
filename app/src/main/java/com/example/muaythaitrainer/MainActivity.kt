package com.example.muaythaitrainer

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.muaythaitrainer.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    // Таймер
    private var countDownTimer: CountDownTimer? = null
    private var isRunning = false
    private var timeLeftInMillis = 180000L
    private val initialTime = 180000L
    private var currentRound = 1
    private val totalRounds = 3
    private var isResting = false
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null
    private var timerListener: TimerUpdateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation)
        vibrator = ContextCompat.getSystemService(this, Vibrator::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WelcomeFragment())
                .commit()
            bottomNav.visibility = android.view.View.GONE
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_techniques -> {
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container, TechniquesFragment())
                        .commit()
                    true
                }
                R.id.nav_workout -> {
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container, WorkoutFragment())
                        .commit()
                    true
                }
                R.id.nav_timer -> {
                    val fragment = TimerFragment()
                    timerListener = fragment
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                    true
                }
                R.id.nav_dictionary -> {
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container, DictionaryFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    fun showNavigation() {
        bottomNav.visibility = android.view.View.VISIBLE
        bottomNav.selectedItemId = R.id.nav_techniques
    }

    // ============ МЕТОДЫ ТАЙМЕРА ============
    fun startTimer(timeInMillis: Long) {
        if (isRunning) return
        timeLeftInMillis = timeInMillis
        isRunning = true
        timerListener?.onTimerUpdate(formatTime(timeLeftInMillis), 10000, "⚔️ БОЙ!", "Раунд 1 из $totalRounds")
        playSound(R.raw.boxing_start)

        countDownTimer = object : CountDownTimer(timeLeftInMillis, 100) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                val progress = (timeLeftInMillis.toFloat() / initialTime.toFloat() * 10000).toInt()
                timerListener?.onTimerUpdate(formatTime(timeLeftInMillis), progress, "⚔️ БОЙ!", "Раунд $currentRound из $totalRounds")
            }

            override fun onFinish() {
                isRunning = false
                playSound(R.raw.boxing_end)
                vibrate()

                if (isResting) {
                    isResting = false
                    currentRound++
                    if (currentRound > totalRounds) {
                        timerListener?.onTimerFinished()
                        return
                    }
                    timeLeftInMillis = initialTime
                    timerListener?.onTimerUpdate("03:00", 10000, "⚔️ БОЙ!", "Раунд $currentRound из $totalRounds")
                    playSound(R.raw.boxing_start)
                    startTimer(initialTime)
                } else {
                    isResting = true
                    timeLeftInMillis = 60000L
                    timerListener?.onTimerUpdate("01:00", 10000, "💤 ОТДЫХ", "Отдых $currentRound")
                    playSound(R.raw.boxing_end)
                    startTimer(60000L)
                }
            }
        }.start()
    }

    fun pauseTimer() {
        countDownTimer?.cancel()
        isRunning = false
        timerListener?.onTimerUpdate(formatTime(timeLeftInMillis), 0, "⏸ Пауза", "Раунд $currentRound из $totalRounds")
    }

    fun resetTimer() {
        countDownTimer?.cancel()
        isRunning = false
        isResting = false
        currentRound = 1
        timeLeftInMillis = initialTime
        timerListener?.onTimerUpdate("03:00", 10000, "🔴 Ждём старта", "Раунд 1 из $totalRounds")
    }

    fun setTimerTime(timeInMillis: Long) {
        if (!isRunning) {
            timeLeftInMillis = timeInMillis
            timerListener?.onTimerUpdate(formatTime(timeInMillis), 10000, "🔴 Ждём старта", "Раунд 1 из $totalRounds")
        }
    }

    private fun formatTime(millis: Long): String {
        val minutes = millis / 60000
        val seconds = (millis % 60000) / 1000
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun playSound(rawId: Int) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(this, rawId)
            mediaPlayer?.start()
        } catch (_: Exception) { }
    }

    private fun vibrate() {
        try {
            vibrator?.let { v ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    v.vibrate(500)
                }
            }
        } catch (_: Exception) { }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}