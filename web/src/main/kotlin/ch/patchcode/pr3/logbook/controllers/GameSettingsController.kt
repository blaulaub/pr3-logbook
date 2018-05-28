package ch.patchcode.pr3.logbook.controllers

import ch.patchcode.pr3.logbook.model.GameSettingsModel
import ch.patchcode.pr3.logbook.services.GameSettingsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional

@RestController
class GameSettingsController @Autowired constructor(
		private val gameSettingsService: GameSettingsService
) {

	@GetMapping("/games/{gameId}/gameSettings")
	@Transactional
	fun getGameSettings(
			@PathVariable gameId: Long
	): GameSettingsModel = gameSettingsService.findByGame(gameId)

	@PutMapping("/games/{gameId}/gameSettings")
	@Transactional
	fun updateGameSettings(
			@PathVariable gameId: Long,
			@RequestBody gamesSetting: GameSettingsModel
	): GameSettingsModel = gameSettingsService.updateGameSettings(gameId, gamesSetting)

}