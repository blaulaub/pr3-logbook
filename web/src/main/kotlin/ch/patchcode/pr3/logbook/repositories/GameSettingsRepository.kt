package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.entities.GameSettingsJpa
import ch.patchcode.pr3.logbook.games.GameJpa
import org.springframework.data.repository.CrudRepository

interface GameSettingsRepository : CrudRepository<GameSettingsJpa, Long> {
	fun findOneByGame(game: GameJpa): GameSettingsJpa?
}