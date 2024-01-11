package com.example.app_projectiot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.ListView
import com.example.app_projectiot.adapter.AdapterBuatanSaya
import com.example.app_projectiot.config.configbmkg
import com.example.app_projectiot.databinding.ActivityBmkgBinding
import com.example.app_projectiot.model.modelbmkg

class bmkg : AppCompatActivity() {

    private lateinit var binding: ActivityBmkgBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmkgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)

        }

        val _listview = findViewById<ListView>(R.id.list_gempa)

        configbmkg().getService()
            .getgempa()
            .enqueue(object : Callback<modelbmkg> {
                override fun onResponse(
                    call: Call<modelbmkg>,
                    response: Response<modelbmkg>
                ) {
                    Log.d("yuzzar", "data json: " + response.body())
                    _listview.adapter = AdapterBuatanSaya(
                        response.body(), this@bmkg,
                        response.body()?.infogempa?.gempa!!
                    )
                }

                override fun onFailure(call: Call<modelbmkg>, t: Throwable) {
                    Log.d("yuzzar", "error: " + t.message.toString())
                }
            })
    }
}