package ch.patchcode.pr3.logbook.savegames

import ch.patchcode.pr3.logbook.games.GameService
import ch.patchcode.pr3.logbook.gamesettings.GameSettingsService
import ch.patchcode.pr3.logbook.goods.GoodService
import ch.patchcode.pr3.logbook.shiptypes.ShiptypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SaveGameService @Autowired constructor(
		private val gameService: GameService,
		private val gameSettingsService: GameSettingsService,
		private val goodService: GoodService,
		private val shiptypeService: ShiptypeService
) {

	@Transactional
	fun getGame(gameId: Long): SaveGame {
		val game = gameService.getGame(gameId)
		return SaveGame(
				captainsName = game.captainsName,
				created = game.created,
				gameDate = game.gameDate,
				settings = getSettings(gameId),
				goods = getGoods(gameId),
				shiptypes = getShiptypes(gameId)
		)
	}

	private fun getSettings(gameId: Long): SaveGameSettings {
		val settings = gameSettingsService.findByGame(gameId)
		return SaveGameSettings(
				salaryPerDay = settings.salaryPerDay,
				workerPerCitizenRatio = settings.workerPerCitizenRatio
		)
	}

	private fun getGoods(gameId: Long): List<SaveGameGood> {
		val goods = goodService.findByGame(gameId)
		return goods.map { good -> SaveGameGood(name = good.name) }
	}

	private fun getShiptypes(gameId: Long): List<SaveGameShiptype> {
		val types = shiptypeService.findByGame(gameId)
		return types.map { type ->
			SaveGameShiptype(
					name = type.name,
					cargoSpace = type.cargoSpace,
					maneuverability = type.maneuverability,
					draft = type.draft,
					minSpeed = type.minSpeed,
					maxSpeed = type.maxSpeed,
					cannons = type.cannons,
					sailors = type.sailors,
					hitPoints = type.hitPoints,
					dailyCost = type.dailyCost,
					price = type.price
			)
		}
	}
}