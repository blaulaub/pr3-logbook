package ch.patchcode.pr3.logbook.services

import ch.patchcode.pr3.logbook.entities.GameSettingsJpa
import ch.patchcode.pr3.logbook.games.GameService
import ch.patchcode.pr3.logbook.model.GameSettingsModel
import ch.patchcode.pr3.logbook.repositories.GameSettingsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameSettingsService @Autowired constructor(
		private val gameSettingsRepository: GameSettingsRepository,
		private val gameService: GameService
) {

	fun findByGame(gameId: Long): GameSettingsModel {
		val game = gameService.resolveGame(gameId)
		val settings = gameSettingsRepository.findOneByGame(game) ?: GameSettingsJpa(gameId, game)
		return settings.toModel()
	}

	fun updateGameSettings(gameId: Long, gameSettings: GameSettingsModel): GameSettingsModel {
		val game = gameService.resolveGame(gameId)
		val oldSettings = gameSettingsRepository.findOneByGame(game) ?: GameSettingsJpa(gameId, game)
		oldSettings.salaryPerDay = gameSettings.salaryPerDay
		oldSettings.workerPerCitizenRatio = gameSettings.workerPerCitizenRatio
		return gameSettingsRepository.save(oldSettings).toModel()
	}
}