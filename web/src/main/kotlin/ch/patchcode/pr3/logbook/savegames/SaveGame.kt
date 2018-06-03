package ch.patchcode.pr3.logbook.savegames

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime

data class SaveGame(
		val captainsName: String,
		val created: LocalDateTime,
		val gameDate: LocalDateTime?,
		val settings: SaveGameSettings,
		val goods: List<SaveGameGood>,
		val shiptypes: List<SaveGameShiptype>
)

data class SaveGameSettings(
		val salaryPerDay: Double?,
		val workerPerCitizenRatio: Double?
)

data class SaveGameGood(
		val name: String
)

data class SaveGameShiptype(
		val name: String,
		val cargoSpace: Int?,
    val maneuverability: Int?,
    val draft: Int?,
    val minSpeed: Int?,
    val maxSpeed: Int?,
    val cannons: Int?,
    val sailors: Int?,
    val hitPoints: Int?,
    val dailyCost: Int?,
    val price: Int?
)
