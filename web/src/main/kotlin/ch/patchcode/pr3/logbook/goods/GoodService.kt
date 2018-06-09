package ch.patchcode.pr3.logbook.goods

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ch.patchcode.pr3.logbook.games.GameJpa

@Service
class GoodService @Autowired constructor(
		private val goodRepository: GoodRepository,
		private val gameService: GameService
) {

	@Transactional
	fun findByGame(gameId: Long) = resolveByGameId(gameId).map { it -> it.toModel() }

	@Transactional
	fun findByGameAndName(gameId: Long, name: String) = goodRepository.findOneByGameAndName(gameService.resolveGame(gameId), name)?.toModel()

	@Transactional
	fun createGood(gameId: Long, name: String) = goodRepository.save(GoodJpa(game = gameService.resolveGame(gameId), name = name)).toModel()

	@Transactional
	fun getGood(gameId: Long, goodId: Long): GoodModel {
		gameService.resolveGame(gameId)
		val good = resolveGood(goodId)
		if (good.game.id != gameId) throw EntityNotFoundException("Good #" + goodId)
		return good.toModel()
	}

	@Transactional
	fun deleteGood(gameId: Long, goodId: Long) {
		val game = gameService.resolveGame(gameId)
		goodRepository.deleteByGameAndId(game, goodId)
	}

	fun deleteByGameId(gameId: Long) = goodRepository.deleteByGameId(gameId)

	fun resolveGood(goodId: Long): GoodJpa {
		val good = goodRepository.findById(goodId)
		if (!good.isPresent) throw EntityNotFoundException("Good #" + goodId)
		return good.get()
	}
	
	fun resolveByGameId(gameId: Long): Iterable<GoodJpa> = goodRepository.findByGame(gameService.resolveGame(gameId))
	
	fun resolveByGameIdAndName(gameId: Long, name: String) : GoodJpa {
		val good = goodRepository.findOneByGameAndName(gameService.resolveGame(gameId), name)
		if (good == null) throw EntityNotFoundException("Good " + name)
		return good
	}
}
