package ch.patchcode.pr3.logbook.model

data class CityModel(val id: Long, val name: String)

data class GoodModel(val id: Long, val name: String)

data class Turnover(
		val id: Long,
		val good: GoodModel,
		val amount: Double)

data class FacilityModel(
		val id: Long,
		val name: String,
		val constructionCost: Int? = null,
		val constructionDays: Int? = null,
		val maintenancePerDay: Int? = null,
		val workers: Int? = null,
		val consumption: List<Turnover> = ArrayList(),
		val production: Turnover? = null)

data class ShiptypeModel(
		val id: Long,
		val name: String,
		val cargoSpace: Int? = null,
    val maneuverability: Int? = null,
    val draft: Int? = null,
    val minSpeed: Int? = null,
    val maxSpeed: Int? = null,
    val cannons: Int? = null,
    val sailors: Int? = null,
    val hitPoints: Int? = null,
    val dailyCost: Int? = null,
    val price: Int? = null
)

data class FleetModel(val id: Long, val name: String)
