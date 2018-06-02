package ch.patchcode.pr3.logbook.gamesettings

import ch.patchcode.pr3.logbook.games.GameJpa
import org.springframework.data.repository.CrudRepository

interface GameSettingsRepository : CrudRepository<GameSettingsJpa, Long> {
	fun findOneByGame(game: GameJpa): GameSettingsJpa?
}