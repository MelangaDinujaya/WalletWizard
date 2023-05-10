package com.example.walletwizard


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.walletwizard.databinding.MyExpenseMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainExpenseActivity : AppCompatActivity() {
    private lateinit var binding: MyExpenseMainBinding
    private lateinit var datalist: ArrayList<ExpenseData>
    private lateinit var adapter: ExpenseAdapter
    private var databaseReference: DatabaseReference? = null
    private var eventListener: ValueEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //3
        binding = MyExpenseMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this@MainExpenseActivity, 1)
        binding.recyclerview.layoutManager = gridLayoutManager

        val builder = AlertDialog.Builder(this@MainExpenseActivity)
        builder.setCancelable(false)

        val dialog = builder.create()
        dialog.show()

        // Initialize the data list and adapter for the RecyclerView
        datalist = ArrayList()
        adapter = ExpenseAdapter(this@MainExpenseActivity, datalist)
        binding.recyclerview.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("Walletwizard")
        dialog.show()

        // Set up the ValueEventListener to listen for changes in the database
        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear the data list
                datalist.clear()
                for (itemSnapShot in snapshot.children) {
                    val expenseClass = itemSnapShot.getValue(ExpenseData::class.java)
                    if (expenseClass != null) {
                        datalist.add(expenseClass)
                    }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }
        })


        // Find the floating action button in the layout
        val addNewExpense = findViewById<FloatingActionButton>(R.id.FAB)
        // Set a click listener on the button to open the ChooseCategoryActivity page
        addNewExpense.setOnClickListener {
            val intent = Intent(this@MainExpenseActivity, ChooseCategoryActivity::class.java)
            startActivity(intent)
        }
    }

}