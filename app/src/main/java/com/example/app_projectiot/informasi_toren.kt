package com.example.app_projectiot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.app_projectiot.config.DataConfigWaterR
import com.example.app_projectiot.databinding.ActivityInformasiTorenBinding
import com.example.app_projectiot.databinding.ActivityLoginBinding
import com.example.app_projectiot.model.modell_reservour
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call


class informasi_toren : AppCompatActivity() {
    private lateinit var persenTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var progressBar2: ProgressBar
    private lateinit var debit: TextView
    private lateinit var waterlevel: TextView
    private lateinit var binding: ActivityInformasiTorenBinding

    private var handler = Handler()
    private val updateInterval = 1000L // Interval update dalam milidetik
    private val getDataRunnable = object : Runnable {
        override fun run() {
            getDataWater()
            handler.postDelayed(this, updateInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiTorenBinding.inflate(layoutInflater) // Initialize the binding object
        setContentView(binding.root)

        persenTextView = findViewById(R.id.presentase_air)
        progressBar = findViewById(R.id.progress_presentase_air)
        progressBar2 = findViewById(R.id.progress_pemakaian_sehari)
        debit = findViewById(R.id.debit_air)

        binding.backHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        persenTextView.addTextChangedListener(createTextWatcher(progressBar))
        debit.addTextChangedListener(createTextWatcher(progressBar2))
    }

    private fun createTextWatcher(progressBar: ProgressBar): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val value = s?.toString()?.toIntOrNull() ?: 0
                progressBar.progress = value
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(getDataRunnable, updateInterval)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(getDataRunnable)
    }

    private fun getDataWater() {
        DataConfigWaterR().getService().getDataWater().enqueue(object : Callback<modell_reservour> {
            override fun onResponse(call: Call<modell_reservour>, response: Response<modell_reservour>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        updateUI(it.kapasitas, it.debit)
                    } ?: Log.e("Yuzzar", "Response body is null")
                } else {
                    Log.e("Yuzzar", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<modell_reservour>, t: Throwable) {
                Log.e("Yuzzar", "Error: ${t.message}")
            }
        })
    }

    private fun updateUI(kapasitas: String?, debit: String?) {
        try {
            val kapasitasFloat = kapasitas?.toFloatOrNull() ?: 0.0f
            val debitFloat = debit?.toFloatOrNull() ?: 0.0f

            persenTextView.text = kapasitas
            progressBar.progress = kapasitasFloat.toInt()

            this.debit.text = debit
            progressBar2.progress = debitFloat.toInt()
        } catch (e: NumberFormatException) {
            Log.e("Yuzzar", "Error converting data to float or integer: ${e.message}")
        }
    }
}


