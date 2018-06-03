package ch.patchcode.pr3.logbook.savegames

import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SaveGameService @Autowired constructor(
		private val gameService: GameService
) {
	fun getGame(gameId: Long): SaveGame {
		val game = gameService.getGame(gameId)
		return SaveGame(
				captainsName = game.captainsName
		)
	}
}