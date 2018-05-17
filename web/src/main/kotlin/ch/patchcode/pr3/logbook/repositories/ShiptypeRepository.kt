package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.entities.ShiptypeJpa
import ch.patchcode.pr3.logbook.entities.GameJpa
import org.springframework.data.repository.CrudRepository

interface ShiptypeRepository : CrudRepository<ShiptypeJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<ShiptypeJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}