package com.example.registrationloginauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class welcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Get a reference to the "Get Started" button
        val getStartedButton = findViewById<Button>(R.id.button2)

        // Set a click listener on the button
        getStartedButton.setOnClickListener {
            // Create an intent to launch the sign-up activity
            val intent = Intent(this, signupActivity::class.java)
            startActivity(intent)
        }
    }
}
