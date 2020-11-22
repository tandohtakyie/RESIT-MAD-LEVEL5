package com.example.resit_mad_level_5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resit_mad_level_5.R
import com.example.resit_mad_level_5.model.Game
import kotlinx.android.synthetic.main.item_game_added.view.*

class GameAdapter (private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    lateinit var context : Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_game_added, parent, false))
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(game: Game){
            itemView.etTitle.text = game.title
            itemView.etPlatform.text = game.platform
            itemView.releaseDay.text = "Release: " + game.day.toString() + " "
            itemView.releaseMonth.text = game.month + " "
            itemView.releaseYear.text = game.year.toString()
        }
    }

}
