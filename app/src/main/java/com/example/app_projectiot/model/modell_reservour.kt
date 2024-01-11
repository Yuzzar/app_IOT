package com.example.app_projectiot.model

import com.google.gson.annotations.SerializedName

data class modell_reservour(

    @field:SerializedName("ione/waterlevel")
    val waterLevel: String? = null,

    @field:SerializedName("ione/debit")
    val debit: String? = null,

    @field:SerializedName("ione/penggunaanLiter")
    val penggunaanLiter: String? = null,

    @field:SerializedName("ione/jarak")
    val jarak: String? = null,

    @field:SerializedName("ione/kapasitas")
    val kapasitas: String? = null,

    @field:SerializedName("ione/suhu")
    val suhu: String? = null,

    @field:SerializedName("ione/statuspompa")
    val statusPompa: Int? = null
)
data class Waterone(

    @field:SerializedName("modell_reservour")
    val modell_reservour:modell_reservour?=null
)