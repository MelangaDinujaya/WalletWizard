package com.example.walletwizard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ExpenseAdapter( private val context: android.content.Context, private val dataList: ArrayList<ExpenseData>) : RecyclerView.Adapter<ExpenseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.expense_layout, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense= dataList[position]
        //Glide.with(context).load(dataList[position].dataImage).into(holder.recImage)
        holder.recCategory.text = dataList[position].label
        holder.recAmount.text = dataList[position].amount

        holder.record.setOnClickListener {

            val intent = Intent(holder.itemView.context,AddExpenseActivity::class.java)
            intent.putExtra("id",currentExpense.id)
            intent.putExtra("expenseAmount", currentExpense.amount)
            intent.putExtra("expenseCategory", currentExpense.label)
            intent.putExtra("expenseDescription", currentExpense.description)



            // Start the ExpenseDetails activity
            holder.itemView.context.startActivity(intent)
        }
    }

}
