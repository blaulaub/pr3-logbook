package ch.patchcode.pr3.logbook.controllers

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
import ch.patchcode.pr3.logbook.services.GameService
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
class GameController @Autowired constructor(
		private val gameService: GameService
) {

	@GetMapping("/games")
	@Transactional
	fun getGames(): List<Game> = gameService.getAll().map { it -> it.toDto() }

	@PostMapping("/games")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	fun createGame(
			@RequestParam captainsName: String
	): Game = gameService.createGame(captainsName)

	@GetMapping("/games/{gameId}")
	@Transactional
	fun getGame(@PathVariable gameId: Long): Game = gameService.resolveGame(gameId).toDto()

	@DeleteMapping("/games/{gameId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	fun deleteGame(@PathVariable gameId: Long) {
		gameService.deleteGame(gameId)
	}
}