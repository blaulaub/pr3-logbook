package ch.patchcode.pr3.logbook.controller

import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.objects.Game
import ch.patchcode.pr3.logbook.repositories.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController @Autowired constructor(
		private val gameRepository: GameRepository
) {

	@GetMapping("/games")
	@Transactional
	fun getGames() = gameRepository.findAll()

	@PostMapping("/games")
	@Transactional
	fun createGame(
			@RequestParam captainsName: String
	) = gameRepository.save(GameJpa(captainsName = captainsName)).toDto()

	@GetMapping("/games/{gameId}")
	@Transactional
	fun getGame(@PathVariable gameId: Long) = resolveGame(gameId).toDto()

	private fun resolveGame(gameId: Long): GameJpa {
		val game = gameRepository.findById(gameId)
		if (!game.isPresent) throw EntityNotFoundException("Game #" + gameId)
		return game.get()
	}
}