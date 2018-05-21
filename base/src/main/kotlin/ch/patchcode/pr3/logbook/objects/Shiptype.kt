package ch.patchcode.pr3.logbook.objects

data class Shiptype(
		val id: Long,
		val game: Game,
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
