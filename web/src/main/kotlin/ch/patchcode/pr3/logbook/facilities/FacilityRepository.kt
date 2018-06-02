package ch.patchcode.pr3.logbook.facilities

import ch.patchcode.pr3.logbook.games.GameJpa
import org.springframework.data.repository.CrudRepository

interface FacilityRepository : CrudRepository<FacilityJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<FacilityJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}