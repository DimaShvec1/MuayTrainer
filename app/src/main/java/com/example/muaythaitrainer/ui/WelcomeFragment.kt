package com.example.muaythaitrainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.muaythaitrainer.MainActivity
import com.example.muaythaitrainer.R

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStart = view.findViewById<Button>(R.id.btn_start_app)
        btnStart.setOnClickListener {
            (activity as? MainActivity)?.showNavigation()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TechniquesFragment())
                .commit()
        }
    }
}