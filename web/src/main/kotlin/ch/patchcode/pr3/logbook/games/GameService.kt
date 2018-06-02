package ch.patchcode.pr3.logbook.games

import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameService @Autowired constructor(
		private val gameRepository: GameRepository
) {

	fun getAll() = gameRepository.findAll()

	fun getGame(gameId: Long): GameModel = resolveGame(gameId).toModel()

	fun createGame(captainsName: String) = gameRepository.save(GameJpa(captainsName = captainsName)).toModel()

	fun resolveGame(gameId: Long): GameJpa {
		val game = gameRepository.findById(gameId)
		if (!game.isPresent) throw EntityNotFoundException("Game #" + gameId)
		return game.get()
	}

	fun updateGame(gameId: Long, game: GameModel): GameModel {
		if (gameId != game.id) throw IllegalArgumentException("URL gameId does not match model game id")
		val oldGame = resolveGame(gameId)

		oldGame.captainsName = game.captainsName
		oldGame.created = game.created
		oldGame.gameDate = game.gameDate

		return gameRepository.save(oldGame).toModel()
	}

	fun deleteGame(gameId: Long) {
		gameRepository.deleteById(gameId)
	}
}