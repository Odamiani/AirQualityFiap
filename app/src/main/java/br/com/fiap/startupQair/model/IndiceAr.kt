package br.com.fiap.startupQair.model

data class ApiResponse(
    val status: String = "",
    val data: List<IndiceAr> = emptyList()
)

data class IndiceAr(
    val uid: Int = 0,
    val aqi: String = "",
    val time: Time = Time(),
    val station: Station = Station()
)

data class Time(
    val tz: String = "",
    val stime: String = "",
    val vtime: Long = 0
)

data class Station(
    val name: String = "",
    val geo: List<Double> = emptyList(),
    val url: String = "",
)

data class ApiResponseByLocal(
    val status: String = "",
    val data: IndiceArByLocal = IndiceArByLocal()
)

data class IndiceArByLocal(
    val idx: Int = 0,
    val aqi: String = "",
    val city: Station = Station()
)

