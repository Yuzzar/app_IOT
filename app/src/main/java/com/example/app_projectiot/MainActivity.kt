package com.example.app_projectiot

import FHome
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.app_projectiot.MainActivity
import com.example.app_projectiot.databinding.ActivityLoginBinding
import com.example.app_projectiot.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize the binding object
        setContentView(binding.root)
        loadFragment(FHome())

        binding.image.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)

        }
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomnavview)
        bottomnav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bot_menu_Home -> {
                    loadFragment(FHome())
                    true
                }
            }
            when(it.itemId){
                R.id.bot_menu_Graphics -> {
                    loadFragment(FGraphic())
                    true
                }
            }

            when(it.itemId){
                R.id.bot_menu_profile -> {
                    loadFragment(FProfile())
                    true
                }
                else -> {false}
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.f_container,fragment)
        transaction.commit()
        }
}


