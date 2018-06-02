package ch.patchcode.pr3.logbook.cities

import ch.patchcode.pr3.logbook.games.GameJpa
import org.springframework.data.repository.CrudRepository

interface CityRepository : CrudRepository<CityJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<CityJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}