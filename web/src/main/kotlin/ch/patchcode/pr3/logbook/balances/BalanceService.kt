package ch.patchcode.pr3.logbook.balances

import ch.patchcode.pr3.logbook.cities.CityService
import ch.patchcode.pr3.logbook.cityFactories.CityFactoryService
import ch.patchcode.pr3.logbook.cityFactories.FactoryCountModel
import ch.patchcode.pr3.logbook.facilities.FacilityModel
import ch.patchcode.pr3.logbook.facilities.FacilityService
import ch.patchcode.pr3.logbook.goods.GoodService
import ch.patchcode.pr3.logbook.perheadconsumptions.PerHeadConsumptionModel
import ch.patchcode.pr3.logbook.perheadconsumptions.PerHeadConsumptionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ch.patchcode.pr3.logbook.facilities.TurnoverModel
import ch.patchcode.pr3.logbook.citydetails.CityDetailsService
import ch.patchcode.pr3.logbook.citydetails.CityDetailsModel

@Service
class BalanceService @Autowired constructor(
		private val goodService: GoodService,
		private val cityService: CityService,
		private val cityDetailsService: CityDetailsService,
		private val cityFactoryService: CityFactoryService,
		private val perHeadConsumptionService: PerHeadConsumptionService,
		private val facilityService: FacilityService
) {

	@Transactional
	fun getBalanceByGame(gameId: Long): List<BalanceModel> {
		val goods = goodService.findByGame(gameId)
		val balances = goods.associateBy({ it -> it.name }, { it -> BalanceModel(it.name) })

		val cities = cityService.findByGame(gameId)

		val perHeadConsumptions: Map<String, PerHeadConsumptionModel> =
				perHeadConsumptionService.findByGame(gameId).associateBy(
						{ it -> it.good }, { it -> it })
		val facilities: Map<String, FacilityModel> =
				facilityService.findByGame(gameId).associateBy(
						{ it -> it.name }, { it -> it })

		for (city in cities) {

			for (factory: FactoryCountModel in cityFactoryService.findByGameAndCity(gameId, city.id)) {
				val facility = facilities.get(factory.facilityName)
				if (facility != null) {
					facility.consumption.forEach { it ->
						run {
							balances.get(it.good.name)!!.byPlayerFactoryConsumption -= it.amount * factory.playerCount
							balances.get(it.good.name)!!.byRivalFactoryConsumption -= it.amount * factory.rivalCount
						}
					}
					val production = facility.production
					if (production != null) {
						balances.get(production.good.name)!!.byPlayerFactoryProduction += production.amount * factory.playerCount
						balances.get(production.good.name)!!.byRivalFactoryProduction += production.amount * factory.rivalCount
					}
				}
			}

			val cityDetails: CityDetailsModel = cityDetailsService.findByGameAndCity(gameId, city.id)
			for (good in goods) {
				val perHead: PerHeadConsumptionModel = perHeadConsumptions.get(good.name)!!
				// divide by 100 because population is counted in hundreds
				// divide by 10 because consumption is measured per 10 days
				balances.get(good.name)!!.byCityConsumption -= cityDetails.population * perHead.consumptionPerHundred / 100.0 / 10.0
			}
		}

		return balances.values.toList()
	}
}
