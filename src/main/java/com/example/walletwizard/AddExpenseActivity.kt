package com.example.walletwizard

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.walletwizard.databinding.AddExpenseBinding
import com.google.firebase.database.FirebaseDatabase


class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: AddExpenseBinding
    private var dataBase = FirebaseDatabase.getInstance().getReference("Walletwizard")

    //private lateinit var labelInput: EditText
    //private lateinit var descriptionInput: EditText
    //private lateinit var amountInput: EditText
    //private lateinit var submitButton: Button
    //private lateinit var labelLayout: TextInputLayout
    //private lateinit var amountLayout: TextInputLayout
    private lateinit var closeBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout from activity_incomes_create.xml and set as view
        val binding = AddExpenseBinding.inflate(layoutInflater)

        //binding = AddExpenseBinding().inflate(layoutInflater)
        setContentView(binding.root)

        //labelInput = findViewById(R.id.labelInput)
        //descriptionInput = findViewById(R.id.descriptionInput)
        //amountInput = findViewById(R.id.amountInput)
        //submitButton = findViewById(R.id.submitButton)
        //closeBtn = findViewById(R.id.closeBtn)


        //labelInput.addTextChangedListener {
        //    if(it!!.count() > 0)
        //        labelLayout.error = null
        //}

        //amountInput.addTextChangedListener {
         //   if(it!!.count() > 0)
         //       amountLayout.error = null
        //}

        binding.submitButton.setOnClickListener {
            saveData()
        }
        closeBtn.setOnClickListener {
            finish()
        }


    }

    private fun saveData(){

        //Validations for EditText s
        val amount = binding.amountInput.text.toString().trim()
        val category = binding.labelInput.text.toString().trim()
        val description = binding.descriptionInput.text.toString().trim()

        if (amount.isEmpty()) {
            binding.amountInput.error = "Amount is required"
            binding.amountInput.requestFocus()
            return
        }

        if (category.isEmpty()) {
            binding.labelInput.error = "Category is required"
            binding.labelInput.requestFocus()
            return
        }

        if (description.isEmpty()) {
            binding.descriptionInput.error = "Description is required"
            binding.descriptionInput.requestFocus()
            return
        }

    }

    //function to upload data
    private fun uploadData(){
        val amount = binding.amountInput.text.toString()
        val category = binding.labelInput.text.toString()
        val description = binding.descriptionInput.text.toString()
        val id = dataBase.push().key!!


        val expenseData = ExpenseData(id, amount, category, description)


        dataBase.child(id)
            .setValue(expenseData).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this@AddExpenseActivity, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.addOnFailureListener{ e ->
                Toast.makeText(this@AddExpenseActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

    }

}
