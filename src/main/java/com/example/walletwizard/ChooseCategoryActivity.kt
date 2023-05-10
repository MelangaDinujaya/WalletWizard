package com.example.walletwizard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class ChooseCategoryActivity : AppCompatActivity() {

    private var selectedCategory = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_category)

        // Find the GridView in the layout
        val categoryGridView = findViewById<GridView>(R.id.category_gridview)

        // Create an array of category images
        val categoryImages = intArrayOf(
            R.drawable.food,
            R.drawable.transportation,
            R.drawable.clothes,
            R.drawable.entertainment,
            R.drawable.bills,
            R.drawable.debt,
            R.drawable.education,
            R.drawable.health,
            R.drawable.insurance,
            R.drawable.tax,
            R.drawable.add2,
        )
        // Create an adapter to populate the GridView with category images
        val adapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return categoryImages.size
            }

            override fun getItem(position: Int): Any {
                return categoryImages[position]
            }

            override fun getItemId(position: Int): Long {
                return 0
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val imageView = ImageView(this@ChooseCategoryActivity)
                imageView.setImageResource(categoryImages[position])
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP

                //imageView.layoutParams = GridView.LayoutParams(350, 350)
                imageView.layoutParams = AbsListView.LayoutParams(350, 350)
                return imageView
            }
        }
        // Set the adapter for the GridView
        categoryGridView.adapter = adapter

        // Add an OnItemClickListener to the GridView to handle when a category image is clicked
        categoryGridView.setOnItemClickListener { parent, view, position, id ->
            selectedCategory = getCategoryName(position)
        }

        // Find the submit button in the layout
        val submitButton = findViewById<Button>(R.id.submit_button)

        // Add a click listener to the submit button to handle when the user clicks it
        submitButton.setOnClickListener {
            // Get a reference to the Firebase database
            val database = FirebaseDatabase.getInstance().reference

            // Store the selected category in the database
            database.child("categories").push().setValue(selectedCategory)

            // Navigate to the AddExpenseActivity
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

    }
    // Function to get the name of a category based on its position in the GridView
    private fun getCategoryName(position: Int): String {
        return when (position) {
            0 -> "Food"
            1 -> "Transportation"
            2 -> "Shopping"
            3 -> "Entertainment"
            4 -> "Bills"
            5 -> "Debt/Loan"
            6 -> "Education"
            7 -> "Health"
            8 -> "Insurance"
            9 -> "Tax"
            10 -> "Other"
            else -> ""
        }
    }

}
