package ch.patchcode.pr3.logbook.repositories

import ch.patchcode.pr3.logbook.entities.GameJpa
import ch.patchcode.pr3.logbook.entities.GameSettingsJpa
import org.springframework.data.repository.CrudRepository

interface GameSettingsRepository : CrudRepository<GameSettingsJpa, Long> {
	fun findOneByGame(game: GameJpa): GameSettingsJpa?
}