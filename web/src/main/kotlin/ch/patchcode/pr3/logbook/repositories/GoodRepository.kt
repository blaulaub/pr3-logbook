package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.goods.GoodJpa
import org.springframework.data.repository.CrudRepository

interface GoodRepository : CrudRepository<GoodJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<GoodJpa>
	fun findOneByGameAndName(game: GameJpa, name: String): GoodJpa?
	fun deleteByGameAndId(game: GameJpa, id: Long)
}