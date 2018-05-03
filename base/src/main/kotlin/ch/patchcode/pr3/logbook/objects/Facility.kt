package ch.patchcode.pr3.logbook.objects

data class Facility(
		val id: Long,
		val game: Game,
		val name: String,
		val constructionCost: Int = 0,
		val constructionDays: Int = 0,
		val maintenancePerDay: Int = 0
)

data class FacilityConsumption(
		val facility: Facility,
		val good: Good,
		val amount: Int
)

data class FacilityProduction(
		val facility: Facility,
		val good: Good,
		val amount: Int
)