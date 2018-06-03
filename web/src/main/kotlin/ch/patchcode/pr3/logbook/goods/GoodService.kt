package ch.patchcode.pr3.logbook.goods

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GoodService @Autowired constructor(
		private val goodRepository: GoodRepository,
		private val gameService: GameService
) {

	@Transactional
	fun findByGame(gameId: Long) = goodRepository.findByGame(gameService.resolveGame(gameId)).map { it -> it.toModel() }

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

	fun resolveGood(goodId: Long): GoodJpa {
		val good = goodRepository.findById(goodId)
		if (!good.isPresent) throw EntityNotFoundException("Good #" + goodId)
		return good.get()
	}
}
