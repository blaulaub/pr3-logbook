package ch.patchcode.pr3.logbook.shiptypes

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
