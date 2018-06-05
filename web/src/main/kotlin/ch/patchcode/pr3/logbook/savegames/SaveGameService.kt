package ch.patchcode.pr3.logbook.savegames

import ch.patchcode.pr3.logbook.cities.CityModel
import ch.patchcode.pr3.logbook.cities.CityService
import ch.patchcode.pr3.logbook.facilities.FacilityModel
import ch.patchcode.pr3.logbook.facilities.FacilityService
import ch.patchcode.pr3.logbook.facilities.TurnoverModel
import ch.patchcode.pr3.logbook.games.GameModel
import ch.patchcode.pr3.logbook.games.GameService
import ch.patchcode.pr3.logbook.gamesettings.GameSettingsModel
import ch.patchcode.pr3.logbook.gamesettings.GameSettingsService
import ch.patchcode.pr3.logbook.goods.GoodModel
import ch.patchcode.pr3.logbook.goods.GoodService
import ch.patchcode.pr3.logbook.shiptypes.ShiptypeModel
import ch.patchcode.pr3.logbook.shiptypes.ShiptypeService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ch.patchcode.pr3.logbook.cityFactories.CityFactoryService
import ch.patchcode.pr3.logbook.cityproducts.CityProductService

@Service
class SaveGameService @Autowired constructor(
		private val gameService: GameService,
		private val gameSettingsService: GameSettingsService,
		private val goodService: GoodService,
		private val facilityService: FacilityService,
		private val shiptypeService: ShiptypeService,
		private val cityService: CityService,
		private val cityProductService: CityProductService,
		private val cityFactoryService: CityFactoryService
) {

	companion object {
		val log = LoggerFactory.getLogger(SaveGameService::class.java)
	}

	@Transactional
	fun getGame(gameId: Long): SaveGame {
		val game = gameService.getGame(gameId)
		return SaveGame(
				captainsName = game.captainsName,
				created = game.created,
				gameDate = game.gameDate,
				settings = getSettings(gameId),
				goods = getGoods(gameId),
				facilities = getFacilities(gameId),
				shiptypes = getShiptypes(gameId),
				cities = getCities(gameId)
		)
	}

	@Transactional
	fun putGame(gameId: Long, data: SaveGame) {
		gameService.updateGame(gameId, GameModel(
				id = gameId,
				captainsName = data.captainsName,
				created = data.created,
				gameDate = data.gameDate
		))
		putSettings(gameId, data.settings)
		putGoods(gameId, data.goods)
		putFacilities(gameId, data.facilities)
		putShiptypes(gameId, data.shiptypes)
		putCities(gameId, data.cities)
		log.info("not fully implemented: save {}", data)
	}

	private fun getSettings(gameId: Long): SaveGameSettings {
		val settings = gameSettingsService.findByGame(gameId)
		return SaveGameSettings(
				salaryPerDay = settings.salaryPerDay,
				workerPerCitizenRatio = settings.workerPerCitizenRatio
		)
	}

	private fun putSettings(gameId: Long, settings: SaveGameSettings): GameSettingsModel {
		return gameSettingsService.updateGameSettings(gameId, GameSettingsModel(
				salaryPerDay = settings.salaryPerDay,
				workerPerCitizenRatio = settings.workerPerCitizenRatio
		))
	}

	private fun getGoods(gameId: Long): List<SaveGameGood> {
		val goods = goodService.findByGame(gameId)
		return goods.map { good -> SaveGameGood(name = good.name) }
	}

	private fun putGoods(gameId: Long, goods: List<SaveGameGood>): List<GoodModel> {
		goodService.deleteByGameId(gameId)
		return goods.map { good -> goodService.createGood(gameId, good.name) };
	}

	private fun getFacilities(gameId: Long): List<SaveGameFacility> {
		val facilities = facilityService.findByGame(gameId)
		return facilities.map { facility ->
			SaveGameFacility(
					name = facility.name,
					constructionCost = facility.constructionCost,
					constructionDays = facility.constructionDays,
					maintenancePerDay = facility.maintenancePerDay,
					workers = facility.workers,
					material = facility.material.map { item -> item.toTurnover() },
					consumption = facility.consumption.map { consumption -> consumption.toTurnover() },
					production = facility.production?.toTurnover()
			)
		}
	}

	fun TurnoverModel.toTurnover() = SaveGameTurnover(
			good = this.good.name,
			amount = this.amount
	)

	private fun putFacilities(gameId: Long, facilities: List<SaveGameFacility>): List<FacilityModel> {
		facilityService.deleteByGameId(gameId)
		return facilities.map { facility -> putFacility(gameId, facility) }
	}

	private fun putFacility(gameId: Long, facility: SaveGameFacility): FacilityModel {
		val entity = facilityService.createFacility(gameId, facility.name)
		return facilityService.updateFacility(gameId, entity.id, FacilityModel(
				id = entity.id,
				name = facility.name,
				constructionCost = facility.constructionCost,
				constructionDays = facility.constructionDays,
				maintenancePerDay = facility.maintenancePerDay,
				workers = facility.workers,
				material = facility.material.map { item -> item.toTurnoverModel(gameId) },
				consumption = facility.consumption.map { consumption -> consumption.toTurnoverModel(gameId) },
				production = facility.production?.toTurnoverModel(gameId)
		))
	}

	fun SaveGameTurnover.toTurnoverModel(gameId: Long) = TurnoverModel(
			good = goodService.findByGameAndName(gameId, this.good)!!,
			amount = this.amount
	)

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

	private fun putShiptypes(gameId: Long, types: List<SaveGameShiptype>): List<ShiptypeModel> {
		shiptypeService.deleteByGameId(gameId)
		return types.map { type -> putShiptype(gameId, type) }
	}

	private fun putShiptype(gameId: Long, type: SaveGameShiptype): ShiptypeModel {
		val entity = shiptypeService.createShiptype(gameId, type.name)
		return shiptypeService.updateShiptype(gameId, entity.id, ShiptypeModel(
				id = entity.id,
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

		))
	}

	private fun getCities(gameId: Long): List<SaveGameCity> {
		val cities = cityService.findByGame(gameId)
		return cities.map { city ->
			SaveGameCity(
					name = city.name,
					factories = getCityFactoryCounts(gameId, city)
			)
		}
	}

	private fun getCityFactoryCounts(gameId: Long, city: CityModel): List<SaveGameFactoryCount> {
		val factoryCounts = cityFactoryService.findByGameAndCity(gameId, city.id)
		return factoryCounts.map { count ->
			SaveGameFactoryCount(
					name = count.facilityName,
					rivalCount = count.rivalCount,
					playerCount = count.playerCount
			)
		}
	}

	private fun putCities(gameId: Long, cities: List<SaveGameCity>) {
		cityService.deleteByGameId(gameId)
		for (city in cities) {
			// cities
			val cityModel = cityService.createCity(gameId, city.name)

			// city products
			val goods: List<GoodModel> = city.factories
					.map { factoryCount -> facilityService.findOneByGameIdAndName(gameId, factoryCount.name) }
					.map { factory -> factory.production?.good }
					.filterNotNull()
					.map { good -> goodService.findByGameAndName(gameId, good.name) }
					.filterNotNull()
			cityProductService.updateCityProducts(gameId, cityModel.id, goods)

			// city factory counts
			for (factoryCount in city.factories) {
				val factory = facilityService.findOneByGameIdAndName(gameId, factoryCount.name)
				    // TODO city factories, kinda like this:
			}
		}
	}

}
