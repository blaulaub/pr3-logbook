package ch.patchcode.pr3.logbook.gamesettings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GameSettingsController @Autowired constructor(
		private val gameSettingsService: GameSettingsService
) {

	@GetMapping("/games/{gameId}/gameSettings")
	fun getGameSettings(
			@PathVariable gameId: Long
	): GameSettingsModel = gameSettingsService.findByGame(gameId)

	@PutMapping("/games/{gameId}/gameSettings")
	fun updateGameSettings(
			@PathVariable gameId: Long,
			@RequestBody gamesSetting: GameSettingsModel
	): GameSettingsModel = gameSettingsService.updateGameSettings(gameId, gamesSetting)
}
