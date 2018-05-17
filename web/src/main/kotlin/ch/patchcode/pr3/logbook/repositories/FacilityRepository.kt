package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.entities.FacilityJpa
import ch.patchcode.pr3.logbook.entities.GameJpa
import org.springframework.data.repository.CrudRepository

interface FacilityRepository : CrudRepository<FacilityJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<FacilityJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}