package com.example.app_projectiot.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.app_projectiot.R
import com.example.app_projectiot.bmkg
import com.example.app_projectiot.model.GempaItem
import com.example.app_projectiot.model.modelbmkg

class AdapterBuatanSaya(val data: modelbmkg?, val context: bmkg, val _g: List<GempaItem?>)
    : ArrayAdapter<GempaItem>(context, R.layout.custom_listview, _g) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_listview, null, true)

        //indexing
        var _idx = rowView.findViewById<TextView>(R.id.lst_nomor)
        //tanggal
        var _tgl = rowView.findViewById<TextView>(R.id.lst_tanggal)
        //koordinat
        var _koordinat = rowView.findViewById<TextView>(R.id.lst_koordinat)
        //wilayah
        var _wilayah = rowView.findViewById<TextView>(R.id.lst_wilayah)
        //potensi
        var _potensi = rowView.findViewById<TextView>(R.id.lst_potensi)

        //pengisian data
        _idx.setText((position + 1).toString())
        Log.d("yuzzar", "posisi: " + (position + 1))

        _tgl.setText(data?.infogempa?.gempa?.get(position)?.tanggal)
        Log.d("yuzzar", "Tanggal: " + data?.infogempa?.gempa?.get(position)?.tanggal)

        _koordinat.setText(data?.infogempa?.gempa?.get(position)?.coordinates)
        Log.d("yuzzar", "Koordinat: " + data?.infogempa?.gempa?.get(position)?.coordinates)

        _wilayah.setText(data?.infogempa?.gempa?.get(position)?.wilayah)
        Log.d("yuzzar", "Wilayah: " + data?.infogempa?.gempa?.get(position)?.wilayah)

        _potensi.setText(data?.infogempa?.gempa?.get(position)?.potensi)
        Log.d("yuzzar", "Wilayah: " + data?.infogempa?.gempa?.get(position)?.potensi)

        return rowView
    }
}