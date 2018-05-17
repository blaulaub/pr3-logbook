package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.entities.FleetJpa
import ch.patchcode.pr3.logbook.entities.GameJpa
import org.springframework.data.repository.CrudRepository

interface FleetRepository : CrudRepository<FleetJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<FleetJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}