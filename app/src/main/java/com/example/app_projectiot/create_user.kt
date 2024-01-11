package com.example.app_projectiot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.provider.ContactsContract.Profile
import android.widget.Toast
import com.example.app_projectiot.databinding.ActivityCreateUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase





class create_user : AppCompatActivity() {

    private lateinit var binding: ActivityCreateUserBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener {
            saveData()
        }
        database = FirebaseDatabase.getInstance().getReference("User")
    }


    private fun saveData() {
        val name = binding.uploadName.text.toString()
        val email = binding.uploadOperator.text.toString()
        val alamat = binding.uploadLocation.text.toString()
        val phone = binding.uploadPhone.text.toString()

        // Gunakan nomor telepon sebagai child node
        val userReference = database.child(phone)

        val user = User(name, email, alamat, phone)

        userReference.setValue(user).addOnSuccessListener {
            binding.uploadName.text.clear()
            binding.uploadOperator.text.clear()
            binding.uploadLocation.text.clear()
            binding.uploadPhone.text.clear()
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@create_user, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
}