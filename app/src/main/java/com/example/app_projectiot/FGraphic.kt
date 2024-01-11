package com.example.app_projectiot

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.db.williamchart.view.BarChartView
import com.example.app_projectiot.config.config_bar
import com.example.app_projectiot.databinding.FragmentFGraphicBinding
import com.example.app_projectiot.model.Model_average
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FGraphic.newInstance] factory method to
 * create an instance of this fragment.
 */
class FGraphic : Fragment() {
    private var _binding: FragmentFGraphicBinding? = null
    private val binding get() = _binding!!

    private val lineSet = mutableListOf<Pair<String, Float>>()
    private val barSet = mutableListOf<Pair<String, Float>>()

    private lateinit var barChartView: BarChartView

    private var param1: String? = null
    private var param2: String? = null

    private val animationDuration = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFGraphicBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharts()
        fetchDataFromApi()
    }

    private fun setupCharts() {
        binding.apply {
            barChart.animation.duration = animationDuration
            barChart.animate(barSet)
            lineChart.gradientFillColors = intArrayOf(
                Color.parseColor("#64B5F6"),
                Color.TRANSPARENT
            )
            lineChart.animation.duration = animationDuration
            lineChart.animate(lineSet)
            lineChart.onDataPointTouchListener = { index, _, _ ->
                tvChartData.text = lineSet.toList()[index].second.toString()
            }
        }
    }

    private fun fetchDataFromApi() {
        // Assuming you have a Retrofit service for fetching earthquake data
        // Make sure to replace ApiService and getDataGempa with your actual Retrofit service
        config_bar().getService()
            .getDataWaterG()
            .enqueue(object : Callback<Model_average> {
                override fun onResponse(
                    call: Call<Model_average>,
                    response: Response<Model_average>
                ) {
                    if (response.isSuccessful) {
                        parseData(response.body())
                        updateCharts()
                    }
                }

                override fun onFailure(call: Call<Model_average>, t: Throwable) {

                }

            })
    }

    private fun parseData(modelAverage: Model_average?) {
        modelAverage?.let {
            it.data?.let { data ->
                val senin = data.senin ?: 0
                val selasa = data.selasa ?: 0
                val rabu = data.rabu ?: 0
                val kamis = data.kamis ?: 0
                val jumat = data.jumat ?: 0
                val sabtu = data.sabtu ?: 0
                val minggu = data.minggu ?: 0

                lineSet.apply {
                    clear()
                    add("Senin" to senin.toFloat())
                    add("Selasa" to selasa.toFloat())
                    add("Rabu" to rabu.toFloat())
                    add("Kamis" to kamis.toFloat())
                    add("Jumat" to jumat.toFloat())
                    add("Sabtu" to sabtu.toFloat())
                    add("Minggu" to minggu.toFloat())
                }

                barSet.apply {
                    clear()
                    add("Senin" to senin.toFloat())
                    add("Selasa" to selasa.toFloat())
                    add("Rabu" to rabu.toFloat())
                    add("Kamis" to kamis.toFloat())
                    add("Jumat" to jumat.toFloat())
                    add("Sabtu" to sabtu.toFloat())
                    add("Minggu" to minggu.toFloat())
                }
            }
        }
    }

    private fun updateCharts() {
        binding.apply {
            // Add new data
            lineChart.animate(lineSet)
            barChart.animate(barSet)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val animationDuration = 1000L

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FGraphic.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FGraphic().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}