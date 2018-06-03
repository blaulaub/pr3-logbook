package ch.patchcode.pr3.logbook.facilities

import ch.patchcode.pr3.logbook.games.GameJpa
import ch.patchcode.pr3.logbook.goods.GoodJpa
import org.springframework.data.repository.CrudRepository

interface FacilityRepository : CrudRepository<FacilityJpa, Long> {
	fun findByGame(game: GameJpa): Iterable<FacilityJpa>
	fun findOneByProductionGood(good: GoodJpa): FacilityJpa?
	fun deleteByGameAndId(game: GameJpa, id: Long)
}