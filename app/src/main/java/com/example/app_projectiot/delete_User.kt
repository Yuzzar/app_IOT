package com.example.app_projectiot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.app_projectiot.databinding.ActivityDeleteUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class delete_User : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteUserBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteButton.setOnClickListener {
            val phone = binding.deletePhone.text.toString()
            val intent = Intent(this@delete_User, MainActivity2::class.java)
            startActivity(intent)
            if (phone.isNotEmpty())
                deleteData(phone)
            else
                Toast.makeText(this, "Please enter the phone number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData(phone: String){
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(phone).removeValue().addOnSuccessListener {
            binding.deletePhone.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}
