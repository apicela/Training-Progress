package com.apicela.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.R
import com.apicela.training.models.Execution


class ExecutionAdapter(private var executionMap: Map<String, List<Execution>>) :
    RecyclerView.Adapter<ExecutionAdapter.ExecutionViewHolder>() {

    inner class ExecutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        val repetitionsTextView: TextView = itemView.findViewById(R.id.textViewRepetitions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExecutionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_execution, parent, false)
        return ExecutionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExecutionViewHolder, position: Int) {
        val date = executionMap.keys.toList()[position]
        val executions = executionMap[date]

        holder.dateTextView.text = date

        val executionsText = StringBuilder()
        executions?.forEach { execution ->
            executionsText.append("${execution.repetitions} reps - ${execution.kg} kg\n")
        }

        holder.repetitionsTextView.text = executionsText.toString()
    }

    fun updateData(newExecutionMap: Map<String, List<Execution>>) {
        executionMap = newExecutionMap
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return executionMap.size
    }
}