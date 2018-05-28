package ch.patchcode.pr3.logbook.objects

import java.time.LocalDate

data class GameSettings(
		val game: Game,
		var salaryPerDay: Double?,
		var workerPerCitizenRatio: Double?		
)
