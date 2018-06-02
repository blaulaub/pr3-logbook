package ch.patchcode.pr3.logbook.goods

import ch.patchcode.pr3.logbook.games.GameJpa
import org.springframework.data.repository.CrudRepository

interface GoodRepository : CrudRepository<GoodJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<GoodJpa>
	fun findOneByGameAndName(game: GameJpa, name: String): GoodJpa?
	fun deleteByGameAndId(game: GameJpa, id: Long)
}