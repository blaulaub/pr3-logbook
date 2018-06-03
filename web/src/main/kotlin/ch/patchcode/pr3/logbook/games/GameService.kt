package ch.patchcode.pr3.logbook.games

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameService @Autowired constructor(
		private val gameRepository: GameRepository
) {

	@Transactional
	fun getAll() = gameRepository.findAll().map { it -> it.toModel() }

	@Transactional
	fun getGame(gameId: Long): GameModel = resolveGame(gameId).toModel()

	@Transactional
	fun createGame(captainsName: String) = gameRepository.save(GameJpa(captainsName = captainsName)).toModel()

	fun resolveGame(gameId: Long): GameJpa {
		val game = gameRepository.findById(gameId)
		if (!game.isPresent) throw EntityNotFoundException("Game #" + gameId)
		return game.get()
	}

	@Transactional
	fun updateGame(gameId: Long, game: GameModel): GameModel {
		if (gameId != game.id) throw IllegalArgumentException("URL gameId does not match model game id")
		val oldGame = resolveGame(gameId)

		oldGame.captainsName = game.captainsName
		oldGame.created = game.created
		oldGame.gameDate = game.gameDate

		return gameRepository.save(oldGame).toModel()
	}

	@Transactional
	fun deleteGame(gameId: Long) {
		gameRepository.deleteById(gameId)
	}
}