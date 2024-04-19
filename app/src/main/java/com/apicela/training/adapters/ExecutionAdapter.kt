package com.apicela.training.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.R
import com.apicela.training.models.Execution
import java.util.Date

class ExecutionAdapter(private val context: Context, private val executionGroups: List<Pair<Date, List<Execution>>>) :
    RecyclerView.Adapter<ExecutionAdapter.ExecutionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExecutionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_execution, parent, false)
        return ExecutionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExecutionViewHolder, position: Int) {
        val executionGroup = executionGroups[position]
        holder.bind(executionGroup)
    }

    override fun getItemCount(): Int {
        return executionGroups.size
    }

    inner class ExecutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        private val repetitionsTextView: TextView = itemView.findViewById(R.id.textViewRepetitions)

        fun bind(executionGroup: Pair<Date, List<Execution>>) {
            val executions = executionGroup.second
            dateTextView.text = executionGroup.first.toString()
            repetitionsTextView.text = executions.joinToString(separator = "\n") { it.repetitions.toString() }
        }
    }
}
