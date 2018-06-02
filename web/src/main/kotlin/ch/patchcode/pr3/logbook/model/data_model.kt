package ch.patchcode.pr3.logbook.model

import ch.patchcode.pr3.logbook.goods.GoodModel

data class GameSettingsModel(
		val salaryPerDay: Double? = null,
		val workerPerCitizenRatio: Double? = null
)

data class FleetModel(val id: Long, val name: String)
