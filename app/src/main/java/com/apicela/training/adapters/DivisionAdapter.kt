package com.apicela.training.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.ExerciseActivity
import com.apicela.training.HomeActivity
import com.apicela.training.R
import com.apicela.training.dialog.EditDivisionDialog
import com.apicela.training.models.Division
import com.apicela.training.services.WorkoutService
import com.apicela.training.ui.utils.ImageHelper
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DivisionAdapter(
    private val context: Context,
    private val workoutId: String
) : RecyclerView.Adapter<DivisionAdapter.MyViewHolder>() {
    val workoutService = WorkoutService(HomeActivity.DATABASE)
    private var isEditing = false
    val workout = runBlocking { workoutService.getWorkoutById(workoutId) }
    var list = workout.listOfDivision as MutableList<Division>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_division, parent, false)
        return MyViewHolder(itemView)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.text)
        var image = itemView.findViewById<ShapeableImageView>(R.id.image)
        val minusButton = itemView.findViewById<ImageView>(R.id.minus)
        val editButton = itemView.findViewById<ImageView>(R.id.imageViewEdit)
    }

    fun refreshData() {
        notifyDataSetChanged()
    }

    fun setEditing(isEditing: Boolean) {
        this.isEditing = isEditing
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val division = list.get(position)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ExerciseActivity::class.java)
            intent.putExtra("division_id", division.id)
            holder.itemView.context.startActivity(intent)
        }
        holder.name.text = division.name
        holder.minusButton.visibility = if (isEditing) View.VISIBLE else View.GONE
        holder.editButton.visibility = if (isEditing) View.VISIBLE else View.GONE

        holder.minusButton.setOnClickListener {
            list.remove(division)
            refreshData()
            CoroutineScope(Dispatchers.Main).launch {
                workoutService.divisionService.deleteDivisionById(division.id)
                workoutService.updateWorkout(workout)
            }
        }

        holder.editButton.setOnClickListener {
            val dialog = EditDivisionDialog(division.name, division.image)
            if (context is FragmentActivity) {
                dialog.show(context.supportFragmentManager, "EditarDivisão")
            }
            dialog.onDismissListener = { // Configura o listener para saber da dismiss do diálogo
                val confirmDelete =
                    dialog.confirmed // Verifica se o diálogo foi cancelado (clique em "Cancelar")
                if (confirmDelete) {
                    division.name = dialog.divisionName
                    division.image = dialog.image
                    refreshData()
                    CoroutineScope(Dispatchers.IO).launch {
                        workoutService.divisionService.updateDivisionObject(division)
                        workoutService.updateWorkout(workout)
                    }
                }
            }
        }
        ImageHelper.setImage(context, holder.image, "number_${(position+1)}", false)
    }
}