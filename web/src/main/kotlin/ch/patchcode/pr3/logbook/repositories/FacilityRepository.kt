package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.facilities.FacilityJpa
import org.springframework.data.jpa.repository.JpaRepository

interface FacilityRepository : JpaRepository<FacilityJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<FacilityJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}