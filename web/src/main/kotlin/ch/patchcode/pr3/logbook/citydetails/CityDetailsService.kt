package ch.patchcode.pr3.logbook.citydetails

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
	fun findByGameAndCity(gameId: Long, cityId: Long): CityDetailsModel {
		gameService.resolveGame(gameId)
		val city = cityService.resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)

		val details = cityDetailsRepository.findOneByCity(city) ?: CityDetailsJpa(
				city = city,
				population = 1200,
				warehouses = 0)

		return details.toModel()
	}

	@Transactional
	fun updateCityDetails(gameId: Long, cityId: Long, details: CityDetailsModel): CityDetailsModel {
		gameService.resolveGame(gameId)
		val city = cityService.resolveCity(cityId)
		if (city.game.id != gameId) throw EntityNotFoundException("City #" + cityId)

		val oldDetails = cityDetailsRepository.findOneByCity(city) ?: CityDetailsJpa(
				city = city,
				population = 1200,
				warehouses = 0)

		oldDetails.population = details.population;
		oldDetails.warehouses = details.warehouses;
		return cityDetailsRepository.save(oldDetails).toModel()
	}
}