package ch.patchcode.pr3.logbook.fleets

import ch.patchcode.pr3.logbook.games.GameJpa
import org.springframework.data.repository.CrudRepository

interface FleetRepository : CrudRepository<FleetJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<FleetJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}