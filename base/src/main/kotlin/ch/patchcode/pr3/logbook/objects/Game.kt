package ch.patchcode.pr3.logbook.objects

import java.time.LocalDateTime

data class Game(
		val id: Long,
		val captainsName: String,
		val created: LocalDateTime = LocalDateTime.now()
)