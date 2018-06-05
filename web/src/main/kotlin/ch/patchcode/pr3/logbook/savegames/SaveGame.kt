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
		val facilities: List<SaveGameFacility>,
		val shiptypes: List<SaveGameShiptype>,
		val cities: List<SaveGameCity>
)

data class SaveGameSettings(
		val salaryPerDay: Double?,
		val workerPerCitizenRatio: Double?
)

data class SaveGameGood(
		val name: String
)

data class SaveGameFacility(
		val name: String,
		val constructionCost: Int?,
		val constructionDays: Int?,
		val maintenancePerDay: Int?,
		val workers: Int?,
		val material: List<SaveGameTurnover>,
		val consumption: List<SaveGameTurnover>,
		val production: SaveGameTurnover?
)

data class SaveGameTurnover(
		val good: String,
		val amount: Double
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

data class SaveGameCity(
		val name: String
)
