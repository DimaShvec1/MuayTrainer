package com.example.muaythaitrainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.muaythaitrainer.R
import com.example.muaythaitrainer.model.Technique

class TechniqueDetailFragment : Fragment() {

    private lateinit var technique: Technique

    companion object {
        fun newInstance(technique: Technique): TechniqueDetailFragment {
            val fragment = TechniqueDetailFragment()
            val args = Bundle()
            args.putSerializable("technique", technique)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        technique = arguments?.getSerializable("technique") as Technique
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_technique_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameTextView: TextView = view.findViewById(R.id.tv_detail_name)
        val descriptionTextView: TextView = view.findViewById(R.id.tv_detail_description)
        val categoryTextView: TextView = view.findViewById(R.id.tv_detail_category)
        val imageView: ImageView = view.findViewById(R.id.iv_detail_image)
        val btnBack: Button = view.findViewById(R.id.btn_back)

        nameTextView.text = technique.name
        descriptionTextView.text = technique.description
        categoryTextView.text = "Категория: ${technique.category}"
        imageView.setImageResource(technique.detailImageResId)   // ← для детального экрана (с q)

        imageView.setOnClickListener {
            val videoUrl = "https://www.youtube.com/watch?v=${technique.videoId}"
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(videoUrl))
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}