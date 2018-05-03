package ch.patchcode.pr3.logbook.objects

data class Facility(
		val id: Long,
		val game: Game,
		val name: String,
		val constructionCost: Int = 0,
		val constructionDays: Int = 0,
		val maintenance: Int = 0
)