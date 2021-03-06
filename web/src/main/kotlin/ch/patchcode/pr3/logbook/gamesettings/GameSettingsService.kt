package ch.patchcode.pr3.logbook.gamesettings

import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameSettingsService @Autowired constructor(
		private val gameSettingsRepository: GameSettingsRepository,
		private val gameService: GameService
) {

	@Transactional
	fun findByGame(gameId: Long): GameSettingsModel {
		val game = gameService.resolveGame(gameId)
		val settings = gameSettingsRepository.findOneByGame(game) ?: GameSettingsJpa(gameId, game)
		return settings.toModel()
	}

	@Transactional
	fun updateGameSettings(gameId: Long, gameSettings: GameSettingsModel): GameSettingsModel {
		val game = gameService.resolveGame(gameId)
		val oldSettings = gameSettingsRepository.findOneByGame(game) ?: GameSettingsJpa(gameId, game)
		oldSettings.salaryPerDay = gameSettings.salaryPerDay
		oldSettings.workerPerCitizenRatio = gameSettings.workerPerCitizenRatio
		return gameSettingsRepository.save(oldSettings).toModel()
	}
}