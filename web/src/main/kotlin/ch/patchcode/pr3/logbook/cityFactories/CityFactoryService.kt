package ch.patchcode.pr3.logbook.cityFactories

import ch.patchcode.pr3.logbook.cities.CityService
import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import ch.patchcode.pr3.logbook.facilities.FacilityService
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.cityproducts.CityProductService
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory
import ch.patchcode.pr3.logbook.facilities.FacilityJpa

@Service
class CityFactoryService @Autowired constructor(
		private val factoryCountRepository: FactoryCountRepository,
		private val gameService: GameService,
		private val cityService: CityService,
		private val cityProductService: CityProductService,
		private val facilityService: FacilityService
) {

	companion object {
		val log = LoggerFactory.getLogger(CityFactoryService::class.java)
	}

	fun findByGameAndCity(gameId: Long, cityId: Long): List<FactoryCountModel> {
		gameService.resolveGame(gameId)
		val city = cityService.resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)

		val expectedProducts = cityProductService.resolveByGameAndCity(gameId, cityId)
		val expectedFactories: List<FacilityJpa?> = expectedProducts.map { it -> facilityService.resolveByProduct(it.good) }

		val oldFacilitiesToCounts: Map<Long, FactoryCountJpa> = factoryCountRepository.findByCity(city).associateBy(
				{ it -> it.facility.id!! },
				{ it -> it })

		val items = ArrayList<FactoryCountJpa>()
		for (factory in expectedFactories) {
			if (factory != null) {
				val item = oldFacilitiesToCounts.get(factory.id!!) ?: FactoryCountJpa(city = city, facility = factory)
				items.add(item)
			}
		}

		return items.map { it -> it.toModel() }
	}

	fun updateFactoryCounts(gameId: Long, cityId: Long, counts: List<FactoryCountModel>): List<FactoryCountModel> {
		gameService.resolveGame(gameId)
		val city = cityService.resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)

		val oldCounts = factoryCountRepository.findByCity(city)
		val newFacilityIdsToFacilities = counts.associateBy(
				{ it -> it.facilityId },
				{ it -> facilityService.resolveFacility(it.facilityId) })

		val oldFacilities = oldCounts.map { it -> it.facility }
		val newFacilities = newFacilityIdsToFacilities.values
		for (facility in newFacilities) {
			if (facility.game.id != gameId) throw EntityNotFoundException("Facility #" + facility.id)
		}

		val obsoleteFacilities = oldFacilities.minus(newFacilities)
		obsoleteFacilities
				.forEach { it -> factoryCountRepository.deleteByCityAndFacility(city, it) }

		for (count in counts) {
			val item = oldCounts.find { it -> it.facility.id!! == count.facilityId } ?: FactoryCountJpa(
					city = city,
					facility = newFacilityIdsToFacilities.get(count.facilityId)!!)
			item.rivalCount = count.rivalCount
			item.playerCount = count.playerCount
			factoryCountRepository.save(item)
		}

		return factoryCountRepository.findByCity(city).map { it -> it.toModel() }

	}
}