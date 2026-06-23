package com.example.muaythaitrainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.muaythaitrainer.MainActivity
import com.example.muaythaitrainer.R

class TimerFragment : Fragment(), TimerUpdateListener {

    private lateinit var timerTextView: TextView
    private lateinit var progressImage: ImageView
    private lateinit var startButton: Button
    private lateinit var resetButton: Button
    private lateinit var roundTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var minutesInput: EditText
    private lateinit var secondsInput: EditText
    private lateinit var applyTimeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timerTextView = view.findViewById(R.id.tv_timer)
        progressImage = view.findViewById(R.id.circle_progress)
        startButton = view.findViewById(R.id.btn_start_timer)
        resetButton = view.findViewById(R.id.btn_reset_timer)
        roundTextView = view.findViewById(R.id.tv_round)
        statusTextView = view.findViewById(R.id.tv_status)
        minutesInput = view.findViewById(R.id.et_minutes)
        secondsInput = view.findViewById(R.id.et_seconds)
        applyTimeButton = view.findViewById(R.id.btn_apply_time)

        progressImage.setImageLevel(10000)
        timerTextView.text = "03:00"
        roundTextView.text = "⚔️ Раунд 1 из 3"
        statusTextView.text = "🔴 Ждём старта"

        applyTimeButton.setOnClickListener {
            val minutes = minutesInput.text.toString().toIntOrNull() ?: 0
            val seconds = secondsInput.text.toString().toIntOrNull() ?: 0
            val totalSeconds = minutes * 60 + seconds
            if (totalSeconds > 0) {
                (activity as? MainActivity)?.setTimerTime(totalSeconds * 1000L)
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }
        }

        startButton.setOnClickListener {
            val activity = activity as? MainActivity
            if (activity != null) {
                val minutes = minutesInput.text.toString().toIntOrNull() ?: 0
                val seconds = secondsInput.text.toString().toIntOrNull() ?: 0
                val totalSeconds = minutes * 60 + seconds
                if (totalSeconds > 0) {
                    activity.startTimer(totalSeconds * 1000L)
                }
            }
        }

        resetButton.setOnClickListener {
            (activity as? MainActivity)?.resetTimer()
        }
    }

    override fun onTimerUpdate(time: String, progress: Int, status: String, round: String) {
        timerTextView.text = time
        progressImage.setImageLevel(progress)
        statusTextView.text = status
        roundTextView.text = round
    }

    override fun onTimerFinished() {
        timerTextView.text = "🏆"
        statusTextView.text = "✅ ГОТОВО!"
        progressImage.setImageLevel(0)
        roundTextView.text = "🎉 Тренировка завершена!"
    }
}