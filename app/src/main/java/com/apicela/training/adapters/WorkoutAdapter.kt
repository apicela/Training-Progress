package com.apicela.training.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.DivisionActivity
import com.apicela.training.R
import com.apicela.training.dialog.DeleteItemDialog
import com.apicela.training.models.Workout
import com.apicela.training.services.WorkoutService
import com.apicela.training.ui.utils.ImageHelper
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.runBlocking

class WorkoutAdapter(
    private val context: Context, private var listWorkouts: List<Workout>, private val
    workoutService: WorkoutService
) : RecyclerView.Adapter<WorkoutAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return MyViewHolder(itemView)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workout_name = itemView.findViewById<TextView>(R.id.workout_name)
        var workout_image = itemView.findViewById<CircleImageView>(R.id.workout_image)
    }

    fun refreshData() {
        listWorkouts = runBlocking { workoutService.getAllWorkouts() }
        Log.d("adapter", "called updateData")
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listWorkouts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val workout = listWorkouts.get(position)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DivisionActivity::class.java)
            intent.putExtra("workout_id", (workout.id))
            holder.itemView.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            val dialog = DeleteItemDialog(workout.name)
            if (context is FragmentActivity) {
                dialog.show(context.supportFragmentManager, "RegistrarExercicioDialog")
            }
            dialog.onDismissListener = { // Configura o listener para saber da dismiss do diálogo
                val confirmDelete =
                    dialog.confirmed // Verifica se o diálogo foi cancelado (clique em "Cancelar")
                if (confirmDelete) {
                    runBlocking { workoutService.deleteById(workout.id) }
                    refreshData()
                }
            }
            true
        }
        holder.workout_name.text = workout.name
        ImageHelper.setImage(context, holder.workout_image, workout.image, false)
    }
}