package com.example.app_projectiot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_projectiot.databinding.ActivityLoginBinding
import com.example.app_projectiot.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater) // Initialize the binding object
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.databmkg.setOnClickListener {
            val intent = Intent(this, bmkg::class.java)
            startActivity(intent)

        }

        binding.CRUD.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }
    }
}