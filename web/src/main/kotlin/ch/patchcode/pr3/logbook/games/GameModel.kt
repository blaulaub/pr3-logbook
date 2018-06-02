package ch.patchcode.pr3.logbook.games

import java.time.LocalDateTime

data class GameModel(
		val id: Long,
		val captainsName: String,
		val created: LocalDateTime = LocalDateTime.now(),
		var gameDate: LocalDateTime? = null
)