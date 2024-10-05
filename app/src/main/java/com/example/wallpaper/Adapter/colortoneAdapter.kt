package com.example.wallpaper.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaper.FinalWallpaper
import com.example.wallpaper.Model.colortoneModel
import com.example.wallpaper.R

class colortoneAdapter(val requireContext: Context, val listTheColorTone: ArrayList<colortoneModel>) :RecyclerView.Adapter<colortoneAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardBack=itemView.findViewById<CardView>(R.id.item_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_colortone,parent,false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {

        val color = listTheColorTone[position].color
        holder.cardBack.setCardBackgroundColor(Color.parseColor(color!!))



        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalWallpaper::class.java)
            intent.putExtra("link",listTheColorTone[position].link)
            requireContext.startActivity(intent)
        }


    }

    override fun getItemCount() =listTheColorTone.size

}