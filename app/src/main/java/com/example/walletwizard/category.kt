package com.example.walletwizard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private lateinit var binding: ActivityCategoryAddBinding

private lateinit var firebaseAuth:FirebaseAuth

private lateinit var progressDialog : progressDialog


class category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryAddBinding.inflate(layotInflater)
        setContentView(binding.root)

        firebaseAuth = firebaseAuth.getinstace()


        progressDialog= progressDialog(this)
        progressDialog.setTitle("please wait...")
        progressDialog.setCanceledOnTouchOutside(false)


        binding.backbtn.setOnclickListner{
            OnBackPressed()
        }
    }
}