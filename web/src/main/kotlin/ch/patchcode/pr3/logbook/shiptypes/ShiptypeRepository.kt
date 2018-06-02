package ch.patchcode.pr3.logbook.shiptypes

import ch.patchcode.pr3.logbook.games.GameJpa
import org.springframework.data.repository.CrudRepository

interface ShiptypeRepository : CrudRepository<ShiptypeJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<ShiptypeJpa>
	fun deleteByGameAndId(game: GameJpa, id: Long)
}