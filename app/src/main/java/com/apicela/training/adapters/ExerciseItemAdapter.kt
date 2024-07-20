package com.apicela.training.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.ExecutionActivity
import com.apicela.training.ItemTouchHelperAdapter
import com.apicela.training.R
import com.apicela.training.interfaces.ExerciseAdapterInterface
import com.apicela.training.interfaces.OnExerciseCheckedChangeListener
import com.apicela.training.models.Exercise
import com.apicela.training.services.ExerciseService
import com.apicela.training.ui.utils.ImageHelper
import kotlinx.coroutines.runBlocking
import java.util.Collections

class ExerciseItemAdapter(
    private val context: Context,
    private val divisionId: String? = null,
    private val exercises : List<Exercise>? = null,
    private val checkedItems : MutableList<Exercise>? = null,
    private val checkedItemCountChangedListener: OnExerciseCheckedChangeListener? = null // Adicionando a interface
) :
    RecyclerView.Adapter<ExerciseItemAdapter.ExerciseItemViewHolder>(), ExerciseAdapterInterface,
    ItemTouchHelperAdapter {

    private var isEditing = false
    private val exerciseService = ExerciseService()
    private var exerciseList : List<Exercise> = getExerciseList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseItemViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseItemViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.exerciseName.text = exercise.name
        ImageHelper.setImage(context, holder.exerciseImage, exercise.image, true)
        setVisibility(holder)
        setOnClick(holder, exercise)
        setOnChecked(holder, exercise)
    }

    class ExerciseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName = itemView.findViewById<TextView>(R.id.exercise_text)
        val exerciseImage = itemView.findViewById<ImageView>(R.id.exercise_image)
        val checkbox = itemView.findViewById<CheckBox>(R.id.checkbox)
        val minusImage = itemView.findViewById<ImageView>(R.id.minus)
    }

    private fun setVisibility(holder: ExerciseItemAdapter.ExerciseItemViewHolder) {
        holder.checkbox.visibility =
            if (checkedItemCountChangedListener != null) View.VISIBLE else View.GONE
        holder.minusImage.visibility = if (isEditing) View.VISIBLE else View.GONE
    }

    private fun setOnChecked(holder: ExerciseItemAdapter.ExerciseItemViewHolder, exercise : Exercise) {
        holder.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkedItems?.add(exercise)
                checkedItemCountChangedListener!!.onCheckedItemCountChanged(checkedItems!!.size)
            } else {
                checkedItems?.remove(exercise)
                checkedItemCountChangedListener!!.onCheckedItemCountChanged(checkedItems!!.size)

            }
        }
    }
    private fun setOnClick(holder: ExerciseItemAdapter.ExerciseItemViewHolder, exercise : Exercise) {

        holder.minusImage.setOnClickListener {
            runBlocking {
                if (divisionId !== null) {
                    exerciseService.removeExerciseFromDivision(divisionId, exercise.id)
                    exerciseList = exerciseService.getExerciseListFromDivision(divisionId)
                } else {
                    exerciseService.deleteExerciseById(exercise.id)
                }
                refreshData()
            }
        }

        if (!isEditing && checkedItemCountChangedListener == null) {
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, ExecutionActivity::class.java)
                intent.putExtra("exercise_id", exercise.id)
                intent.putExtra("exercise_image", exercise.image)
                intent.putExtra("exercise_name", exercise.name)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }




    override fun refreshData() {
        runBlocking {
            exerciseList =  getExerciseList()
            notifyDataSetChanged()
        }
    }

    private fun getExerciseList() : List<Exercise>{
        return exercises ?: exerciseService.getExerciseListFromDivision(divisionId)
    }


    override fun setEditing(isEditing: Boolean) {
        this.isEditing = isEditing
        notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(exerciseList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

}
