package ch.patchcode.pr3.logbook.objects

import java.time.LocalDate

data class City(
		val id: Long,
		val game: Game,
		val name: String
)

data class CityPopulation(
		val city: City,
		val date: LocalDate,
		val population: Int
)

data class CityProduct(
		val id: Long,
		val city: City,
		val good: Good
)