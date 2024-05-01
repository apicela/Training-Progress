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
import com.apicela.training.models.Execution


class ExecutionAdapter(private val context: Context, private var executionMap: Map<String, List<Execution>>) :
    RecyclerView.Adapter<ExecutionAdapter.ExecutionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExecutionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.date_linear_layout, parent, false)
        return ExecutionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExecutionViewHolder, position: Int) {
        val key = executionMap.keys.elementAt(position)
        holder.date.text = key

        val executions = executionMap.getValue(key)
        holder.linearLayoutExecutions.removeAllViews()

        executions.forEach { execution ->
            val executionView = LayoutInflater.from(context).inflate(R.layout.item_execution, null)
            val textViewRepetitions = executionView.findViewById<TextView>(R.id.textViewRepetitions)
            val textViewWeight = executionView.findViewById<TextView>(R.id.textViewWeight)
            val imageViewMinus = executionView.findViewById<ImageView>(R.id.imageViewMinus)
            textViewRepetitions.text = "${execution.repetitions}"
            textViewWeight.text = "${execution.kg}"
//            imageViewMinus.setImageResource(R.drawable.minus)

            holder.linearLayoutExecutions.addView(executionView)
        }
    }

    override fun getItemCount(): Int = executionMap.size

    class ExecutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val linearLayoutExecutions: LinearLayout = itemView.findViewById(R.id.linearLayoutExecutions)
    }

    fun updateData(newExecutionMap: Map<String, List<Execution>>) {
        executionMap = newExecutionMap
        notifyDataSetChanged()
    }
}