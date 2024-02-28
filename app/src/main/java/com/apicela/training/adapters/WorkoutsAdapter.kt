package com.apicela.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.R
import com.apicela.training.models.Exercise

class WorkoutsAdapter : RecyclerView.Adapter<WorkoutsAdapter.MyViewHolder>() {

    private var lista: List<Exercise> = ArrayList()
    fun setList(newList: List<Exercise>) {
        this.lista = newList
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val jogadorOriginal: TextView = itemView.findViewById(R.id.jogadorOriginal)
//        val date: TextView = itemView.findViewById(R.id.dateMatch)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.exercises_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val match = lista.get(position)
//        holder.jogadorOriginal.text = match.playerOne
//        holder.oponente.text = match.opponent
//        holder.date.text = match.date
//
//        when (match.resultado) {
//            0 -> holder.matchResult.setImageResource(R.drawable.draw)
//            1 -> holder.matchResult.setImageResource(R.drawable.trofeu)
//            2 -> holder.matchResult.setImageResource(R.drawable.lose)
//        }
    }
}