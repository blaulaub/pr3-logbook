package ch.patchcode.pr3.logbook.citydetails

import ch.patchcode.pr3.logbook.cities.CityJpa
import ch.patchcode.pr3.logbook.cities.CityService
import ch.patchcode.pr3.logbook.exception.EntityNotFoundException
import ch.patchcode.pr3.logbook.games.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CityDetailsService @Autowired constructor(
		private val cityDetailsRepository: CityDetailsRepository,
		private val gameService: GameService,
		private val cityService: CityService
) {

	@Transactional
	fun findByGameAndCity(gameId: Long, cityId: Long): CityDetailsModel = resolveByGameAndCity(gameId, cityId).toModel()

	@Transactional
	fun updateCityDetails(gameId: Long, cityId: Long, details: CityDetailsModel): CityDetailsModel {
		val oldDetails = resolveByGameAndCity(gameId, cityId)
		oldDetails.population = details.population;
		oldDetails.warehouses = details.warehouses;
		oldDetails.support = details.support;
		oldDetails.isExportCity = details.isExportCity;
		return cityDetailsRepository.save(oldDetails).toModel()
	}

	fun resolveByGameAndCity(gameId: Long, cityId: Long): CityDetailsJpa {
		gameService.resolveGame(gameId)
		val city = cityService.resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)

		return cityDetailsRepository.findOneByCity(city) ?: defaultCityDetails(city)
	}

	private fun defaultCityDetails(city: CityJpa) = CityDetailsJpa(
			city = city,
			population = 1200,
			warehouses = 0,
			support = 0.0,
			isExportCity = false)

}