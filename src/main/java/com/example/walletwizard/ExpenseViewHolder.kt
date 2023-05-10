package com.example.walletwizard

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ExpenseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var recCategory: TextView = itemView.findViewById(R.id.label)
    var recAmount: TextView = itemView.findViewById(R.id.amount)
    var record: CardView = itemView.findViewById(R.id.record)

}
