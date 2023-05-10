package com.example.walletwizard

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.walletwizard.databinding.EditExpenseBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ExpenseEdit : AppCompatActivity() {

    private lateinit var binding: EditExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout from activity_incomes_edit.xml and set as view
        binding = EditExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Get data from previous activity and set to corresponding views
        val bundle = intent.extras
        if (bundle != null){
            binding.editamount.text = Editable.Factory.getInstance().newEditable(bundle.getString("ExpenseAmount"))
            binding.editlabel.text = Editable.Factory.getInstance().newEditable(bundle.getString("ExpenseCategory"))
            binding.editdescription.text = Editable.Factory.getInstance().newEditable(bundle.getString("ExpenseDescription"))
            binding.id.text = Editable.Factory.getInstance().newEditable(bundle.getString("id"))
        }

        //set click listener for the delete button
        binding.btnDelete.setOnClickListener {

            val id = binding.id.text.toString()


            if (id.isNotEmpty()) {
                deleteData(id)
            } else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

        //set click listener for the update button
        binding.btnUpdate.setOnClickListener {
            val updateId = binding.editlabel.toString()
            val updateAmount = binding.editamount.text.toString()
            val updateCategory = binding.editlabel.text.toString()
            val updateDescription = binding.editdescription.text.toString()

            if (updateAmount.isNotEmpty() && updateCategory.isNotEmpty() && updateDescription.isNotEmpty()) {
                updateData(updateId, updateAmount, updateCategory, updateDescription)
            } else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // Function to delete plan data from Firebase database
    private fun deleteData(id: String){
        Log.d("id id",id)
        val db : DatabaseReference = FirebaseDatabase.getInstance().getReference("Walletwizard")
        val delete = db.child(id).removeValue()
        Log.d("deleted",delete.toString())
        delete.addOnSuccessListener {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@ExpenseEdit, MainExpenseActivity::class.java)
            startActivity(intent)
            finish()
        }
        delete.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to update plan data in Firebase database
    private fun updateData(id: String, updateAmount: String, updateCategory: String, updateDescription: String) {
        val db = FirebaseDatabase.getInstance().getReference("Walletwizard")

        // Create map of updated plan data
        val expense = mapOf(
            "id" to id,
            "amount" to updateAmount,
            "category" to updateCategory,
            "description" to updateDescription

        )

        // Update amount
        binding.editamount.text = Editable.Factory.getInstance().newEditable(updateAmount)

        // Update category
        binding.editlabel.text = Editable.Factory.getInstance().newEditable(updateCategory)

        // Update description
        binding.editdescription.text = Editable.Factory.getInstance().newEditable(updateDescription)

        db.child(id).updateChildren(expense).addOnSuccessListener {


            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@ExpenseEdit, MainExpenseActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }

}
