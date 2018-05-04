package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.entities.CityJpa
import ch.patchcode.pr3.logbook.entities.GameJpa
import org.springframework.data.repository.CrudRepository

interface CityRepository : CrudRepository<CityJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<CityJpa>
}