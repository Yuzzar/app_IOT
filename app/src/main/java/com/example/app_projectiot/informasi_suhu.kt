package com.example.app_projectiot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import com.example.app_projectiot.config.DataConfigWaterR
import com.example.app_projectiot.databinding.ActivityInformasiSuhuBinding
import com.example.app_projectiot.model.modell_reservour
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call


class informasi_suhu : AppCompatActivity() {

    private lateinit var progressBar3: ProgressBar
    private lateinit var celci: TextView
    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informasi_suhu)

        progressBar3 = findViewById(R.id.progress_suhu_hari_ini)
        celci = findViewById(R.id.suhu_hari_ini)

        celci.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                // Ketika nilai TextView persen berubah, perbarui ProgressBar
                val persenValue = s?.toString()?.toIntOrNull() ?: 0
                progressBar3.progress = persenValue
            }
        })
        handler.postDelayed(object : Runnable {
            override fun run() {
                // Panggil metode getDataWater untuk mendapatkan data dan mengupdate TextView
                getDataWater()
                // Atur handler untuk dijalankan lagi setelah 5 detik
                handler.postDelayed(this, 500)
            }
        }, 1000)
    }
    private fun getDataWater() {
        DataConfigWaterR().getService()
            .getDataWater()
            .enqueue(object : Callback<modell_reservour> {
                override fun onResponse(
                    call: Call<modell_reservour>,
                    response: Response<modell_reservour>
                ) {
                    Log.d("Yuzzar", "data json : " + response.body())

                    val suhuString = response.body()?.suhu
                    try {
                        val suhuFloat = suhuString?.toFloat() ?: 0.0f
                        val suhuInt = suhuFloat.toInt()
                        celci.text = suhuString
                        progressBar3.progress = suhuInt
                    } catch (e: NumberFormatException) {
                        Log.e(
                            "Yuzzar",
                            "Error converting kapasitas to float or integer: ${e.message}"
                        )
                    }
                }
                    override fun onFailure(call: Call<modell_reservour>, t: Throwable) {
                        Log.d("Yuzzar", "Error : " + t.message.toString())

                }
                })

        }
}