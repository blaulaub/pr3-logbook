package ch.patchcode.pr3.logbook.services

import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.objects.Game
import ch.patchcode.pr3.logbook.repositories.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameService @Autowired constructor(
		private val gameRepository: GameRepository
) {

	fun getAll() = gameRepository.findAll()

	fun createGame(captainsName: String) = gameRepository.save(GameJpa(captainsName = captainsName)).toDto()

	fun resolveGame(gameId: Long): GameJpa {
		val game = gameRepository.findById(gameId)
		if (!game.isPresent) throw EntityNotFoundException("Game #" + gameId)
		return game.get()
	}

}