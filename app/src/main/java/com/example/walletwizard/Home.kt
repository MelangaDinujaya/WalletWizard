package com.example.walletwizard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var incomebtn = findViewById<Button>(R.id.incomebtn)

        incomebtn.setOnClickListener {
            val intent2 = Intent(this, Income::class.java)
            startActivity(intent2)
        }

        var expensebtn = findViewById<Button>(R.id.expensebtn)

        expensebtn.setOnClickListener {
            val intent1 = Intent(this, expense::class.java)
            startActivity(intent1)
        }

    }
}