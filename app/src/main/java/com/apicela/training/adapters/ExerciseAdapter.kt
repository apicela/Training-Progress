package com.apicela.training.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.R
import com.apicela.training.models.Exercise
import com.apicela.training.models.Muscle
import com.apicela.training.services.ExerciseService
import com.apicela.training.ui.utils.ImageHelper
import com.google.android.material.imageview.ShapeableImageView


class ExerciseAdapter(private val context: Context, private var exerciseMap: Map<String, List<Exercise>>,
                      private val exerciseService: ExerciseService) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var isEditing = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_exercise_layout, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val key = exerciseMap.keys.elementAt(position)
        holder.muscle_name.text = Muscle.getMuscleByPtbr(key)
        val resourceId = context.resources.getIdentifier("muscle_group_${(key).lowercase()}", "drawable", context.packageName)
        holder.muscle_image.setImageResource(resourceId)
        val exercises = exerciseMap.getValue(key)
        holder.layoutExercises.removeAllViews()

        exercises.forEach { exercise ->
            val exerciseItemView = LayoutInflater.from(context).inflate(R.layout.exercise_item, null)
            val exerciseName = exerciseItemView.findViewById<TextView>(R.id.exercise_text)
            val exerciseImage = exerciseItemView.findViewById<ImageView>(R.id.exercise_image)
            exerciseName.text = "${exercise.exerciseName}"
            ImageHelper.setImage(context, exerciseImage, exercise.image, false)
//            imageViewMinus.visibility = if (isEditing) View.VISIBLE else View.GONE
//            imageViewEdit.visibility = if (isEditing) View.VISIBLE else View.GONE
//
//            imageViewEdit.setOnClickListener{
//                val dialog = RegisterExecutionDialog(execution.exercise_id, execution.id, context)
//                if (context is FragmentActivity) {
//                    dialog.show(context.supportFragmentManager, "RegistrarExercicioDialog")
//                    dialog.onDismissListener = {
////                        updateData(executionService.executionListToMap(execution.exercise_id))
//                    }
//                }
//            }
//            imageViewMinus.setOnClickListener{
//                runBlocking {
////                    executionService.deleteById(execution.id)
////                    executionMap = executionService.executionListToMap(execution.exercise_id)
////                    notifyDataSetChanged()
//                }
//            }
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

    fun setEditing(isEditing: Boolean) {
        this.isEditing = isEditing
        notifyDataSetChanged()
    }
}