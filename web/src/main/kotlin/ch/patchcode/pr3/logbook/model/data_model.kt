package ch.patchcode.pr3.logbook.model

data class CityModel(val id: Long, val name: String)
data class FacilityModel(val id: Long, val name: String)

data class ShiptypeModel(
		val id: Long,
		val name: String,
		val cargoSpace: Int?,
    val maneuverability: Int?,
    val draft: Int?,
    val minSpeed: Int?,
    val maxSpeed: Int?,
    val cannons: Int?,
    val sailors: Int?,
    val hitPoints: Int?,
    val dailyCost: Int?,
    val price: Int?
)

data class GoodModel(val id: Long, val name: String)
data class FleetModel(val id: Long, val name: String)
