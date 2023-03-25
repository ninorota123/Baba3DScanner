package com.bestgame.baba3dscanner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class ModelListAdapter(
    private val context: Context,
    private val models: List<File>,
    private val onModelClickListener: (File) -> Unit
) : RecyclerView.Adapter<ModelListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val modelNameTextView: TextView = itemView.findViewById(R.id.model_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onModelClickListener(models[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.model_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = models[position]
        holder.modelNameTextView.text = model.name
    }

    override fun getItemCount(): Int {
        return models.size
    }
}
