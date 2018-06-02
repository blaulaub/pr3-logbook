package ch.patchcode.pr3.logbook.games

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController @Autowired constructor(
		private val gameService: GameService
) {

	@GetMapping("/games")
	@Transactional
	fun getGames(): List<GameModel> = gameService.getAll().map { it -> it.toModel() }

	@PostMapping("/games")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	fun createGame(
			@RequestParam captainsName: String
	): GameModel = gameService.createGame(captainsName)

	@GetMapping("/games/{gameId}")
	@Transactional
	fun getGame(@PathVariable gameId: Long): GameModel = gameService.getGame(gameId)

	@PutMapping("/games/{gameId}")
	@Transactional
	fun updateGame(
			@PathVariable gameId: Long,
			@RequestBody game: GameModel
	): GameModel = gameService.updateGame(gameId, game)

	@DeleteMapping("/games/{gameId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	fun deleteGame(@PathVariable gameId: Long) {
		gameService.deleteGame(gameId)
	}
}