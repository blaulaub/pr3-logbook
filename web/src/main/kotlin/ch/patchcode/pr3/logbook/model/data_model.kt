package ch.patchcode.pr3.logbook.model

data class GameSettingsModel(
		val salaryPerDay: Double? = null,
		val workerPerCitizenRatio: Double? = null
)

data class FleetModel(val id: Long, val name: String)
