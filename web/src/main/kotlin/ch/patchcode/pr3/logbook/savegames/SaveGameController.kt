package ch.patchcode.pr3.logbook.savegames

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SaveGameController @Autowired constructor(
		private val saveGameService: SaveGameService
) {

	@GetMapping("/games/{gameId}/save")
	fun getGame(@PathVariable gameId: Long): SaveGame = saveGameService.getGame(gameId)
}

