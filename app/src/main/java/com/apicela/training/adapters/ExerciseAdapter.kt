package com.apicela.training.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.ExecutionActivity
import com.apicela.training.R
import com.apicela.training.interfaces.OnExerciseCheckedChangeListener
import com.apicela.training.models.Exercise
import com.apicela.training.models.Muscle
import com.apicela.training.services.ExerciseService
import com.apicela.training.ui.utils.ImageHelper
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.runBlocking


class ExerciseAdapter(
    private val context: Context, private var exerciseMap: Map<String, List<Exercise>>,
    private val divisionId : String? = null,
    private val exerciseService: ExerciseService,
    private val checkedItemCountChangedListener: OnExerciseCheckedChangeListener? = null // Adicionando a interface
) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var checkedItems = mutableListOf<Exercise>();
    private var isEditing = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.cardview_exercise_layout, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val key = exerciseMap.keys.elementAt(position)
        holder.muscle_name.text = Muscle.getMuscleByPtbr(key).uppercase()
        val resourceId = context.resources.getIdentifier(
            "muscle_group_${(key).lowercase()}",
            "drawable",
            context.packageName
        )
        holder.muscle_image.setImageResource(resourceId)
        val exercises = exerciseMap.getValue(key)
        holder.layoutExercises.removeAllViews()

        exercises.forEach { exercise ->
            val exerciseItemView =
                LayoutInflater.from(context).inflate(R.layout.exercise_item, null)
            val exerciseName = exerciseItemView.findViewById<TextView>(R.id.exercise_text)
            val exerciseImage = exerciseItemView.findViewById<ImageView>(R.id.exercise_image)
            val checkbox = exerciseItemView.findViewById<CheckBox>(R.id.checkbox)
            val minusImage = exerciseItemView.findViewById<ImageView>(R.id.minus)
            exerciseName.text = "${exercise.exerciseName}"
            ImageHelper.setImage(context, exerciseImage, exercise.image, true)
            checkbox.visibility = if (checkedItemCountChangedListener != null) View.VISIBLE else View.GONE
            minusImage.visibility = if(isEditing) View.VISIBLE else View.GONE

            minusImage.setOnClickListener{
                runBlocking {
                    exerciseService.removeExerciseFromDivision(divisionId!!, exercise.id)
                    exerciseMap = exerciseService.exerciseListToMap(divisionId!!)!!
                    notifyDataSetChanged()
                }
            }
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    checkedItems.add(exercise)
                    checkedItemCountChangedListener!!.onCheckedItemCountChanged(checkedItems.size)

                } else {
                    checkedItems.remove(exercise)
                    checkedItemCountChangedListener!!.onCheckedItemCountChanged(checkedItems.size)

                }
            }
            if (isEditing == false) {
                exerciseItemView.setOnClickListener {
                    val intent = Intent(holder.itemView.context, ExecutionActivity::class.java)
                    intent.putExtra("exercise_id", exercise.id)
                    intent.putExtra("exercise_image", exercise.image)
                    intent.putExtra("exercise_name", exercise.exerciseName)
                    holder.itemView.context.startActivity(intent)
                }
            }
            holder.layoutExercises.addView(exerciseItemView)
        }
    }

    override fun getItemCount(): Int = exerciseMap.size

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val muscle_name: TextView = itemView.findViewById(R.id.muscle_name)
        val muscle_image: ShapeableImageView = itemView.findViewById(R.id.muscle_image)
        val layoutExercises: LinearLayout = itemView.findViewById(R.id.linearLayoutExercise)
    }

    fun updateData(newExecutionMap: Map<String, List<Exercise>>) {
        exerciseMap = newExecutionMap
        notifyDataSetChanged()
    }

    fun getSelectedExercises(): List<Exercise> {
        return checkedItems;
    }

    fun setEditing(isEditing: Boolean) {
        this.isEditing = isEditing
        notifyDataSetChanged()
    }
}