package com.example.app_projectiot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.app_projectiot.databinding.ActivityUpdateUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class update_user : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateUserBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.updateButton.setOnClickListener {
            val referencePhone = binding.referencePhone.text.toString()
            val updateName = binding.updateName.text.toString()
            val updateOperator = binding.updateOperator.text.toString()
            val updateLocation = binding.updateLocation.text.toString()
            updateData(referencePhone, updateName, updateOperator, updateLocation)

            val intent = Intent(this@update_user, MainActivity2::class.java)
            startActivity(intent)
        }
    }
    private fun updateData(phone: String, name: String, email: String, alamat: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        val user = User(name, email, alamat, phone)
        database.child(phone).setValue(user).addOnSuccessListener {
            binding.referencePhone.text.clear()
            binding.updateName.text.clear()
            binding.updateOperator.text.clear()
            binding.updateLocation.text.clear()
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
            }
        }
}