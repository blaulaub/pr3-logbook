package ch.patchcode.pr3.logbook.controller

import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.repositories.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController @Autowired constructor(
		private val gameRepository: GameRepository
) {
	
	@PostMapping("/games")
  fun createGame(
			@RequestParam captainsName: String
	) = gameRepository.save(GameJpa(captainsName = captainsName)).toDto()
	
	@GetMapping("/games/{gameId}")
  fun getGame(@PathVariable gameId: Long) = gameRepository.findById(gameId).get().toDto()
}