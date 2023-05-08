package com.example.registrationloginauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.registrationloginauth.databinding.ActivityMainBinding

import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            binding.emailTextView.setText(it.email)
        }

        binding.updateButton.setOnClickListener{
            val email = binding.emailTextView.text.toString().trim()

            if (email.isNotEmpty()) {
                // Update user email
                user?.updateEmail(email)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Update successful, update email in the database
                        databaseReference = FirebaseDatabase.getInstance().getReference("registrationLogin Auth")
                        databaseReference.child(user.uid).child("email").setValue(email)
                        Toast.makeText(this, "Email updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        // Update failed, display error message
                        Toast.makeText(this, "Failed to update email", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deleteButton.setOnClickListener{
            val email = binding.emailTextView.text.toString().trim()

            if (email.isNotEmpty()) {
                // Update user email
                user?.delete()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Update successful, update email in the database
                        databaseReference = FirebaseDatabase.getInstance().getReference("registrationLogin Auth")
                        databaseReference.child(user.uid).removeValue()
                        Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        // Update failed, display error message
                        Toast.makeText(this, "Failed to delete email", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
