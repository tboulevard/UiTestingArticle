package com.boulevard.uitestingarticle.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.boulevard.uitestingarticle.R
import com.boulevard.uitestingarticle.core.model.Plant
import kotlinx.android.synthetic.main.item_plant_list.view.*

class PlantListAdapter(
    private val context: Context,
    private val plantList: List<Plant>,
    private val plantItemClickListener: OnPlantItemClickedListener
) : RecyclerView.Adapter<PlantListAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {

        val inflater = LayoutInflater.from(context)
        val plantItemView = inflater.inflate(R.layout.item_plant_list, parent, false)
        return PlantViewHolder(plantItemView)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: PlantListAdapter.PlantViewHolder, position: Int) {
        val plant = plantList[position]
        holder.plantName.text = plant.name
    }

    inner class PlantViewHolder(
        plantItemView: View
    ) : RecyclerView.ViewHolder(plantItemView) {

        val plantName: TextView = plantItemView.plant_name

        init {
            plantItemView.setOnClickListener { plantItemClickListener.onClick() }
        }
    }

    interface OnPlantItemClickedListener {
        fun onClick()
    }
}
