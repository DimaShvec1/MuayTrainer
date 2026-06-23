package com.example.muaythaitrainer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.muaythaitrainer.R
import com.example.muaythaitrainer.model.Technique

class TechniqueAdapter(
    private val techniques: List<Technique>,
    private val onItemClick: (Technique) -> Unit
) : RecyclerView.Adapter<TechniqueAdapter.TechniqueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechniqueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_technique, parent, false)
        return TechniqueViewHolder(view)
    }

    override fun onBindViewHolder(holder: TechniqueViewHolder, position: Int) {
        val technique = techniques[position]
        holder.bind(technique)
        holder.itemView.setOnClickListener { onItemClick(technique) }

        holder.itemView.alpha = 0f
        holder.itemView.translationY = 80f
        holder.itemView.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(400)
            .setStartDelay((position * 60).toLong())
            .start()
    }

    override fun getItemCount(): Int = techniques.size

    class TechniqueViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_description)
        private val categoryTextView: TextView = itemView.findViewById(R.id.tv_category)
        private val imageView: ImageView = itemView.findViewById(R.id.iv_technique)

        fun bind(technique: Technique) {
            nameTextView.text = technique.name
            descriptionTextView.text = technique.description
            categoryTextView.text = technique.category
            imageView.setImageResource(technique.imageResId)   // ← для карточки (без q)
        }
    }
}