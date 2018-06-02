package ch.patchcode.pr3.logbook.facilities

import ch.patchcode.pr3.logbook.entities.GameJpa
import org.springframework.data.jpa.repository.JpaRepository

interface FacilityRepository : JpaRepository<FacilityJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<FacilityJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}