package com.example.muaythaitrainer.ui

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.muaythaitrainer.R

class WorkoutFragment : Fragment() {

    private lateinit var exerciseTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var startButton: Button
    private lateinit var resetButton: Button

    private var countDownTimer: CountDownTimer? = null
    private var isRunning = false
    private var currentExerciseIndex = 0
    private var isResting = false
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null

    private val workTime = 45000L  // 45 секунд
    private val restTime = 15000L   // 15 секунд
    private var timeLeft = workTime

    private val exercises = listOf(
        "🔥 Бой с тенью",
        "💪 Отжимания",
        "🦵 Приседания",
        "🔄 Бёрпи",
        "💪 Пресс",
        "🦵 Выпады",
        "🏃 Скакалка",
        "🥊 Джебы с резинкой"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseTextView = view.findViewById(R.id.tv_exercise)
        timerTextView = view.findViewById(R.id.tv_timer_workout)
        statusTextView = view.findViewById(R.id.tv_status_workout)
        startButton = view.findViewById(R.id.btn_start_workout)
        resetButton = view.findViewById(R.id.btn_reset_workout)

        vibrator = ContextCompat.getSystemService(requireContext(), Vibrator::class.java)

        updateExerciseDisplay()
        timerTextView.text = "00:45"
        statusTextView.text = "🔴 Готов к тренировке"

        startButton.setOnClickListener {
            if (isRunning) pauseWorkout() else startWorkout()
        }

        resetButton.setOnClickListener { resetWorkout() }
    }

    private fun startWorkout() {
        isRunning = true
        startButton.text = "⏸ Пауза"
        statusTextView.text = "⚡ РАБОТА!"

        // Звук начала упражнения (если это не первый старт после паузы)
        if (!isResting && currentExerciseIndex < exercises.size) {
            playSound(R.raw.boxing_start)
        }

        countDownTimer = object : CountDownTimer(timeLeft, 100) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateTimerDisplay(millisUntilFinished)
            }

            override fun onFinish() {
                // Звук окончания + вибрация
                playSound(R.raw.boxing_end)
                vibrate()

                if (isResting) {
                    // Отдых закончился → следующее упражнение
                    isResting = false
                    currentExerciseIndex++
                    if (currentExerciseIndex >= exercises.size) {
                        exerciseTextView.text = "🎉 Тренировка завершена!"
                        timerTextView.text = "🏆"
                        statusTextView.text = "✅ ГОТОВО!"
                        isRunning = false
                        startButton.text = "✅ Готово"
                        playSound(R.raw.boxing_end) // финальный звук
                        vibrate()
                        return
                    }
                    timeLeft = workTime
                    statusTextView.text = "⚡ РАБОТА!"
                    updateExerciseDisplay()
                    playSound(R.raw.boxing_start) // звук начала упражнения
                    startWorkout()
                } else {
                    // Работа закончилась → отдых
                    isResting = true
                    timeLeft = restTime
                    statusTextView.text = "💤 ОТДЫХ"
                    exerciseTextView.text = "😮‍💨 Отдых"
                    playSound(R.raw.boxing_start) // звук начала отдыха
                    startWorkout()
                }
            }
        }.start()
    }

    private fun pauseWorkout() {
        countDownTimer?.cancel()
        isRunning = false
        startButton.text = "▶ Продолжить"
        statusTextView.text = "⏸ Пауза"
    }

    private fun resetWorkout() {
        countDownTimer?.cancel()
        isRunning = false
        isResting = false
        currentExerciseIndex = 0
        timeLeft = workTime
        timerTextView.text = "00:45"
        statusTextView.text = "🔴 Готов к тренировке"
        updateExerciseDisplay()
        startButton.text = "▶ Старт"
    }

    private fun updateExerciseDisplay() {
        if (currentExerciseIndex < exercises.size) {
            exerciseTextView.text = exercises[currentExerciseIndex]
        }
    }

    private fun updateTimerDisplay(millis: Long) {
        val seconds = millis / 1000
        timerTextView.text = String.format("%02d:%02d", seconds / 60, seconds % 60)
    }

    private fun playSound(rawId: Int) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(requireContext(), rawId)
            mediaPlayer?.start()
        } catch (_: Exception) { }
    }

    private fun vibrate() {
        try {
            vibrator?.let { v ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    v.vibrate(300)
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